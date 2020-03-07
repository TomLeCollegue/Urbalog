package com.example.urbalog;

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

        buildings = PlayerConnexionActivity.net.getCurrentGame().getCity().getBuildings();

        textScorePlayer = (TextView) findViewById(R.id.text_score_player);

        textAttractivityScore = (TextView) findViewById(R.id.attractivity_score);
        textEnvironmentScore = (TextView) findViewById(R.id.environment_score);
        textFluidityScore = (TextView) findViewById(R.id.fluidity_score);

        logisticButton = (Button) findViewById(R.id.logisticButton);

        //Log.d("debug", buildings.toString());
        rv1 = (RecyclerView) findViewById(R.id.recyclerListBuildingBuilt);
        rv1.setLayoutManager(new LinearLayoutManager(EndGameActivity.this, LinearLayoutManager.VERTICAL, false));

        BuildingBuiltAdapter MyAdapter= new BuildingBuiltAdapter(buildings);
        rv1.setAdapter(MyAdapter);

        textScorePlayer.setText("Score : " + String.valueOf(PlayerConnexionActivity.net.getPlayer().getScore()));

        textAttractivityScore.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreAttractivite()));
        textEnvironmentScore.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreEnvironnemental()));
        textFluidityScore.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreFluidite()));

        /*logisticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("debug", "Listener");
                Intent MyIntent = new Intent(EndGameActivity.this, LogisticActivity.class);
                Log.d("debug", "Before startActivity");
                startActivity(MyIntent);
            }
        });*/



    }

    public void scoreLog(View view) {
        Log.d("debug", "Listener");
        Intent MyIntent = new Intent(EndGameActivity.this, LogisticActivity.class);
        Log.d("debug", "Before startActivity");
        startActivity(MyIntent);
    }
}
