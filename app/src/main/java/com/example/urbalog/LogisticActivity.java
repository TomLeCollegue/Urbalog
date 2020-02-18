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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic);

        /*** Print the logistic score at the end of the game ***/

        totalLogisticScore = (TextView) findViewById(R.id.TotalScore_TextView);
        //Version de test
        totalLogisticScore.setText("-3");

        //Version fin
        //Ne peut être testée qu'avec une partie finie
        //totalLogisticScore.setText(PlayerConnexionActivity.net.getCurrentGame().getScoreLogistique());

        /*** Choose and print the sentence below the Logistic Score ***/
        //version de test
        iLogisticScore = -3;


        //version final qu'on ne peut pas tester
        //iLogisticScore = PlayerConnexionActivity.net.getCurrentGame().getScoreLogistique();
        totalLogisticScoreSentence = (TextView) findViewById(R.id.LogisticSentence_TextView);
        
        if (iLogisticScore <= -2) {
            totalLogisticScoreSentence.setText("Pas ouf la fluidité");
        }


         /*** Put the city buildings in the recycler view ***/
        //Version de test
        JsonBuilding.init(LogisticActivity.this);
        buildings = JsonBuilding.readBuilding();

        //Version finale
        //buildings = PlayerConnexionActivity.net.getCurrentGame().getCity().getBuildings();

        //Log.d("debug", buildings.toString());
        rv = (RecyclerView) findViewById(R.id.recyclerListBuildingInfluence);
        rv.setLayoutManager(new LinearLayoutManager(LogisticActivity.this, LinearLayoutManager.VERTICAL, false));

        BuildingInfluenceAdapter mMyadapter= new BuildingInfluenceAdapter(buildings);
        rv.setAdapter(mMyadapter);

    }
    
}