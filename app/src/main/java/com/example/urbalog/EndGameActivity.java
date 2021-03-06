package com.example.urbalog;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbalog.Adapter.BuildingBuiltAdapter;
import com.example.urbalog.Class.Building;
import com.example.urbalog.EndGameStats.StatsScorePerRoundActivity;

import java.util.ArrayList;

public class EndGameActivity extends AppCompatActivity {

    private ArrayList<Building> buildings = new ArrayList<Building>();
    private RecyclerView rv1;
    private TextView textScorePlayer;

    private TextView textAttractivityScore;
    private TextView textEnvironmentScore;
    private TextView textFluidityScore;

    private Button logisticButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        MainActivity.net.setCurrentEndView(this);

        buildings = MainActivity.net.getCurrentGame().getCity().getBuildings();

        textScorePlayer = findViewById(R.id.text_score_player);

        textAttractivityScore = findViewById(R.id.attractivity_score);
        textEnvironmentScore = findViewById(R.id.environment_score);
        textFluidityScore = findViewById(R.id.fluidity_score);

        logisticButton = findViewById(R.id.logisticButton);

        rv1 = findViewById(R.id.recyclerListBuildingBuilt);
        rv1.setLayoutManager(new LinearLayoutManager(EndGameActivity.this, LinearLayoutManager.VERTICAL, false));

        BuildingBuiltAdapter MyAdapter= new BuildingBuiltAdapter(buildings);
        rv1.setAdapter(MyAdapter);

        textScorePlayer.setText("Score : " + MainActivity.net.getPlayer().getScore());

        textAttractivityScore.setText(String.valueOf(MainActivity.net.getCurrentGame().getScoreAttractivite()));
        textEnvironmentScore.setText(String.valueOf(MainActivity.net.getCurrentGame().getScoreEnvironnemental()));
        textFluidityScore.setText(String.valueOf(MainActivity.net.getCurrentGame().getScoreFluidite()));

        if (MainActivity.net.isTimeOver()) {
            AlertDialog diaBox = MainActivity.net.showMessage("Temps écoulé !",
                    "Le temps est écoulé et vous n'avez malheuresement pas eu le temps de contruire votre ville en entier...\n" +
                            "La prochaine fois gardez un oeil sur le temps restant.",
                    this);
            diaBox.show();
        }
    }

    public void scoreLog(View view) {
        Intent MyIntent = new Intent(EndGameActivity.this, LogisticActivity.class);
        startActivity(MyIntent);
    }

    public void statsScore(View view) {
        Intent MyIntent = new Intent(EndGameActivity.this, StatsScorePerRoundActivity.class);
        startActivity(MyIntent);
    }
}
