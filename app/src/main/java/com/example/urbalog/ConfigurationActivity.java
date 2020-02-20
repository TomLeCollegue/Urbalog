package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.urbalog.Json.JsonBuilding;

public class ConfigurationActivity extends AppCompatActivity {

    private Button modificationBuilding;
    private Button initBuilding;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        mContext = ConfigurationActivity.this;

        modificationBuilding = (Button) findViewById(R.id.modificationBuilding);
        initBuilding = (Button) findViewById(R.id.initBuilding);

        modificationBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigurationActivity.this, ListBuildingsActivity.class);
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


    }

    public void onBackPressed(){
        Intent intent = new Intent(ConfigurationActivity.this, AdminConnectionActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}