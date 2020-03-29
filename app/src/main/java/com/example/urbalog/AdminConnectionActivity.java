package com.example.urbalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.Role;
import com.example.urbalog.Class.Signal;
import com.example.urbalog.Class.TransferPackage;
import com.example.urbalog.Json.JsonBuilding;
import com.example.urbalog.Json.JsonRole;
import com.example.urbalog.Json.JsonStats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private Spinner spinnerPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JsonBuilding.init(getApplicationContext());
        JsonRole.init(getApplicationContext());
        JsonStats.init(getApplicationContext());
        this.roles = JsonRole.readRole();

        net = new NetworkHelper(this, true);
        net.setHost(true);
        setContentView(R.layout.activity_admin_connection);

        this.bHost = (Button)findViewById(R.id.hostButton);
        this.bPlay = (Button)findViewById(R.id.playButton);
        this.configurationButton = (Button)findViewById(R.id.configurationButton);
        this.bStop = (Button)findViewById(R.id.stopButton);
        tPlayers = (TextView) findViewById(R.id.nbPlayer);
        this.tStatus = (TextView) findViewById(R.id.statusText);

        spinnerPlayer = (Spinner) findViewById(R.id.spinnerNBplayer);

        List<Integer> ListNB = new ArrayList<>();
        ListNB.add(1);
        ListNB.add(2);
        ListNB.add(3);
        ListNB.add(4);
        ListNB.add(5);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, ListNB);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPlayer.setAdapter(adapter);
        spinnerPlayer.setSelection(4);

        spinnerPlayer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                net.setNB_PLAYERS((Integer)spinnerPlayer.getSelectedItem());
                updateNbPlayers();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        bHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurationButton.setEnabled(false);
                net.hostGame();
                updateStatus("Searching...");
            }
        });
        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(net.getListPlayer().size() == NetworkHelper.getNbPlayers()) {
                    bPlay.setEnabled(false);
                    configurationButton.setEnabled(false);
                    net.setGameStarted(true);
                    currentGame = new Game();
                    currentGame.setMarket(new Market());
                    net.setCurrentGame(currentGame);
                    net.startGame(roles);
                }
            }
        });
        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurationButton.setEnabled(true);
                bPlay.setEnabled(true);
                net.stopAll();
                updateStatus("Disconnected");
                updateNbPlayers();
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

    public static void updateNbPlayers()
    {
        tPlayers.setText(net.getListPlayer().size()+"/"+ net.getNbPlayers());
    }

    public void updateStatus(String s)
    {
        tStatus.setText(s);
    }
}
