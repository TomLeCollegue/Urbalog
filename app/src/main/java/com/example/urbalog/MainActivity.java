package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Json.JsonBuilding;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Building> listeBuilding = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonBuilding.init(this);
    }

    public void changeActivityPlayer(View view) {
        Intent myIntent = new Intent(this, playerConnectionActivity.class);
        startActivity(myIntent);
    }


    public void changeActivityAdmin(View view) {
        Intent myIntent = new Intent(this, adminConnectionActivity.class);
        startActivity(myIntent);
    }
}
