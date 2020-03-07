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

    private ArrayList<Role> roles;
    private Button bHost;
    private Button bPlay;
    private Button configurationButton;
    private Button bStop;
    private static TextView tPlayers;
    private TextView tStatus;
    private Game currentGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JsonBuilding.init(getApplicationContext());
        JsonRole.init(getApplicationContext());
        this.roles = JsonRole.readRole();

        net = new NetworkHelper(this);
        net.setHost(true);
        setContentView(R.layout.activity_admin_connection);

        this.bHost = (Button)findViewById(R.id.hostButton);
        this.bPlay = (Button)findViewById(R.id.playButton);
        this.configurationButton = (Button)findViewById(R.id.configurationButton);
        this.bStop = (Button)findViewById(R.id.stopButton);
        tPlayers = (TextView) findViewById(R.id.nbPlayer);
        this.tStatus = (TextView) findViewById(R.id.statusText);

        bHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurationButton.setEnabled(false);
                net.hostGame(getCurrentFocus());
                updateStatus("Searching...");
            }
        });
        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurationButton.setEnabled(false);
                if(net.getListPlayer().size() == NetworkHelper.getNbPlayers()) {
                    net.setGameStarted(true);
                    currentGame = new Game();
                    currentGame.setMarket(new Market());
                    net.setCurrentGame(currentGame);
                    try {
                        net.sendToAllClients(currentGame);
                        randomRoleAssignment();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                bPlay.setEnabled(false);
            }
        });
        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurationButton.setEnabled(true);
                bPlay.setEnabled(true);
                net.stopAll();
                updateStatus("Disconnected");
                updateNbPlayers(0);
            }
        });

        configurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminConnectionActivity.this, ConfigurationActivity.class);
                startActivity(intent);
                finish();
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
