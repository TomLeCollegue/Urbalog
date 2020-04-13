package com.example.urbalog;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbalog.Adapter.CityProgressionAdapter;
import com.example.urbalog.Class.Building;

import java.util.ArrayList;

/* This activity is opened whenever a player wants to see the city
 progression during gameplay */
public class CityProgressionActivity extends AppCompatActivity {

    private ArrayList<Building> buildings = new ArrayList<Building>();
    private RecyclerView rv;
    private CityProgressionAdapter mProgressionAdapter;
    private TextView turnNumber;

    private TextView textAttractivityScore;
    private TextView textEnvironmentScore;
    private TextView textFluidityScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_progression);

        buildings = AdminConnectionActivity.net.getCurrentGame().getCity().getBuildings();

        textAttractivityScore = findViewById(R.id.text_score_city_attract);
        textEnvironmentScore = findViewById(R.id.text_score_city_envi);
        textFluidityScore = findViewById(R.id.text_score_city_trafic);

        textAttractivityScore.setText(String.valueOf(AdminConnectionActivity.net.getCurrentGame().getScoreAttractivite()));
        textEnvironmentScore.setText(String.valueOf(AdminConnectionActivity.net.getCurrentGame().getScoreEnvironnemental()));
        textFluidityScore.setText(String.valueOf(AdminConnectionActivity.net.getCurrentGame().getScoreFluidite()));

        rv = findViewById(R.id.recyclerListBuildingsInCity);
        rv.setLayoutManager(new LinearLayoutManager(CityProgressionActivity.this,LinearLayoutManager.VERTICAL,false));

        mProgressionAdapter = new CityProgressionAdapter(buildings);
        rv.setAdapter(mProgressionAdapter);

        turnNumber = findViewById(R.id.text_turnNumber);
        turnNumber.setText("Tour n°"+AdminConnectionActivity.net.getCurrentGame().getnTurn());

        AdminConnectionActivity.net.setCurrentAdminView(this);
    }

    public void updateView(){
        buildings = AdminConnectionActivity.net.getCurrentGame().getCity().getBuildings();
        mProgressionAdapter.updateData(buildings);
        rv.setAdapter(mProgressionAdapter);
    }
}
