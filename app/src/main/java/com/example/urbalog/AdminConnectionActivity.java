package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.urbalog.Class.Building;
import com.example.urbalog.Json.JsonBuilding;

import java.util.ArrayList;

public class AdminConnectionActivity extends AppCompatActivity {

    private ArrayList<Building> buildings;
    private NetworkHelper net;
    private Button bHost;
    private Button bPlay;
    private Button bRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JsonBuilding.init(getApplicationContext());
        this.buildings = JsonBuilding.readBuilding();
        this.net = new NetworkHelper(getApplicationContext());

        this.bHost = (Button)findViewById(R.id.hostButton);
        this.bPlay = (Button)findViewById(R.id.playButton);
        this.bRefresh = (Button)findViewById(R.id.refreshButton);

        bHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net.hostGame();
            }
        });

        setContentView(R.layout.activity_admin_connection);
    }
}
