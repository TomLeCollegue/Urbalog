package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.urbalog.Adapter.CityProgressionAdapter;
import com.example.urbalog.Class.Building;

import java.util.ArrayList;

/* This activity is opened whenever a player wants to see the city
 progression during gameplay */
public class CityProgressionActivity extends AppCompatActivity {

    private ArrayList<Building> buildings = new ArrayList<Building>();
    private RecyclerView rv;
    private TextView turnNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_progression);

        buildings = PlayerConnexionActivity.net.getCurrentGame().getCity().getBuildings();

        rv = (RecyclerView) findViewById(R.id.recyclerListBuildingsInCity);
        rv.setLayoutManager(new LinearLayoutManager(CityProgressionActivity.this,LinearLayoutManager.VERTICAL,false));

        CityProgressionAdapter mProgressionAdapter = new CityProgressionAdapter(buildings);
        rv.setAdapter(mProgressionAdapter);

        turnNumber = (TextView) findViewById(R.id.text_turnNumber);
        turnNumber.setText("Tour n°"+PlayerConnexionActivity.net.getCurrentGame().getnTurn());
    }
}
