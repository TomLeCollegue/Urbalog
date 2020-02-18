package com.example.urbalog.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.urbalog.Class.Building;
import com.example.urbalog.R;

import java.util.ArrayList;

public class BuildingInfluenceAdapter extends RecyclerView.Adapter<BuildingInfluenceAdapter.MyViewHolder>{

    public ArrayList<Building> buildings;

    public BuildingInfluenceAdapter(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    @NonNull
    @Override
    public BuildingInfluenceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.layout_list_influence, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuildingInfluenceAdapter.MyViewHolder holder, int position) {
        Building building = buildings.get(position);
        holder.display(building);
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomTest;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            nomTest = itemView.findViewById(R.id.nomTest);
        }

        public void display(Building building) {

            nomTest.setText(building.getName());

        }
    }

}
