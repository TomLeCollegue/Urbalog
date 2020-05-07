package com.example.urbalog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urbalog.Json.JsonBuilding;

public class BuildingConfigActivity extends AppCompatActivity {

    private Button modificationBuilding;
    private Button initBuilding;
    private Button addBuilding;

    // Hugo H
    private Button changeNbBuildingPerTurn;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_config);

        mContext = BuildingConfigActivity.this;

        modificationBuilding = findViewById(R.id.modificationBuilding);
        initBuilding = findViewById(R.id.initBuilding);
        addBuilding = findViewById(R.id.addBuilding);

        changeNbBuildingPerTurn = findViewById(R.id.nbBuildingMaxPerTurn);

        modificationBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingConfigActivity.this, ListBuildingsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        initBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonBuilding.recreate(mContext);
                Toast.makeText(mContext, "Les batiments on été réinitialisé", Toast.LENGTH_LONG).show();
            }
        });

        addBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingConfigActivity.this, AddBuildingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        changeNbBuildingPerTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingConfigActivity.this, ChangeNbBuildingPerTurn.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(BuildingConfigActivity.this, ConfigurationActivity.class);
        startActivity(intent);
        finish();
    }
}
