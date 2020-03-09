package com.example.urbalog;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.urbalog.Class.Bet;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.Player;
import com.example.urbalog.Class.Role;
import com.example.urbalog.Class.Signal;
import com.example.urbalog.Class.TransferPackage;
import com.example.urbalog.Class.Triplet;
import com.example.urbalog.Json.JsonStats;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import com.google.android.gms.nearby.connection.Strategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Helper class for network interactions using Nearby Connection API
 * This handle advertise/discovery, data sending, disconnection and managements of connections
 */

public class NetworkHelper implements Serializable {
    private Context appContext;
    private PlayerViewActivity currentPlayerView = null;

    private boolean advertising;
    private boolean discovering;
    private boolean host;

    private static int NB_PLAYERS = 5;
    private static int NB_BUILDINGS = 6;

    private Game currentGame;
    private boolean gameStarted;
    private Player player;

    private ArrayList<Pair<String, String>> listPlayer; // Pair array for connexion information storage of connected users
    private ArrayList<Triplet<Player, String, String>> playersInformations; // Pair array with Player instance of clients

    private int nextTurnVotes = 0;

    private final ConnectionsClient connectionsClient;
    private static final String TAG = "UrbalogGame"; // Tag for log
    private final String codeName = CodenameGenerator.generate();
    private final String playerUUID;

    private static final String[] REQUIRED_PERMISSIONS =
            new String[] {
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            };

    private static final int REQUEST_CODE_REQUIRED_PERMISSIONS = 1;

    private static final Strategy STRATEGY = Strategy.P2P_STAR; // Nearby connection strategie for data sending

    // Callbacks for receiving payloads
    private final PayloadCallback payloadCallback =
            new PayloadCallback() {
                /**
                 * Callback for data reception
                 * It deserialize the received object (in byte) to an instance
                 * @param endpointId
                 * @param payload
                 */
                @SuppressLint("InflateParams")
                @Override
                public void onPayloadReceived(@NonNull String endpointId, Payload payload) {

                    /* Deserialization of incoming payload */
                    Object dataReceived = new Object();
                    try {
                        dataReceived = SerializationHelper.deserialize(payload.asBytes());
                        Log.i(TAG, "sendToAllClients: "+ dataReceived.toString());

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    /* Process data received based on this type and if it's host or not */
                    /* If it's a player device */
                    if(!host){
                        /* Manage TransferPackage data */
                        if(dataReceived instanceof TransferPackage){
                            /* If host have send Market, usually used for respond to player bet with updated state of Market */
                            if(((TransferPackage) dataReceived).second instanceof Market)
                            {
                                try {
                                    player.resetFinancementRessource();
                                    sendToClient(new Triplet<Signal, String, Player>(Signal.UPDATE_PLAYER, playerUUID, player), endpointId);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                currentGame.setMarket(((Market) ((TransferPackage) dataReceived).second));
                                updatePlayerView();
                                currentPlayerView.setButtonState(true);
                                currentPlayerView.setEnabledBetButtons(true);
                            }
                            /* If host have send Bet, no longer used for the moment */
                            else if(((TransferPackage) dataReceived).second instanceof Bet){
                                if(currentGame.equals(((Game) ((TransferPackage) dataReceived).first)))
                                {
                                    currentGame.majBet(((Bet) ((TransferPackage) dataReceived).second));
                                    if(currentPlayerView != null)
                                        updatePlayerView();

                                }
                            }
                            /* If host have send PLayer and Game instances for returning player */
                            else if(((TransferPackage) dataReceived).first instanceof Game){
                                Log.i(TAG, "Restart game");
                                currentGame = ((Game)((TransferPackage) dataReceived).first);
                                player = ((Player)((TransferPackage) dataReceived).second);
                                gameStarted = true;
                                Intent myIntent = new Intent(appContext, PlayerViewActivity.class);
                                appContext.startActivity(myIntent);
                                if(currentPlayerView != null){
                                    updatePlayerView();
                                }
                            }
                            /* If host have send Signal, used for communicate with players without data transfer */
                            else if(((TransferPackage) dataReceived).first instanceof Signal){
                               switch((Signal)((TransferPackage) dataReceived).first)
                               {
                                   /* Ask player to check if his goals are completed */
                                   case CHECK_GOALS:
                                       if(player.checkGoals((ArrayList<Building>)((TransferPackage) dataReceived).second)
                                           && (currentGame.getCity().getBuildings().size() + ((ArrayList<Building>) ((TransferPackage) dataReceived).second).size()) < NB_BUILDINGS){

                                           final AlertDialog.Builder scoreDialog = new AlertDialog.Builder(getCurrentPlayerView());

                                           LayoutInflater inflater = getCurrentPlayerView().getLayoutInflater();

                                           scoreDialog.setView(inflater.inflate(R.layout.alert_dialog,null))
                                                  .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {
                                                          dialogInterface.dismiss();
                                                      }
                                                  });

                                           final AlertDialog alertScoreDialog = scoreDialog.create();

                                           alertScoreDialog.show();
                                           Objects.requireNonNull(alertScoreDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);

                                       }
                                       try {
                                           player.resetFinancementRessource();
                                           sendToClient(new Triplet<Signal, String, Player>(Signal.UPDATE_PLAYER, playerUUID, player), endpointId);
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                       break;
                                   /* Report to player the end of the game */
                                   case GAME_OVER:
                                       currentGame = (Game)((TransferPackage) dataReceived).second;
                                       try {
                                           sendToClient(new Triplet<Signal, String, Player>(Signal.UPDATE_PLAYER, playerUUID, player), endpointId);
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                       currentPlayerView.finish();
                                       Intent myIntent = new Intent(appContext, EndGameActivity.class);
                                       appContext.startActivity(myIntent);
                                       break;
                                   default:
                                       break;
                               }
                            }
                        }
                        /* If host have send Game, used for returning player, when the game start or at new turn */
                        else if(dataReceived instanceof Game){
                            currentGame = (Game) dataReceived;
                            if(currentPlayerView != null) {
                                updatePlayerView();
                                currentPlayerView.resetTurnButton();
                                currentPlayerView.resetColorRessources();
                            }
                            else
                                gameStarted = true;
                        }
                        /* If host have send Role, used on game start for role attribution */
                        else if(dataReceived instanceof Role){
                            player.setRole((Role) dataReceived);
                            try {
                                sendToClient(new Triplet<Signal, String, Player>(Signal.UPDATE_PLAYER, playerUUID, player), endpointId);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Intent myIntent = new Intent(appContext, PlayerViewActivity.class);
                            appContext.startActivity(myIntent);
                        }
                    }
                    /* If it's the host device */
                    else
                    {
                        /* If received payload is a instance of TransferPackage */
                        if(dataReceived instanceof TransferPackage){
                            /* If player have send Market, no longer used for the moment */
                            if(((TransferPackage) dataReceived).second instanceof Market) {
                                if (currentGame.equals(((Game) ((TransferPackage) dataReceived).first))) {
                                    currentGame.setMarket(((Market) ((TransferPackage) dataReceived).second));
                                    try {
                                        TransferPackage resend = new TransferPackage<Game, Market>((((Game) ((TransferPackage) dataReceived).first)), ((Market) ((TransferPackage) dataReceived).second));
                                        sendToAllClients(resend);
                                    }
                                    catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                else if(((TransferPackage) dataReceived).second instanceof Signal){
                                    switch(((Signal) ((TransferPackage) dataReceived).second)){
                                        case RETURN_PLAYER:
                                            for(int i = 0; i < playersInformations.size(); i++) {
                                                if(playersInformations.get(i).getSecond().equals(((String) ((TransferPackage) dataReceived).second))){
                                                    try {
                                                        sendToClient(new TransferPackage<Game, Player>(currentGame, playersInformations.get(i).getFirst()), endpointId);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                }
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                            else if(((TransferPackage) dataReceived).first instanceof Player){
                                if(gameStarted) {
                                    Log.i(TAG, "gameStarted");
                                    for (int i = 0; i < playersInformations.size(); i++) {
                                        if (playersInformations.get(i).getSecond().equals(((String) ((TransferPackage) dataReceived).second))) {
                                            try {
                                                Log.i(TAG, "returning player");
                                                sendToClient(new TransferPackage<Game, Player>(currentGame, playersInformations.get(i).getFirst()), endpointId);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        }
                                    }
                                }
                                else{
                                    Log.i(TAG, "!gameStarted: ");
                                    for (int i = 0; i < playersInformations.size(); i++) {
                                        if(playersInformations.get(i).getThird().equals(endpointId)){
                                            playersInformations.get(i).setFirst(((Player) ((TransferPackage) dataReceived).first));
                                            break;
                                        }
                                    }
                                }
                            }
                            /* If player have send Bet, always used when player put or remove a bet on a building in the market
                            *  after updating the host market with this Bet, it will resend it to all clients */
                            else if(((TransferPackage) dataReceived).second instanceof Bet){
                                if(currentGame.equals(((Game) ((TransferPackage) dataReceived).first)))
                                {
                                    currentGame.majBet(((Bet) ((TransferPackage) dataReceived).second));
                                    try {
                                        sendToAllClients(new TransferPackage<Game, Market>(currentGame, currentGame.getMarket()));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        /* if player have send Triplet data set */
                        else if(dataReceived instanceof Triplet){
                            if(((Triplet)dataReceived).getFirst() instanceof Signal){
                                switch((Signal)(((Triplet)dataReceived).getFirst())){
                                    case UPDATE_PLAYER:
                                        for (int i = 0; i < playersInformations.size(); i++) {
                                            if (playersInformations.get(i).getSecond().equals((String)(((Triplet)dataReceived).getSecond()))){
                                                playersInformations.get(i).setFirst((Player)(((Triplet)dataReceived).getThird()));
                                            }
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        /* If player have send Player, used on client connection to the host and often during the game */
                        else if(dataReceived instanceof Player){
                            for (int i = 0; i < playersInformations.size(); i++) {
                                if(playersInformations.get(i).getThird().equals(endpointId)){
                                    playersInformations.get(i).setFirst((Player)dataReceived);
                                    break;
                                }
                            }
                        }
                        /* If player have send Signal, used for communicate with the host without data transfer */
                        else if(dataReceived instanceof Signal){
                            switch ((Signal) dataReceived)
                            {
                                /* Report to host a next turn vote */
                                case NEXT_TURN:
                                    nextTurnVotes++;
                                    break;

                                /* Report to host the cancellation of a next turn vote */
                                case CANCEL_NEXT_TURN:
                                    nextTurnVotes--;
                                    break;

                                default:
                                    break;
                            }
                            /* If all players have voted for next turn */
                            if(nextTurnVotes == NB_PLAYERS)
                            {
                                /* Check market and build financed building */
                                ArrayList<Building> newBuildings = new ArrayList<Building>();
                                for(int i = 0; i < currentGame.getMarket().getBuildings().size(); i++) {
                                    if(currentGame.getMarket().getBuildings().get(i).isFilled()) {
                                        currentGame.getCity().addBuilding(currentGame.getMarket().getBuildings().get(i));
                                        newBuildings.add(currentGame.getMarket().getBuildings().get(i));
                                        // Delete build buildings to deck
                                        currentGame.getMarket().deleteBuilding(currentGame.getMarket().getBuildings().get(i));
                                    }
                                }
                                try {
                                    sendToAllClients(new TransferPackage<Signal, ArrayList<Building>>(Signal.CHECK_GOALS, newBuildings));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                currentGame.updateAllGameScores();
                                /* Check if the game is finish */
                                // If not, refresh market and update game instance of players
                                if(currentGame.getCity().getBuildings().size() < NB_BUILDINGS) {
                                    currentGame.refreshMarket();
                                    currentGame.incrTurn();
                                    try {
                                        sendToAllClients(currentGame);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    nextTurnVotes = 0;
                                }
                                else {
                                    try {
                                        sendToAllClients(new TransferPackage<Signal, Game>(Signal.GAME_OVER, currentGame));
                                        JsonStats.giveContext(appContext);
                                        ArrayList<Player> endGamePlayerList = new ArrayList<Player>();
                                        for (int i = 0; i < playersInformations.size(); i++) {
                                            endGamePlayerList.add(playersInformations.get(i).getFirst());
                                        }
                                        Log.d("debug", "ok");
                                        JsonStats.writeGame(endGamePlayerList);
                                        gameStarted = false;
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }

                /**
                 * Call when data transfer of a chunk is finish (Succeed or Failed)
                 * PayloadTransferUpdate parameter contains status of the payload transfer
                 * @param endpointId
                 * @param update
                 */
                @Override
                public void onPayloadTransferUpdate(@NonNull String endpointId, @NonNull PayloadTransferUpdate update) {
                    Log.i(TAG, String.format(
                            "onPayloadTransferUpdate(endpointId=%s, update=%s)", endpointId, update));
                }
            };

    // Callbacks for finding other devices
    private final EndpointDiscoveryCallback endpointDiscoveryCallback =
            new EndpointDiscoveryCallback() {
                @Override
                public void onEndpointFound(@NonNull String endpointId, @NonNull DiscoveredEndpointInfo info) {
                    Log.i(TAG, "onEndpointFound: endpoint found, connecting");
                    connectionsClient
                            .requestConnection(playerUUID, endpointId, connectionLifecycleCallback)
                            .addOnFailureListener(
                                    new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG,"requestConnection() failed.", e);
                                        }
                                    });
                }

                @Override
                public void onEndpointLost(@NonNull String endpointId) {
                    for (int i = 0; i<listPlayer.size(); i++)
                    {
                        if(listPlayer.get(i).second.equals(endpointId)) {
                            listPlayer.remove(i);
                            break;
                        }
                    }
                }
            };

    // Callbacks for connections to other devices
    private final ConnectionLifecycleCallback connectionLifecycleCallback =
            new ConnectionLifecycleCallback() {
                private String playerName;

                @Override
                public void onConnectionInitiated(@NonNull String endpointId, @NonNull ConnectionInfo connectionInfo)
                {
                    if(host){
                        boolean returnPlayer = false;
                        /* If game isn't start and room isn't full */
                        if ((!gameStarted) && (listPlayer.size() < NB_PLAYERS)) {
                            Log.i(TAG, "onConnectionInitiated: accepting new connection");
                            playerName = connectionInfo.getEndpointName();
                            connectionsClient
                                    .acceptConnection(endpointId, payloadCallback)
                                    .addOnFailureListener(
                                            new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "acceptConnection() failed.", e);
                                                }
                                            });
                        }
                        /* If game started and room not full
                         * host will check if it's a returning player, if tyes it's will accept connection */
                        else if ((gameStarted) && (listPlayer.size() < NB_PLAYERS)) {
                            for (int i = 0; i < playersInformations.size(); i++) {
                                if (playersInformations.get(i).getSecond().equals(connectionInfo.getEndpointName())) {
                                    returnPlayer = true;
                                    Log.i(TAG, "onConnectionInitiated: accepting connection of returning player");
                                    playerName = connectionInfo.getEndpointName();
                                    connectionsClient
                                            .acceptConnection(endpointId, payloadCallback)
                                            .addOnFailureListener(
                                                    new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w(TAG, "acceptConnection() failed.", e);
                                                        }
                                                    });
                                    break;
                                }
                            }
                            if (!returnPlayer) {
                                Log.i(TAG, "onConnectionInitiated: game started but rejecting connection");
                                connectionsClient
                                        .rejectConnection(endpointId)
                                        .addOnFailureListener(
                                                new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "rejectConnection() failed.", e);
                                                    }
                                                });
                            }
                        }
                        /* Reject connection if room is full */
                        else {
                            Log.i(TAG, "onConnectionInitiated: rejecting connection");
                            connectionsClient
                                    .rejectConnection(endpointId)
                                    .addOnFailureListener(
                                            new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "rejectConnection() failed.", e);
                                                }
                                            });
                        }
                    }
                    /* Accepting connection in any case for client */
                    else {
                        Log.i(TAG, "onConnectionInitiated: accepting connection to host");
                        playerName = playerUUID;
                        connectionsClient
                                .acceptConnection(endpointId, payloadCallback)
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "acceptConnection() failed.", e);
                                            }
                                        });
                    }
                }

                @Override
                public void onConnectionResult(@NonNull String endpointId, ConnectionResolution result) {
                    if (result.getStatus().isSuccess()) {
                        Log.i(TAG, "onConnectionResult: connection successful");

                        connectionsClient.stopDiscovery();
                        discovering = false;

                        listPlayer.add(new Pair<>(playerName, endpointId));
                        if(host){
                            AdminConnectionActivity.updateNbPlayers();
                            if(!gameStarted) {
                                playersInformations.add(new Triplet<Player, String, String>(null, playerName, endpointId));
                                Log.i(TAG, "onConnectionResult: add player");
                            }
                        }
                        else{
                            Log.i(TAG, "onConnectionResult: player");
                            PlayerConnexionActivity.setStatus("Connected");
                            try {
                                sendToClient(new TransferPackage<Player, String>(player, playerName), endpointId);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Log.i(TAG, "onConnectionResult: connection failed");
                    }
                }

                @Override
                public void onDisconnected(@NonNull String endpointId) {
                    if(host) {
                        for (int i = 0; i < listPlayer.size(); i++) {
                            if (endpointId.equals(listPlayer.get(i).second)) {
                                listPlayer.remove(i);
                                break;
                            }
                        }
                        if(!gameStarted){
                            for (int i = 0; i < playersInformations.size(); i++) {
                                if (endpointId.equals(playersInformations.get(i).getThird())) {
                                    playersInformations.remove(i);
                                    break;
                                }
                            }
                        }
                        AdminConnectionActivity.updateNbPlayers();
                    }
                    else {
                        PlayerConnexionActivity.setStatus("Disconnected");
                        if(currentPlayerView != null) {
                            currentPlayerView.finish();
                            Toast.makeText(appContext, "Vous avez été déconnecté de la partie, veuillez vous re-connecter en appuyant sur \"CHERCHER UN HOTE\"",
                                    Toast.LENGTH_LONG).show();
                        }
                        if(listPlayer.size() != 0)
                            listPlayer.clear();
                    }
                }
            };

    public NetworkHelper(Context c) {
        appContext = c;
        discovering = false;
        advertising = false;
        host = false;
        gameStarted = false;
        player = new Player(null);
        listPlayer = new ArrayList<>();
        playersInformations = new ArrayList<>();
        connectionsClient = Nearby.getConnectionsClient(c);
        playerUUID = UUIDHelper.id(appContext);
    }

    /** Finds an opponent to play the game with using Nearby Connections. */
    public void hostGame() {
        if (!discovering) {
            startAdvertising();
        }
    }

    /**
     * Launch discovery and set status attributes
     */
    public void searchGame() {
        if (!advertising) {
            startDiscovery();
        }
    }

    /** Disconnects from the opponent and reset the player list. */
    public void disconnect(String endpointId) {
        for (int i = 0; i<listPlayer.size(); i++)
        {
            if(endpointId.equals(listPlayer.get(i).second)){
                connectionsClient.disconnectFromEndpoint(listPlayer.get(i).second);
                listPlayer.remove(i);
                if(host) playersInformations.remove(i);
                break;
            }
        }
        if(!host)
            PlayerConnexionActivity.setStatus("Disconnected");
    }

    /** Disconnects from the opponent and reset the player list. */
    public void disconnectFromAllEndpoints() {
        connectionsClient.stopDiscovery();
        discovering = false;
        for (int i = 0; i<listPlayer.size(); i++)
        {
            connectionsClient.disconnectFromEndpoint(listPlayer.get(i).second);
        }
        if(!host) {
            playersInformations.clear();
            PlayerConnexionActivity.setStatus("Disconnected");
        }
        listPlayer.clear();
    }

    /**
     * Ends and disconnect from all endpoints
     */
    public void stopAll() {
        connectionsClient.stopAdvertising();
        connectionsClient.stopDiscovery();
        connectionsClient.stopAllEndpoints();
        listPlayer.clear();
        gameStarted = false;
        advertising = false;
        discovering = false;
        if(host) {
            playersInformations.clear();
        }
    }

    /** Starts looking for other players using Nearby Connections. */
    private void startDiscovery() {
        if(!discovering) {
            connectionsClient
                    .startDiscovery(
                            appContext.getPackageName(), endpointDiscoveryCallback,
                            new DiscoveryOptions.Builder().setStrategy(STRATEGY).build())
                    .addOnSuccessListener(
                            new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unusedResult) {
                                    discovering = true;
                                    Log.d(TAG, "Now discovering endpoint " + playerUUID);
                                    PlayerConnexionActivity.setStatus("Searching");
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    discovering = false;
                                    Log.w(TAG, "startAdvertising() failed.", e);
                                    PlayerConnexionActivity.setStatus("Disconnected");
                                }
                            });
        }
    }

    /** Broadcasts our presence using Nearby Connections so other players can find us. */
    private void startAdvertising() {
        if(!advertising) {
            connectionsClient
                    .startAdvertising(
                            playerUUID, appContext.getPackageName(), connectionLifecycleCallback,
                            new AdvertisingOptions.Builder().setStrategy(STRATEGY).build())
                    .addOnSuccessListener(
                            new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unusedResult) {
                                    advertising = true;
                                    Log.d(TAG, "Now advertising endpoint " + playerUUID);
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    advertising = false;
                                    Log.w(TAG, "startAdvertising() failed.", e);
                                }
                            });
        }
    }

    public static String[] getRequiredPermissions() {
        return REQUIRED_PERMISSIONS;
    }

    public static int getRequestCodeRequiredPermissions() {
        return REQUEST_CODE_REQUIRED_PERMISSIONS;
    }

    public List<Pair<String, String>> getListPlayer() {
        return listPlayer;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
    public Game getCurrentGame() {
        return currentGame;
    }

    public PlayerViewActivity getCurrentPlayerView() {
        return currentPlayerView;
    }

    public void setCurrentPlayerView(PlayerViewActivity currentPlayerView) {
        this.currentPlayerView = currentPlayerView;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static int getNbPlayers() {
        return NB_PLAYERS;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public boolean isAdvertising()
    {
        return advertising;
    }

    public boolean isDiscovering()
    {
        return discovering;
    }

    public boolean isHost()
    {
        return host;
    }

    /**
     * Send data to all client connected to the host
     * It serialize the object in parameter before sending it
     * @param o
     * @throws IOException
     */
    public void sendToAllClients(Object o) throws IOException {
        Payload data = Payload.fromBytes(SerializationHelper.serialize(o));
        for (int i = 0; i < listPlayer.size(); i++) {
            connectionsClient
                    .sendPayload(listPlayer.get(i).second, data)
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG,"sendPayload() failed.", e);
                                }
                            });
        }
    }

    /**
     * Send data to specific client connected to the host
     * It serialize the object in parameter before sending it
     * @param o
     * @param id
     * @throws IOException
     */
    public void sendToClient(Object o, String id) throws IOException {
        Payload data = Payload.fromBytes(SerializationHelper.serialize(o));
        connectionsClient
                .sendPayload(id, data)
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG,"sendPayload() failed.", e);
                            }
                        });
    }

    /**
     * Send random role of an ArrayList to all users connected to the host
     * It serialize the object in parameter before sending it
     * @param srcData
     * @throws IOException
     */
    public void sendRandomRoleToAllClients(ArrayList<Role> srcData) throws IOException {
        if(srcData.size() >= listPlayer.size()){
            int randomIndex = 0;
            Payload data;
            for (int i = 0; i < listPlayer.size(); i++) {
                data = Payload.fromBytes(SerializationHelper.serialize(srcData.get(i)));
                connectionsClient
                        .sendPayload(listPlayer.get(i).second, data)
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG,"sendPayload() failed.", e);
                                    }
                                });
            }
        }
    }

    private void updatePlayerView(){
        currentPlayerView.fillInfosView();
        currentPlayerView.colorBuildingBet();
        //currentPlayerView.resetColorRessources(); pas optimal. Peut être trop lourd en local.
        currentPlayerView.resetColorRessources();
        currentPlayerView.colorRessources();
    }

    private int randomIndex(int min, int max)
    {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void setNB_PLAYERS(int NB_PLAYERS) {
        this.NB_PLAYERS = NB_PLAYERS;
    }
}