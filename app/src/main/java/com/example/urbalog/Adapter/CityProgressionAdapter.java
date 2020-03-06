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

public class CityProgressionAdapter extends RecyclerView.Adapter<CityProgressionAdapter.MyViewHolder> {

    public ArrayList<Building> buildings;

    public CityProgressionAdapter(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    @NonNull
    @Override
    public CityProgressionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.layout_list_buildings_built_hugo,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityProgressionAdapter.MyViewHolder holder, int position){
        Building building = buildings.get(position);
        holder.display(building);
    }

    @Override
    public int getItemCount(){
        return buildings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView bName;
        private final TextView bEnvironmentalScore;
        private final TextView bFluidityScore;
        private final TextView bAttractivenessScore;
        private final TextView bDescription;

        public MyViewHolder(@NonNull final View itemView){
            super(itemView);

            bName = itemView.findViewById(R.id.bName);
            bEnvironmentalScore = itemView.findViewById(R.id.bEnvironmentalScore);
            bFluidityScore = itemView.findViewById(R.id.bFluidityScore);
            bAttractivenessScore = itemView.findViewById(R.id.bAttractivenessScore);
            bDescription = itemView.findViewById(R.id.bDescription);

        }

        public void display(Building building){
            bName.setText(building.getName());
            bEnvironmentalScore.setText(building.getEffetEnvironnemental().toString());
            bFluidityScore.setText(building.getEffetFluidite().toString());
            bAttractivenessScore.setText(building.getEffetAttractivite().toString());
            bDescription.setText(building.getDescription());
        }
    }
}
