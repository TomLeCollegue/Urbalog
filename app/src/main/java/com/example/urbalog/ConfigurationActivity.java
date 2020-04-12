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
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        mContext = ConfigurationActivity.this;

        modificationBuilding = (Button) findViewById(R.id.modificationBuilding);
        initBuilding = (Button) findViewById(R.id.initBuilding);
        addBuilding = (Button) findViewById(R.id.addBuilding);
        statistics = (Button) findViewById(R.id.statistics);
        addGame = (Button) findViewById(R.id.addGame);
        initStats = (Button) findViewById(R.id.initStats);

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
                Toast.makeText(ConfigurationActivity.this, "Stats réinitialisé", Toast.LENGTH_LONG).show();
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