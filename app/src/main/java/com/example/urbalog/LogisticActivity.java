package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.urbalog.Adapter.BuildingInfluenceAdapter;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Json.JsonBuilding;

import java.util.ArrayList;

public class LogisticActivity extends AppCompatActivity {

    public static ArrayList<Building> buildings = new ArrayList<Building>();
    public static RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic);

        JsonBuilding.init(LogisticActivity.this);
        buildings = JsonBuilding.readBuilding();

        Log.d("debug", buildings.toString());
        rv = (RecyclerView) findViewById(R.id.recyclerListBuildingInfluence);
        rv.setLayoutManager(new LinearLayoutManager(LogisticActivity.this, LinearLayoutManager.VERTICAL, false));

        BuildingInfluenceAdapter mMyadapter= new BuildingInfluenceAdapter(buildings);
        rv.setAdapter(mMyadapter);




    }


}