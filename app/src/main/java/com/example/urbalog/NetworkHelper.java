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
import com.example.urbalog.Class.Duo;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.Player;
import com.example.urbalog.Class.Role;
import com.example.urbalog.Class.Signal;
import com.example.urbalog.Class.TransferPackage;
import com.example.urbalog.Class.Triplet;
import com.example.urbalog.Database.DatabaseHandler;
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
    private DatabaseHandler db;
    private CountDownTimerHandler timer;
    private CountDownTimerHandler gameTimer;

    private PlayerViewActivity currentPlayerView = null;
    private CityProgressionActivity currentAdminView = null;

    private boolean advertising;
    private boolean discovering;
    private boolean host;

    private static int NB_PLAYERS = 5;
    private static int NB_BUILDINGS = 3;
    private static int NB_BUILDINGS_PER_TURN = 2;
    private static String SERVER_DB_ADRESS = "";

    private int TURN_TIME = 60;
    private int GAME_TIME = 30;

    private Game currentGame;
    private boolean gameStarted;
    private Player player;
    private String endpointLast;

    private ArrayList<Pair<String, String>> listPlayer; // Pair array for connexion information storage of connected users
    private ArrayList<Triplet<Player, String, String>> playersInformations; // Pair array with Player instance of clients

    private ArrayList<Duo<String, Boolean>> playersVotes;
    private void createPlayersVotes(){
        playersVotes = new ArrayList<>();
        for (int i = 0; i < listPlayer.size(); i++) {
            playersVotes.add(new Duo<>(listPlayer.get(i).second, false));
        }
    }

    private int nextTurnVotes = 0;

    private final ConnectionsClient connectionsClient;
    public static final String TAG = "UrbalogGame"; // Tag for log
    private final String codeName = CodenameGenerator.generate();
    private final String playerUUID;

    private static final String[] REQUIRED_PERMISSIONS =
            new String[] {
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
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
                        Log.i(TAG, "Received Payload : " + dataReceived.toString());

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    /* Process data received based on this type and if it's host or not */
                    /* If it's a player device */
                    if (!host) {
                        if (dataReceived instanceof TransferPackage) {
                            switch (((TransferPackage) dataReceived).sig) {

                                /* If host have send Market, usually used for respond to player bet with updated state of Market */
                                case MARKET_RECEIVED:
                                    if (((Duo) ((TransferPackage) dataReceived).second).second instanceof Market) {
                                        try {
                                            sendToClient(new TransferPackage<Duo>(
                                                    Signal.UPDATE_PLAYER,
                                                    new Duo<>(playerUUID, player)), endpointId);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        currentGame.setMarket((Market)((Duo) ((TransferPackage) dataReceived).second).second);
                                        updatePlayerView();
                                        currentPlayerView.setButtonState(true);
                                        currentPlayerView.setEnabledBetButtons(true);
                                    }
                                    break;

                                /* If host have send Bet, no longer used for the moment */
                                case BET_RECEIVED:
                                    if (((Duo) ((TransferPackage) dataReceived).second).second instanceof Bet) {
                                        if (currentGame.equals((((Duo) ((TransferPackage) dataReceived).second).first))) {
                                            currentGame.majBet(((Bet) ((Duo) ((TransferPackage) dataReceived).second).second));
                                            if (currentPlayerView != null)
                                                updatePlayerView();
                                        }
                                    }
                                    break;

                                /* If host have send Game, used for returning player, when the game start or at new turn */
                                case GAME_RECEIVED:
                                    if (((TransferPackage) dataReceived).second instanceof Game) {
                                        currentGame = ((Game) ((TransferPackage) dataReceived).second);
                                        if (currentPlayerView != null) {
                                            updatePlayerView();
                                            currentPlayerView.resetTurnButton();
                                            currentPlayerView.resetColorRessources();
                                        } else
                                            gameStarted = true;
                                    }
                                    break;

                                /* If host have send Player, used on game start for role attribution */
                                case UPDATE_PLAYER:
                                    player = (Player)((TransferPackage) dataReceived).second;

                                    Intent intentPlayerView = new Intent(appContext, PlayerViewActivity.class);
                                    appContext.startActivity(intentPlayerView);
                                    break;

                                /* If host have send PLayer and Game instances for returning player */
                                case RESTART_GAME:
                                    if (((Duo) ((TransferPackage) dataReceived).second).first instanceof Game) {
                                        Log.i(TAG, "Restart game");
                                        currentGame = ((Game) ((Duo) ((TransferPackage) dataReceived).second).first);
                                        player = ((Player) ((Duo) ((TransferPackage) dataReceived).second).second);
                                        gameStarted = true;
                                        Intent myIntent = new Intent(appContext, PlayerViewActivity.class);
                                        appContext.startActivity(myIntent);
                                        if (currentPlayerView != null) {
                                            updatePlayerView();
                                        }
                                    }
                                    break;

                                /* Ask player to check if his goals are completed */
                                case CHECK_GOALS:
                                    if (player.checkGoals((ArrayList<Building>) ((TransferPackage) dataReceived).second)
                                            && (currentGame.getCity().getBuildings().size() + ((ArrayList<Building>) ((TransferPackage) dataReceived).second).size()) < NB_BUILDINGS) {

                                        final AlertDialog.Builder scoreDialog = new AlertDialog.Builder(getCurrentPlayerView());

                                        LayoutInflater inflater = getCurrentPlayerView().getLayoutInflater();

                                        scoreDialog.setView(inflater.inflate(R.layout.alert_dialog, null))
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
                                        sendToClient(new TransferPackage<Duo>(
                                                Signal.CHECK_GOALS,
                                                new Duo<>(playerUUID, player)), endpointId);
                                        player.resetFinancementRessource();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;

                                /* Report to player the end of the game */
                                case GAME_OVER:
                                    currentGame = (Game) ((TransferPackage) dataReceived).second;
                                    try {
                                        sendToClient(new TransferPackage<Duo>(
                                                Signal.UPDATE_PLAYER,
                                                new Duo<>(playerUUID, player)), endpointId);
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
                        else if(dataReceived instanceof Signal) {
                            switch ((Signal) dataReceived) {
                                case START_TIMER:
                                    timer = new CountDownTimerHandler(currentGame.getTurnDur() * 1000, new CountDownTimerHandler.TimerTickListener() {
                                        @Override
                                        public void onTick(long millisLeft) {
                                            // Unused
                                        }

                                        @Override
                                        public void onFinish() {
                                            try {
                                                sendToAllClients(Signal.NEXT_TURN);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onCancel() {

                                        }
                                    });
                                    timer.start();
                                    break;

                                case STOP_TIMER:
                                    timer.cancel();
                                    break;

                                case TOO_MUCH_BUILDINGS:
                                    showAlertBuildings();
                                    currentPlayerView.enableTurnButton();
                                    break;

                                case TOO_MUCH_TOTAL_BUILDINGS:
                                    showAlertBuildingsTotal();
                                    currentPlayerView.enableTurnButton();
                                    break;

                                default:
                                    break;
                            }
                        }
                    }
                    /* ----------------------- */
                    /* If it's the host device */
                    /* ----------------------- */
                    else {
                        if(dataReceived instanceof TransferPackage) {
                            switch(((TransferPackage) dataReceived).sig){

                                case MARKET_RECEIVED:
                                    if(((TransferPackage) dataReceived).second instanceof Duo) {
                                        if(((Duo)((TransferPackage) dataReceived).second).second instanceof Market)
                                            if (currentGame.equals(((Duo)((TransferPackage) dataReceived).second).first)) {
                                                currentGame.setMarket((Market)((Duo)((TransferPackage) dataReceived).second).second);
                                                try {
                                                    TransferPackage resend = new TransferPackage<Duo>(
                                                            Signal.MARKET_RECEIVED,
                                                            new Duo<>((Game) ((Duo) ((TransferPackage) dataReceived).second).first, (Market) ((Duo) ((TransferPackage) dataReceived).second).second));
                                                    sendToAllClients(resend);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                    }
                                    break;

                                case RETURN_PLAYER:
                                    for(int i = 0; i < playersInformations.size(); i++) {
                                        if(playersInformations.get(i).getSecond().equals(((TransferPackage)dataReceived).second)) {
                                            try {
                                                sendToClient(new TransferPackage<Duo>(
                                                        Signal.RESTART_GAME,
                                                        new Duo<>(currentGame, playersInformations.get(i).getFirst())), endpointId);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        }
                                    }
                                    break;

                                case NEW_CONNECTION:
                                    if(((TransferPackage) dataReceived).second instanceof Duo) {
                                        if (((Duo)((TransferPackage) dataReceived).second).first instanceof Player) {
                                            if (gameStarted) {
                                                Log.i(TAG, "gameStarted");
                                                for (int i = 0; i < playersInformations.size(); i++) {
                                                    if (playersInformations.get(i).getSecond().equals(((Duo)((TransferPackage) dataReceived).second).second)) {
                                                        try {
                                                            Log.i(TAG, "returning player");
                                                            sendToClient(new TransferPackage<Duo>(
                                                                    Signal.RESTART_GAME,
                                                                    new Duo<>(currentGame, playersInformations.get(i).getFirst())), endpointId);
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }
                                                        break;
                                                    }
                                                }
                                            } else {
                                                Log.i(TAG, "!gameStarted: ");
                                                for (int i = 0; i < playersInformations.size(); i++) {
                                                    if (playersInformations.get(i).getThird().equals(endpointId)) {
                                                        playersInformations.get(i).setFirst((Player)((Duo)((TransferPackage) dataReceived).second).first);
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    break;

                                case BET_RECEIVED:
                                    if(((TransferPackage) dataReceived).second instanceof Duo){
                                        if(currentGame.equals(((Duo)((TransferPackage) dataReceived).second).first))
                                        {
                                            currentGame.majBet((Bet)((Duo)((TransferPackage) dataReceived).second).second);
                                            try {
                                                sendToAllClients(new TransferPackage<Duo>(
                                                        Signal.MARKET_RECEIVED,
                                                        new Duo<>(currentGame, currentGame.getMarket())));
                                                logBet((Bet)((Duo)((TransferPackage) dataReceived).second).second);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                    break;

                                case UPDATE_PLAYER:
                                    for (int i = 0; i < playersInformations.size(); i++) {
                                        if (playersInformations.get(i).getSecond().equals(((Duo)((TransferPackage) dataReceived).second).first)){
                                            playersInformations.get(i).setFirst((Player)((Duo)((TransferPackage) dataReceived).second).second);
                                        }
                                    }
                                    break;

                                case CHECK_GOALS:
                                    for (int i = 0; i < playersInformations.size(); i++) {
                                        if (playersInformations.get(i).getSecond().equals(((Duo)((TransferPackage) dataReceived).second).first)){
                                            playersInformations.get(i).setFirst((Player)((Duo)((TransferPackage) dataReceived).second).second);
                                            db.updatePlayer(playersInformations.get(i).getFirst());
                                            playersInformations.get(i).getFirst().resetFinancementRessource();
                                        }
                                    }
                                    break;

                                case BACKUP_PLAYER:
                                    if(((TransferPackage) dataReceived).second instanceof Player){
                                        for (int i = 0; i < playersInformations.size(); i++) {
                                            if(playersInformations.get(i).getThird().equals(endpointId)){
                                                playersInformations.get(i).setFirst((Player)((TransferPackage) dataReceived).second);
                                                break;
                                            }
                                        }
                                    }
                                    break;

                                default:
                                    break;
                            }
                        }

                        else if(dataReceived instanceof Signal){
                            switch ((Signal) dataReceived)
                            {
                                /* Report to host a next turn vote */
                                case NEXT_TURN:
                                    for (int i = 0; i < playersVotes.size(); i++) {
                                        if(playersVotes.get(i).first.equals(endpointId)) {
                                            playersVotes.get(i).second = true;
                                            break;
                                        }
                                    }
                                    nextTurnVotes++;
                                    break;

                                /* Report to host the cancellation of a next turn vote */
                                case CANCEL_NEXT_TURN:
                                    for (int i = 0; i < playersVotes.size(); i++) {
                                        if(playersVotes.get(i).first.equals(endpointId)) {
                                            playersVotes.get(i).second = false;
                                            break;
                                        }
                                    }
                                    nextTurnVotes--;
                                    break;

                                default:
                                    break;
                            }
                            /* If all players have voted for next turn */
                            if(nextTurnVotes == NB_PLAYERS)
                            {
                                // Cancel turn timer for last player if exist
                                if(endpointLast != null) {
                                    for (int i = 0; i < playersVotes.size(); i++) {
                                        if (!playersVotes.get(i).second) {
                                            try {
                                                sendToClient(Signal.STOP_TIMER, endpointLast);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            endpointLast = null;
                                        }
                                    }
                                }

                                // Reset turns votes
                                createPlayersVotes();

                                /* Check market and build financed building */
                                ArrayList<Building> newBuildings = new ArrayList<>();
                                for(int i = 0; i < currentGame.getMarket().getBuildings().size(); i++) {
                                    if(currentGame.getMarket().getBuildings().get(i).isFilled()) {
                                        newBuildings.add(currentGame.getMarket().getBuildings().get(i));
                                    }
                                }

                                /* If players haven't build too much buildings, we process construction and next turn */
                                if(newBuildings.size() <= NB_BUILDINGS_PER_TURN) {
                                    /* Check if total buildings in city will not exceed maximum */
                                    if((currentGame.getCity().getBuildings().size() + newBuildings.size()) <= NB_BUILDINGS) {
                                        for (int i = 0; i < newBuildings.size(); i++) {
                                            currentGame.getCity().addBuilding(newBuildings.get(i));
                                            // Delete build buildings to deck
                                            currentGame.getMarket().deleteBuilding(newBuildings.get(i));
                                        }
                                        try {
                                            sendToAllClients(new TransferPackage<>(
                                                    Signal.CHECK_GOALS,
                                                    newBuildings));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        currentGame.updateAllGameScores();
                                        currentGame.addArrayGame();
                                        /* Check if the game is finish */
                                        // If not, refresh market and update game instance of players
                                        if (currentGame.getCity().getBuildings().size() < NB_BUILDINGS) {
                                            currentGame.refreshMarket();
                                            currentGame.incrTurn();
                                            try {
                                                sendToAllClients(new TransferPackage<>(
                                                        Signal.GAME_RECEIVED,
                                                        currentGame));
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            nextTurnVotes = 0;
                                        } else {
                                            stopGame();
                                        }
                                        if (currentAdminView != null)
                                            currentAdminView.updateView();
                                    }
                                    else{
                                        try {
                                            nextTurnVotes = 0;
                                            sendToAllClients(Signal.TOO_MUCH_TOTAL_BUILDINGS);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                /* Else we alert players */
                                else{
                                    try {
                                        nextTurnVotes = 0;
                                        sendToAllClients(Signal.TOO_MUCH_BUILDINGS);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            else if(nextTurnVotes == NB_PLAYERS-1 && NB_PLAYERS != 1){
                                // Launch turn timer for last player
                                for (int i = 0; i < playersVotes.size(); i++) {
                                    if(!playersVotes.get(i).second){
                                        endpointLast = playersVotes.get(i).first;
                                        try {
                                            sendToClient(Signal.START_TIMER, endpointLast);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            else{
                                // Cancel turn timer for last player if exist
                                if(endpointLast != null) {
                                    for (int i = 0; i < playersVotes.size(); i++) {
                                        if (!playersVotes.get(i).second) {
                                            try {
                                                sendToClient(Signal.STOP_TIMER, endpointLast);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                    endpointLast = null;
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
                                sendToClient(new TransferPackage<Duo>(
                                        Signal.NEW_CONNECTION,
                                        new Duo<>(player, playerName)), endpointId);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Log.i(TAG, "onConnectionResult: connection failed");
                        Log.i(TAG, "Status : " + result.getStatus().toString());
                        if(result.getStatus().getStatusMessage() != null)
                            Log.i(TAG, result.getStatus().getStatusMessage());
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

    public NetworkHelper(Context c, boolean mHost) {
        appContext = c;
        discovering = false;
        advertising = false;
        host = mHost;
        currentGame = new Game();
        gameStarted = false;
        player = new Player();
        listPlayer = new ArrayList<>();
        playersInformations = new ArrayList<>();
        connectionsClient = Nearby.getConnectionsClient(c);
        playerUUID = UUIDHelper.id(appContext);

        if(host) {
            db = new DatabaseHandler(appContext);
        }
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
                                    Log.w(TAG, "startDiscovery() failed.", e);
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

    public int getTURN_TIME() {
        return TURN_TIME;
    }

    public void setTURN_TIME(int time) {
        TURN_TIME = time;
        Log.i(TAG, "Turn set time: " + time);
        Log.i(TAG, "Turn set TURN: " + TURN_TIME);
    }

    public CityProgressionActivity getCurrentAdminView() {
        return currentAdminView;
    }

    public void setCurrentAdminView(CityProgressionActivity currentAdminView) {
        this.currentAdminView = currentAdminView;
    }

    public DatabaseHandler getDb() {
        return db;
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
    public void sendToAllClients(final Object o) throws IOException {
        Payload data = Payload.fromBytes(SerializationHelper.serialize(o));
        for (int i = 0; i < listPlayer.size(); i++) {
            connectionsClient
                    .sendPayload(listPlayer.get(i).second, data)
                    .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.i(TAG, "SendToAllClients Payload : " + o.toString());
                            }
                    })
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
    public void sendToClient(final Object o, final String id) throws IOException {
        Payload data = Payload.fromBytes(SerializationHelper.serialize(o));
        connectionsClient
                .sendPayload(id, data)
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.i(TAG, "SendToClient (" + id + ") Payload : " + o.toString());
                            }
                        })
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
                playersInformations.get(i).getFirst().setRole(srcData.get(i));
                db.insertPlayer(currentGame, playersInformations.get(i).getFirst());
                data = Payload.fromBytes(SerializationHelper.serialize(
                        new TransferPackage<>(
                                Signal.UPDATE_PLAYER,
                                playersInformations.get(i).getFirst()
                        )));
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
        NetworkHelper.NB_PLAYERS = NB_PLAYERS;
    }

    private void logBet(Bet bet){
        db.insertBet(bet, currentGame);
    }

    public void startGame(ArrayList<Role> roles){
        try {
            currentGame.setTurnDur(TURN_TIME);
            sendToAllClients(new TransferPackage<>(
                    Signal.GAME_RECEIVED,
                    currentGame)
            );
            db.insertGame(currentGame, NB_PLAYERS, NB_BUILDINGS);
            sendRandomRoleToAllClients(roles);
            createPlayersVotes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentGame.setGameDur(GAME_TIME);
        gameTimer = new CountDownTimerHandler(currentGame.getGameDur() * 1000, new CountDownTimerHandler.TimerTickListener() {
            @Override
            public void onTick(long millisLeft) {
                // Unused
            }

            @Override
            public void onFinish() {
                stopGame();
            }

            @Override
            public void onCancel() {

            }
        });
        gameTimer.start();

    }

    private void stopGame() {
        try {
            sendToAllClients(new TransferPackage<>(
                    Signal.GAME_OVER,
                    currentGame));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(timer != null)
            timer.cancel();
        gameTimer.cancel();
        db.updateGame(currentGame);
        gameStarted = false;
    }

    private void showAlertBuildings(){
        AlertDialog diaBox = new AlertDialog.Builder(currentPlayerView)
                .setTitle("Attention")
                .setMessage("Vous cherchez à constuire trop de bâtiments durant ce tour, veuillez limiter leur nombre à "+NB_BUILDINGS_PER_TURN+" maximum.")
                .setIcon(R.drawable.warning_icon)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                })
                .create();
        diaBox.show();
    }

    private void showAlertBuildingsTotal(){
        AlertDialog diaBox = new AlertDialog.Builder(currentPlayerView)
                .setTitle("Attention")
                .setMessage("Vous cherchez à constuire trop de bâtiments dans la ville, leur nombre pour votre ville ne peut excédé "+NB_BUILDINGS+".")
                .setIcon(R.drawable.warning_icon)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                })
                .create();
        diaBox.show();
    }
}