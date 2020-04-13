package com.example.urbalog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Player;
import com.example.urbalog.Json.JsonBuilding;
import com.example.urbalog.Json.JsonStats;

import java.util.ArrayList;

public class ConfigurationActivity extends AppCompatActivity {

    private Button statistics;
    private Button resetDb;
    private Button exportCsv;
    private Button updateTimer;
    private Button buildingConfig;
    private Button dbConfig;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        mContext = ConfigurationActivity.this;

        statistics = findViewById(R.id.statistics);
        resetDb = findViewById(R.id.resetDb);
        exportCsv = findViewById(R.id.exportCsv);
        updateTimer = findViewById(R.id.updateTimer);
        buildingConfig = findViewById(R.id.buildingButton);
        dbConfig = findViewById(R.id.dbButton);


        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigurationActivity.this, StatsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        

        updateTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigurationActivity.this, TimerUpdateActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buildingConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigurationActivity.this, BuildingConfigActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dbConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigurationActivity.this, DbConfigActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    public void onBackPressed(){
        Intent intent = new Intent(ConfigurationActivity.this, AdminConnectionActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}