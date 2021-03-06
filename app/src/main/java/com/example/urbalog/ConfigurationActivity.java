package com.example.urbalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigurationActivity extends AppCompatActivity {

    private Button statistics;
    private Button updateTimer;
    private Button buildingConfig;
    private Button dbConfig;
    private Button roleConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        statistics = findViewById(R.id.statistics);
        updateTimer = findViewById(R.id.updateTimer);
        buildingConfig = findViewById(R.id.buildingButton);
        dbConfig = findViewById(R.id.dbButton);
        roleConfig = findViewById(R.id.roleButton);


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

        roleConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigurationActivity.this, RoleConfigActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    public void onBackPressed(){
        finish();
    }
}