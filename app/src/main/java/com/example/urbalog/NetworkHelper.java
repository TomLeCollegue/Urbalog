package com.example.urbalog;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.urbalog.CodenameGenerator;
import com.example.urbalog.MainActivity;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import com.google.android.gms.nearby.connection.Strategy;

import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class NetworkHelper {
    private Context c;
    private boolean advertising;
    private boolean discovering;

    private List<Pair<String, String>> listPlayer;

    private ConnectionsClient connectionsClient;
    private static final String TAG = "POC_nearby";
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

    private static final Strategy STRATEGY = Strategy.P2P_STAR;

    // Callbacks for receiving payloads
    private final PayloadCallback payloadCallback =
            new PayloadCallback() {
                @Override
                public void onPayloadReceived(String endpointId, Payload payload) {

                }

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
                public void onEndpointLost(String endpointId) {}
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
                        //connectionsClient.stopAdvertising();

                        listPlayer.add(new Pair<>(playerName, endpointId));
                        MainActivity.setStatusText("Connected : "+listPlayer.size()+" players");
                        // setButtonState(true);
                    } else {
                        Log.i(TAG, "onConnectionResult: connection failed");
                    }
                }

                @Override
                public void onDisconnected(String endpointId) {
                    for (int i = 0; i<listPlayer.size(); i++)
                    {
                        if(listPlayer.get(i).second == endpointId) {
                            listPlayer.remove(i);
                        }
                    }
                    MainActivity.setStatusText("Connected : "+listPlayer.size()+" players");
                }
            };

    public NetworkHelper(Context c) {
        this.c = c;
        discovering = false;
        advertising = false;
        listPlayer = new ArrayList<>();
        connectionsClient = Nearby.getConnectionsClient(c);
    }

    public void stop() {
        connectionsClient.stopAllEndpoints();
    }

    /** Finds an opponent to play the game with using Nearby Connections. */
    public void hostGame(View view) {
        if (discovering != true) {
            startAdvertising();
            advertising = true;
            MainActivity.setStatusText("Searching");
        }
    }

    public void searchGame(View view) {
        if (advertising != true)
        {
            startDiscovery();
            discovering = true;
            MainActivity.setStatusText("Searching");
        }
    }

    /** Disconnects from the opponent and reset the UI. */
    public void disconnect(View view) {
        for (int i = 0; i<listPlayer.size(); i++)
        {
            connectionsClient.disconnectFromEndpoint(listPlayer.get(i).second);
        }
        MainActivity.setStatusText("Disconnected");
        listPlayer.clear();
    }

    /** Starts looking for other players using Nearby Connections. */
    private void startDiscovery() {
        // Note: Discovery may fail. To keep this demo simple, we don't handle failures.
        connectionsClient.startDiscovery(
                c.getPackageName(), endpointDiscoveryCallback,
                new DiscoveryOptions.Builder().setStrategy(STRATEGY).build());
    }

    /** Broadcasts our presence using Nearby Connections so other players can find us. */
    private void startAdvertising() {
        // Note: Advertising may fail. To keep this demo simple, we don't handle failures.
        connectionsClient.startAdvertising(
                codeName, c.getPackageName(), connectionLifecycleCallback,
                new AdvertisingOptions.Builder().setStrategy(STRATEGY).build());
    }

    public boolean isAdvertising()
    {
        return advertising;
    }

    public boolean isDiscovering()
    {
        return discovering;
    }
}