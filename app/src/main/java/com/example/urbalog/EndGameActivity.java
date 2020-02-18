package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.urbalog.Adapter.BuildingBuiltAdapter;
import com.example.urbalog.Adapter.BuildingInfluenceAdapter;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Json.JsonBuilding;

import java.util.ArrayList;

public class EndGameActivity extends AppCompatActivity {

    public static ArrayList<Building> buildings = new ArrayList<Building>();
    public static RecyclerView rv1;
    private TextView textScorePlayer;

    private TextView textAttractivityScore;
    private TextView textEnvironmentScore;
    private TextView textFluidityScore;

    private Button logisticButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        JsonBuilding.init(EndGameActivity.this);
        buildings = JsonBuilding.readBuilding();

        textScorePlayer = (TextView) findViewById(R.id.text_score_player);

        textAttractivityScore = (TextView) findViewById(R.id.attractivity_score);
        textEnvironmentScore = (TextView) findViewById(R.id.environment_score);
        textFluidityScore = (TextView) findViewById(R.id.fluidity_score);

        logisticButton = (Button) findViewById(R.id.logisticButton);

        Log.d("debug", buildings.toString());
        rv1 = (RecyclerView) findViewById(R.id.recyclerListBuildingBuilt);
        rv1.setLayoutManager(new LinearLayoutManager(EndGameActivity.this, LinearLayoutManager.VERTICAL, false));

        BuildingBuiltAdapter MyAdapter= new BuildingBuiltAdapter(buildings);
        rv1.setAdapter(MyAdapter);

        /*textScorePlayer.setText("Score : " + String.valueOf(PlayerConnexionActivity.net.getPlayer().getScore()));

        textAttractivityScore.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreAttractivite()));
        textEnvironmentScore.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreEnvironnemental()));
        textFluidityScore.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreFluidite()));*/

        logisticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MyIntent = new Intent(EndGameActivity.this,LogisticActivity.class);
                startActivity(MyIntent);
            }
        });



    }
}
