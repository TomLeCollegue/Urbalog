package com.example.urbalog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.urbalog.Json.JsonBuilding;
import com.example.urbalog.Json.JsonRole;
import com.example.urbalog.Json.JsonStats;

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
    private Button lancerVue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JsonBuilding.init(getApplicationContext());
        JsonRole.init(getApplicationContext());
        JsonStats.init(getApplicationContext());
        this.roles = JsonRole.readRole();

        if(net == null) {
            net = new NetworkHelper(this, true);
            net.setHost(true);
        }
        setContentView(R.layout.activity_admin_connection);

        this.bHost = findViewById(R.id.hostButton);
        this.bPlay = findViewById(R.id.playButton);
        this.configurationButton = findViewById(R.id.configurationButton);
        this.bStop = findViewById(R.id.stopButton);
        tPlayers = findViewById(R.id.nbPlayer);
        this.tStatus = findViewById(R.id.statusText);
        this.lancerVue = findViewById(R.id.vue_admin);

        spinnerPlayer = findViewById(R.id.spinnerNBplayer);

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
                    Log.i("Urbalog", "Turn game: " + net.getTURN_TIME());
                    bPlay.setEnabled(false);
                    configurationButton.setEnabled(false);
                    net.setGameStarted(true);
                    currentGame = net.getCurrentGame();
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
                net = new NetworkHelper(getApplicationContext(), true);
                updateStatus("Disconnected");
                updateNbPlayers();
            }
        });

        configurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminConnectionActivity.this, ConfigurationActivity.class);
                startActivity(intent);
            }
        });


        lancerVue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cityIntent = new Intent(AdminConnectionActivity.this, CityProgressionActivity.class);
                startActivity(cityIntent);
            }
        });

    }

    public static void updateNbPlayers()
    {
        tPlayers.setText(net.getListPlayer().size()+"/"+ NetworkHelper.getNbPlayers());
    }

    public void updateStatus(String s)
    {
        tStatus.setText(s);
    }
}
