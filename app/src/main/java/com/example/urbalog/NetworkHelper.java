package com.example.urbalog;

import android.Manifest;
import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.example.urbalog.Class.Bet;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.Player;
import com.example.urbalog.Class.Role;
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
import java.util.concurrent.ThreadLocalRandom;

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

    private Game currentGame;
    private Player player;

    private List<Pair<String, String>> listPlayer; // Pair array for connexion information storage of connected users

    private ConnectionsClient connectionsClient;
    private static final String TAG = "UrbalogGame"; // Tag for log
    private final String codeName = CodenameGenerator.generate();

    private Object dataReceived;
    private static final String[] REQUIRED_PERMISSIONS =
            new String[] {
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            };

    private static final int REQUEST_CODE_REQUIRED_PERMISSIONS = 1;

    private static final Strategy STRATEGY = Strategy.P2P_CLUSTER; // Nearby connection strategie for data sending

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
                    dataReceived = new Object();
                    try {
                        dataReceived = SerializationHelper.deserialize(payload.asBytes());
                        Log.d(TAG, "sendToAllClients: "+dataReceived.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    /* Process data received based on this type and if it's host or not */
                    if(!host){
                        if(dataReceived instanceof TransferPackage){
                            if(((TransferPackage) dataReceived).second instanceof Market)
                            {
                                if(currentGame.equals(((Game) ((TransferPackage) dataReceived).first))) {
                                    currentGame.refreshMarket(((Market) ((TransferPackage) dataReceived).second));
                                }
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
                        }
                        else if(dataReceived instanceof Game){
                            currentGame = (Game)dataReceived;
                            Intent myIntent = new Intent(appContext, PlayerViewActivity.class);
                            appContext.startActivity(myIntent);
                        }
                        else if(dataReceived instanceof Role){
                            player = new Player((Role)dataReceived);
                        }
                    }
                    else if(host)
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
                                        sendToAllClients(dataReceived);
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
                        if(listPlayer.get(i).second == endpointId) {
                            listPlayer.remove(i);
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
                            if (endpointId.equals(listPlayer.get(i).second))
                                listPlayer.remove(i);
                        }
                        AdminConnectionActivity.updateNbPlayers(listPlayer.size());
                        if(listPlayer.size() == 0){
                            stop();
                        }
                    }
                    else {
                        PlayerConnexionActivity.setStatus("Disconnected");
                    }
                }
            };

    public NetworkHelper(Context c) {
        appContext = c;
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
        if (discovering != true) {
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
        if (advertising != true)
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
            for (int i = 0; i < listPlayer.size(); i++) {
                int randomIndex = ThreadLocalRandom.current().nextInt(0, srcData.size());
                Payload data = Payload.fromBytes(SerializationHelper.serialize(srcData.get(randomIndex)));
                srcData.remove(randomIndex);
                Nearby.getConnectionsClient(appContext).sendPayload(listPlayer.get(i).second, data);
            }
        }
    }
}