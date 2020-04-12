package com.example.urbalog.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        private final ImageView bImage;

        private final ImageView iEnvi;
        private final ImageView iFluid;
        private final ImageView iAttract;
        private final TextView text_Empty;

        public MyViewHolder(@NonNull final View itemView){
            super(itemView);

            bName = itemView.findViewById(R.id.bName);
            bEnvironmentalScore = itemView.findViewById(R.id.bEnvironmentalScore);
            bFluidityScore = itemView.findViewById(R.id.bFluidityScore);
            bAttractivenessScore = itemView.findViewById(R.id.bAttractivenessScore);
            bImage = itemView.findViewById(R.id.image_maison);

            iEnvi = itemView.findViewById(R.id.image_envi);
            iFluid = itemView.findViewById(R.id.image_fluid);
            iAttract = itemView.findViewById(R.id.image_attract);
            text_Empty = itemView.findViewById(R.id.text_empty);



        }

        public void display(Building building){
            bName.setText(building.getName());
            bEnvironmentalScore.setText(String.valueOf(building.getEffetEnvironnemental()));
            bFluidityScore.setText(String.valueOf(building.getEffetFluidite()));
            bAttractivenessScore.setText(String.valueOf(building.getEffetAttractivite()));

            if (building.getName().equals("Emplacement Libre")){
                bImage.setImageResource(R.drawable.plots);
                bEnvironmentalScore.setText(" ");
                bAttractivenessScore.setText(" ");
                bFluidityScore.setText(" ");
                text_Empty.setText("Emplacement Libre");
                bName.setVisibility(View.INVISIBLE);
                iAttract.setVisibility(View.INVISIBLE);
                iFluid.setVisibility(View.INVISIBLE);
                iEnvi.setVisibility(View.INVISIBLE);

            }
        }
    }

    public void updateData(ArrayList data) {
        buildings.clear();
        buildings.addAll(data);
        this.notifyDataSetChanged();
    }
}
