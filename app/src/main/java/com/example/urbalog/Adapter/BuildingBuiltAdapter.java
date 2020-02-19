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

public class BuildingBuiltAdapter extends RecyclerView.Adapter<BuildingBuiltAdapter.MyViewHolder1>{

    public ArrayList<Building> buildings;

    public BuildingBuiltAdapter(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    @NonNull
    @Override
    public BuildingBuiltAdapter.MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.layout_list_buildings_built, parent, false);
        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuildingBuiltAdapter.MyViewHolder1 holder, int position) {
        Building building = buildings.get(position);
        holder.display(building);
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView buildingNameScore;
        private final TextView environmentScore;
        private final TextView fluidityScore;
        private final TextView attractivityScore;

        public MyViewHolder1(@NonNull final View itemView) {
            super(itemView);

            buildingNameScore = itemView.findViewById(R.id.buildingNameScore);
            environmentScore = itemView.findViewById(R.id.rv_environment_score);
            fluidityScore = itemView.findViewById(R.id.rv_fluidity_score);
            attractivityScore = itemView.findViewById(R.id.rv_attractivity_score);

        }

        public void display(Building building) {

            buildingNameScore.setText(building.getName());
            environmentScore.setText(building.getEffetEnvironnemental().toString());
            fluidityScore.setText(building.getEffetFluidite().toString());
            attractivityScore.setText(building.getEffetAttractivite().toString());

        }
    }

}
