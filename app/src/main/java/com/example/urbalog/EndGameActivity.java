package com.example.urbalog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
        PlayerConnexionActivity.net.setCurrentEndView(this);

        buildings = PlayerConnexionActivity.net.getCurrentGame().getCity().getBuildings();

        textScorePlayer = findViewById(R.id.text_score_player);

        textAttractivityScore = findViewById(R.id.attractivity_score);
        textEnvironmentScore = findViewById(R.id.environment_score);
        textFluidityScore = findViewById(R.id.fluidity_score);

        logisticButton = findViewById(R.id.logisticButton);

        rv1 = findViewById(R.id.recyclerListBuildingBuilt);
        rv1.setLayoutManager(new LinearLayoutManager(EndGameActivity.this, LinearLayoutManager.VERTICAL, false));

        BuildingBuiltAdapter MyAdapter= new BuildingBuiltAdapter(buildings);
        rv1.setAdapter(MyAdapter);

        textScorePlayer.setText("Score : " + PlayerConnexionActivity.net.getPlayer().getScore());

        textAttractivityScore.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreAttractivite()));
        textEnvironmentScore.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreEnvironnemental()));
        textFluidityScore.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreFluidite()));

        if (PlayerConnexionActivity.net.isTimeOver()) {
            AlertDialog diaBox = PlayerConnexionActivity.net.showMessage("Temps écoulé !",
                    "Le temps est écoulé et vous n'avez malheuresement pas eu le temps de contruire votre ville en entier...\n" +
                            "La prochaine fois gardez un oeil sur le temps restant.",
                    this);
            diaBox.show();
        }
    }

    public void scoreLog(View view) {
        Log.d("debug", "Listener");
        Intent MyIntent = new Intent(EndGameActivity.this, LogisticActivity.class);
        Log.d("debug", "Before startActivity");
        startActivity(MyIntent);
    }

    public void statsScore(View view) {
        Intent MyIntent = new Intent(EndGameActivity.this, StatsScorePerRoundActivity.class);
        startActivity(MyIntent);
    }
}
