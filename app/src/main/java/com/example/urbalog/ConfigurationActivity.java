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

    private Button modificationBuilding;
    private Button initBuilding;
    private Button addBuilding;
    private Button statistics;
    private Button addGame;
    private Button initStats;
    private Button resetDb;
    private Button exportCsv;
    private Button updateTimer;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        mContext = ConfigurationActivity.this;

        modificationBuilding = findViewById(R.id.modificationBuilding);
        initBuilding = findViewById(R.id.initBuilding);
        addBuilding = findViewById(R.id.addBuilding);
        statistics = findViewById(R.id.statistics);
        addGame = findViewById(R.id.addGame);
        initStats = findViewById(R.id.initStats);
        resetDb = findViewById(R.id.resetDb);
        exportCsv = findViewById(R.id.exportCsv);
        updateTimer = findViewById(R.id.updateTimer);

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

        addBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigurationActivity.this, AddBuildingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigurationActivity.this, StatsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /*
        addGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonStats.giveContext(ConfigurationActivity.this);
                ArrayList<Player> list = new ArrayList<Player>();
                Player un = new Player("Gab", 20, "Retraité", null);
                list.add(un);
                Player deux = new Player("Raph", 22, "Agriculteur, exploitant", null);
                list.add(deux);
                Player trois = new Player("Leo", 24, "Ouvrier qualifié", null);
                list.add(trois);
                Player quatre = new Player("Louis", 25, "Cadres et profession intellectuelle supérieure", null);
                list.add(quatre);
                Player cinq = new Player("Lucas", 26, "Sans emploi", null);
                list.add(cinq);
                Game game = new Game();
                game.setScoreAttractivite(2);
                game.setScoreFluidite(1);
                game.setScoreEnvironnemental(-4);
                JsonStats.writeGame(list, game);
                Toast.makeText(ConfigurationActivity.this, "partie ajoutée", Toast.LENGTH_LONG).show();
            }
        });*/

        initStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonStats.init(ConfigurationActivity.this);
                Toast.makeText(ConfigurationActivity.this, "Stats réinitialisés", Toast.LENGTH_LONG).show();
            }
        });

        resetDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminConnectionActivity.net.getDb().resetDB();
                Toast.makeText(ConfigurationActivity.this, "Base de donnée réinitialisée", Toast.LENGTH_LONG).show();
            }
        });

        exportCsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminConnectionActivity.net.getDb().exportDbToCSV();
                Toast.makeText(ConfigurationActivity.this, "Base de donnée exportée", Toast.LENGTH_LONG).show();
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



    }

    public void onBackPressed(){
        Intent intent = new Intent(ConfigurationActivity.this, AdminConnectionActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}