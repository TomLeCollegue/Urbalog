package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.urbalog.Adapter.ListBuildingsAdapter;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Json.JsonBuilding;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;

public class ListBuildingsActivity extends AppCompatActivity implements ListBuildingsAdapter.OnItemClickListener, ListBuildingsAdapter.OnItemLongClickListener{


    public static ArrayList<Building> buildings = new ArrayList<Building>();

    public static RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_buildings);

        buildings = JsonBuilding.readBuilding();

        rv = (RecyclerView) findViewById(R.id.recyclerListBuildings);
        rv.setLayoutManager(new LinearLayoutManager(ListBuildingsActivity.this, LinearLayoutManager.HORIZONTAL, false));
        ListBuildingsAdapter mMyadapter= new ListBuildingsAdapter(buildings);
        rv.setAdapter(mMyadapter);
        mMyadapter.setonItemClickListener(ListBuildingsActivity.this);
        mMyadapter.setonItemLongClickListener(ListBuildingsActivity.this);
    }


    public void onItemClick(int position) {
        Building clickedItem = buildings.get(position);
        Intent intent = new Intent(ListBuildingsActivity.this, EditBuildingActivity.class);
        intent.putExtra("building", clickedItem);
        startActivity(intent);
    }

    public void onItemLongClick(final int position) {
        Building clickedItem = buildings.get(position);
        //Log.d("debug", clickedItem.toString());
    }
}