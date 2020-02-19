package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.urbalog.Adapter.BuildingInfluenceAdapter;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.City;
import com.example.urbalog.Class.Player;
import com.example.urbalog.Json.JsonBuilding;

import java.util.ArrayList;

public class LogisticActivity extends AppCompatActivity {

    private ArrayList<Building> buildings = new ArrayList<Building>();
    private RecyclerView rv;
    private TextView totalLogisticScore;
    private int iLogisticScore;
    private TextView totalLogisticScoreSentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("debug", "LogisticActivity creation");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic);

        Log.d("debug", "LogisticActivity creation after super ");

        /*** Print the logistic score at the end of the game ***//*

        totalLogisticScore = (TextView) findViewById(R.id.TotalScore_TextView);
        totalLogisticScore.setText(PlayerConnexionActivity.net.getCurrentGame().getScoreLogistique());

        *//*** Choose and print the sentence below the Logistic Score ***//*
        iLogisticScore = PlayerConnexionActivity.net.getCurrentGame().getScoreLogistique();
        totalLogisticScoreSentence = (TextView) findViewById(R.id.LogisticSentence_TextView);
        
        if (iLogisticScore <= -2) {
            totalLogisticScoreSentence.setText("D'un point de vue logistique, l'organisation de votre ville est à revoir.");
        }
        else if ( -2 < iLogisticScore &&  iLogisticScore<= 0) {
            totalLogisticScoreSentence.setText("Dans votre ville, les transports de marchandises se font avec quelques difficultés mineures.");
        }
        else if (0 < iLogisticScore && iLogisticScore<= 2){
            totalLogisticScoreSentence.setText("D'un point de vue logistique, votre ville s'en sort plutôt bien, la plupart des livraisons se font sans encombre.");
        }
        else {
            totalLogisticScoreSentence.setText("Le transport de marchandises se fait très facilement dans votre ville.");
        }


         *//*** Put the city buildings in the recycler view ***//*
        buildings = PlayerConnexionActivity.net.getCurrentGame().getCity().getBuildings();

        //Log.d("debug", buildings.toString());
        rv = (RecyclerView) findViewById(R.id.recyclerListBuildingInfluence);
        rv.setLayoutManager(new LinearLayoutManager(LogisticActivity.this, LinearLayoutManager.VERTICAL, false));

        BuildingInfluenceAdapter mMyadapter= new BuildingInfluenceAdapter(buildings);
        rv.setAdapter(mMyadapter);*/

    }
    
}