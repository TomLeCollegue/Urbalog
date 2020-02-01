package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.Role;
import com.example.urbalog.Json.JsonBuilding;
import com.example.urbalog.Json.JsonRole;

import java.io.IOException;
import java.util.ArrayList;

public class AdminConnectionActivity extends AppCompatActivity {
    public static NetworkHelper net; // Temporary until class can be parcelable and send in intent

    private ArrayList<Building> buildings;
    private ArrayList<Role> roles;
    private Button bHost;
    private Button bPlay;
    private Button bRefresh;
    private Button bStop;
    private static TextView tPlayers;
    private TextView tStatus;
    private Game currentGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JsonBuilding.init(getApplicationContext());
        this.buildings = JsonBuilding.readBuilding();

        JsonRole.init(getApplicationContext());
        this.roles = JsonRole.readRole();
        /*this.roles.add(new Role("Camionneur"));
        this.roles.add(new Role("Habitant"));
        this.roles.add(new Role("Commerçant"));
        this.roles.add(new Role("Maire"));
        this.roles.add(new Role("Dev de génie"));
        this.roles.add(new Role("Expert pluridiciplinaire"));*/

        net = new NetworkHelper(this);
        setContentView(R.layout.activity_admin_connection);

        this.bHost = (Button)findViewById(R.id.hostButton);
        this.bPlay = (Button)findViewById(R.id.playButton);
        this.bRefresh = (Button)findViewById(R.id.refreshButton);
        this.bStop = (Button)findViewById(R.id.stopButton);
        tPlayers = (TextView) findViewById(R.id.nbPlayer);
        this.tStatus = (TextView) findViewById(R.id.statusText);

        bHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net.hostGame(getCurrentFocus());
                updateStatus("Searching...");
            }
        });
        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(net.getListPlayer().size()+3 == 5) {
                    currentGame = new Game();
                    net.setCurrentGame(currentGame);
                    currentGame.setMarket(new Market());
                    try {
                        net.sendToAllClients(currentGame);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    randomRoleAssignment();
                }
            }
        });
        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net.stop();
                updateStatus("Disconnected");
                updateNbPlayers(0);
            }
        });

    }

    public static void updateNbPlayers(int nb)
    {
        tPlayers.setText(nb+"/5");
    }

    public void updateStatus(String s)
    {
        tStatus.setText(s);
    }

    public void randomRoleAssignment() {
        try {
            net.sendRandomRoleToAllClients(roles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
