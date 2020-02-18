package com.example.urbalog;

import android.Manifest;
import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.example.urbalog.Class.Bet;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.Player;
import com.example.urbalog.Class.Role;
import com.example.urbalog.Class.Signal;
import com.example.urbalog.Class.TransferPackage;
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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private static int NB_PLAYERS;

    private Game currentGame;
    private Player player;

    private List<Pair<String, String>> listPlayer; // Pair array for connexion information storage of connected users
    private int nextTurnVotes = 0;

    private ConnectionsClient connectionsClient;
    private static final String TAG = "UrbalogGame"; // Tag for log
    private final String codeName = CodenameGenerator.generate();

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
                @Override
                public void onPayloadReceived(String endpointId, Payload payload) {

                    /* Deserialization of incoming payload */
                    Object dataReceived = new Object();
                    try {
                        dataReceived = SerializationHelper.deserialize(payload.asBytes());
                        Log.d(TAG, "sendToAllClients: "+ dataReceived.toString());

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    /* Process data received based on this type and if it's host or not */
                    if(!host){
                        if(dataReceived instanceof TransferPackage){
                            if(((TransferPackage) dataReceived).second instanceof Market)
                            {
                                currentGame.setMarket(((Market) ((TransferPackage) dataReceived).second));
                                currentPlayerView.fillInfosView();
                            }
                            else if(((TransferPackage) dataReceived).second instanceof Bet){
                                if(currentGame.equals(((Game) ((TransferPackage) dataReceived).first)))
                                {
                                    currentGame.majBet(((Bet) ((TransferPackage) dataReceived).second));
                                    if(currentPlayerView != null)
                                    {
                                        currentPlayerView.fillInfosView();
                                    }
                                }
                            }
                            else if(((TransferPackage) dataReceived).first instanceof Signal){
                               switch((Signal)((TransferPackage) dataReceived).first)
                               {
                                   case CHECK_GOALS:
                                       player.checkGoals((ArrayList<Building>)((TransferPackage) dataReceived).second);
                                       break;
                               }
                            }
                        }
                        else if(dataReceived instanceof Game){
                            currentGame = (Game) dataReceived;
                            if(currentPlayerView != null) {
                                currentPlayerView.fillInfosView();
                                currentPlayerView.resetTurnButton();
                            }
                        }
                        else if(dataReceived instanceof Role){
                            player = new Player((Role) dataReceived);
                            Intent myIntent = new Intent(appContext, PlayerViewActivity.class);
                            appContext.startActivity(myIntent);
                        }
                    }
                    else
                    {
                        if(dataReceived instanceof TransferPackage){
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
                            }
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
                        else if(dataReceived instanceof Signal)
                        {
                            switch ((Signal) dataReceived)
                            {
                                case NEXT_TURN:
                                    nextTurnVotes++;
                                    break;

                                case CANCEL_NEXT_TURN:
                                    nextTurnVotes--;
                                    break;

                                default:
                                    break;
                            }
                            if(nextTurnVotes == NB_PLAYERS)
                            {
                                ArrayList<Building> newBuildings = new ArrayList<Building>();
                                for(int i = 0; i < currentGame.getMarket().getBuildings().size(); i++) {
                                    if(currentGame.getMarket().getBuildings().get(i).isFilled())
                                        currentGame.getCity().addBuilding(currentGame.getMarket().getBuildings().get(i));
                                        newBuildings.add(currentGame.getMarket().getBuildings().get(i));
                                }
                                try {
                                    sendToAllClients(new TransferPackage<Signal, ArrayList<Building>>(Signal.CHECK_GOALS, newBuildings));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if(currentGame.getCity().getBuildings().size() < 6) {
                                    currentGame.refreshMarket();
                                    currentGame.incrTurn();
                                    try {
                                        sendToAllClients(currentGame);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    nextTurnVotes = 0;
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
                public void onPayloadTransferUpdate(String endpointId, PayloadTransferUpdate update) {

                }
            };

    // Callbacks for finding other devices
    private final EndpointDiscoveryCallback endpointDiscoveryCallback =
            new EndpointDiscoveryCallback() {
                @Override
                public void onEndpointFound(String endpointId, DiscoveredEndpointInfo info) {
                    Log.i(TAG, "onEndpointFound: endpoint found, connecting");
                    connectionsClient.requestConnection(codeName, endpointId, connectionLifecycleCallback);
                }

                @Override
                public void onEndpointLost(String endpointId) {
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
                public void onConnectionInitiated(String endpointId, ConnectionInfo connectionInfo) {
                    Log.i(TAG, "onConnectionInitiated: accepting connection");
                    connectionsClient.acceptConnection(endpointId, payloadCallback);
                    playerName = connectionInfo.getEndpointName();
                }

                @Override
                public void onConnectionResult(String endpointId, ConnectionResolution result) {
                    if (result.getStatus().isSuccess()) {
                        Log.i(TAG, "onConnectionResult: connection successful");

                        connectionsClient.stopDiscovery();

                        listPlayer.add(new Pair<>(playerName, endpointId));
                        if(host){
                            AdminConnectionActivity.updateNbPlayers(listPlayer.size());
                        }
                        else{
                            PlayerConnexionActivity.setStatus("Connected");
                        }
                    } else {
                        Log.i(TAG, "onConnectionResult: connection failed");
                    }
                }

                @Override
                public void onDisconnected(String endpointId) {
                    if(host) {
                        for (int i = 0; i < listPlayer.size(); i++) {
                            if (endpointId.equals(listPlayer.get(i).second)) {
                                listPlayer.remove(i);
                                break;
                            }

                        }
                        AdminConnectionActivity.updateNbPlayers(listPlayer.size());
                    }
                    else {
                        PlayerConnexionActivity.setStatus("Disconnected");
                    }
                }
            };

    public NetworkHelper(Context c) {
        appContext = c;
        NB_PLAYERS = 2;
        discovering = false;
        advertising = false;
        host = false;
        listPlayer = new ArrayList<>();
        connectionsClient = Nearby.getConnectionsClient(c);
    }

    /**
     * Ends and disconnect from all endpoints
     */
    public void stop() {
        connectionsClient.stopAllEndpoints();
        listPlayer.clear();
        if(host)
            connectionsClient.stopAdvertising();
        else
            connectionsClient.stopDiscovery();
    }

    /** Finds an opponent to play the game with using Nearby Connections. */
    public void hostGame(View view) {
        if (!discovering) {
            startAdvertising();
            advertising = true;
            host = true;
        }
    }

    /**
     * Launch discovery and set status attributes
     * @param view
     */
    public void searchGame(View view) {
        if (!advertising)
        {
            startDiscovery();
            discovering = true;
            host = false;
            PlayerConnexionActivity.setStatus("Searching");
        }
    }

    /** Disconnects from the opponent and reset the player list. */
    public void disconnect(View view) {
        for (int i = 0; i<listPlayer.size(); i++)
        {
            connectionsClient.disconnectFromEndpoint(listPlayer.get(i).second);
        }
        if(!host)
            PlayerConnexionActivity.setStatus("Disconnected");
        listPlayer.clear();
        host = false;
    }

    /** Starts looking for other players using Nearby Connections. */
    private void startDiscovery() {
        connectionsClient.startDiscovery(
                appContext.getPackageName(), endpointDiscoveryCallback,
                new DiscoveryOptions.Builder().setStrategy(STRATEGY).build());
    }

    /** Broadcasts our presence using Nearby Connections so other players can find us. */
    private void startAdvertising() {
        connectionsClient.startAdvertising(
                codeName, appContext.getPackageName(), connectionLifecycleCallback,
                new AdvertisingOptions.Builder().setStrategy(STRATEGY).build());
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

    public static void setNbPlayers(int nbPlayers) {
        NB_PLAYERS = nbPlayers;
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
            Nearby.getConnectionsClient(appContext).sendPayload(listPlayer.get(i).second, data);
        }
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
                Nearby.getConnectionsClient(appContext).sendPayload(listPlayer.get(i).second, data);
            }
        }
    }

    private int randomIndex(int min, int max)
    {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}