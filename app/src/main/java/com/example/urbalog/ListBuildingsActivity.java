package com.example.urbalog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbalog.Adapter.ListBuildingsAdapter;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Json.JsonBuilding;

import java.util.ArrayList;

public class ListBuildingsActivity extends AppCompatActivity implements ListBuildingsAdapter.OnItemClickListener, ListBuildingsAdapter.OnItemLongClickListener{


    private ArrayList<Building> buildings = new ArrayList<Building>();
    private ListBuildingsAdapter mMyadapter;

    private RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_buildings);

        buildings = JsonBuilding.readBuilding();

        rv = findViewById(R.id.recyclerListBuildings);
        rv.setLayoutManager(new LinearLayoutManager(ListBuildingsActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mMyadapter = new ListBuildingsAdapter(buildings, this);
        rv.setAdapter(mMyadapter);
        mMyadapter.setonItemClickListener(ListBuildingsActivity.this);
        mMyadapter.setonItemLongClickListener(ListBuildingsActivity.this);
        
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dx > 20 || dx < -20){
                    mMyadapter.notifyDataSetChanged();
                }
            }
        });


        
    }


    public void onItemClick(int position) {
        Building clickedItem = buildings.get(position);
        Intent intent = new Intent(ListBuildingsActivity.this, EditBuildingActivity.class);
        intent.putExtra("building", clickedItem);
        startActivity(intent);
        finish();
    }

    public void onItemLongClick(final int position) {
        Building clickedItem = buildings.get(position);
        Log.d("debug", clickedItem.toString());
    }

    public void refreshAdapter(int position){
        buildings.remove(position);
        mMyadapter.notifyItemRemoved(position);
        mMyadapter.notifyItemRangeChanged(position, buildings.size());
    }

    public void onBackPressed(){
        Intent intent = new Intent(ListBuildingsActivity.this, ConfigurationActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}