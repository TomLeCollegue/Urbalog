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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_progression);


        buildings = AdminConnectionActivity.net.getCurrentGame().getCity().getBuildings();

        Building b = new Building("Emplacement Libre"," ",0,0,0,0,0,0,0, " ");

        int size = buildings.size();

        for (int i = 0; i< 6 - size; i++){
            buildings.add(b);
        }


        mProgressionAdapter = new CityProgressionAdapter(buildings);
        rv = findViewById(R.id.recyclerListBuildingsInCity);
        rv.setLayoutManager(new LinearLayoutManager(CityProgressionActivity.this,LinearLayoutManager.HORIZONTAL,false));


        rv.setAdapter(mProgressionAdapter);



        AdminConnectionActivity.net.setCurrentAdminView(this);
    }

    public void updateView(){
        buildings = AdminConnectionActivity.net.getCurrentGame().getCity().getBuildings();
        mProgressionAdapter.updateData(buildings);
        rv.setAdapter(mProgressionAdapter);
    }
}
