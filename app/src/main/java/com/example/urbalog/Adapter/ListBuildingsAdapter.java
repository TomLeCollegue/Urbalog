package com.example.urbalog.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbalog.Class.Building;
import com.example.urbalog.Json.JsonBuilding;
import com.example.urbalog.ListBuildingsActivity;
import com.example.urbalog.R;

import java.util.ArrayList;

public class ListBuildingsAdapter extends RecyclerView.Adapter<ListBuildingsAdapter.MyViewHolder>{

    public ArrayList<Building> buildings;
    private OnItemClickListener mListener;
    private OnItemLongClickListener mListener2;
    private Context mContext;

    public ListBuildingsAdapter(ArrayList<Building> buildings){
        this.buildings = buildings;
    }

    public ListBuildingsAdapter(ArrayList<Building> buildings, Context mContext){
        this.buildings = buildings;
        this.mContext = mContext;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setonItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public void setonItemLongClickListener(OnItemLongClickListener listener2) {
        mListener2 = listener2;
    }

    @NonNull
    @Override
    public ListBuildingsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.layout_list_buildings, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBuildingsAdapter.MyViewHolder holder, int position) {
        Building building = buildings.get(position);
        holder.display(building);
        holder.hideDeleteButton(position);
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;
        private final TextView politique;
        private final TextView social;
        private final TextView economique;
        private final TextView attractivite;
        private final TextView fluidite;
        private final TextView environnnement;
        private final Button buttonDelete;


        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameBuilding);
            description = itemView.findViewById(R.id.descriptionBuilding);
            politique = itemView.findViewById(R.id.coutPolitiqueBuilding);
            social = itemView.findViewById(R.id.coutSocialBuilding);
            economique = itemView.findViewById(R.id.coutEconomiqueBuilding);
            attractivite = itemView.findViewById(R.id.effetAttractiviteBuilding);
            fluidite = itemView.findViewById(R.id.effetFluiditeBuilding);
            environnnement = itemView.findViewById(R.id.effetEnvironnementBuilding);

            buttonDelete = itemView.findViewById(R.id.buttonDeleteBuilding);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int position = getAdapterPosition();

                    buttonDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView textView = itemView.findViewById(R.id.nameBuilding);
                            JsonBuilding.removeBuilding(textView.getText().toString());
                            if(mContext instanceof ListBuildingsActivity){
                                ((ListBuildingsActivity) mContext).refreshAdapter(position);


                            }
                            Toast.makeText(itemView.getContext(), textView.getText().toString() + " a été supprimé", Toast.LENGTH_LONG).show();

                        }
                    });
                    mListener2.onItemLongClick(position);
                    CardView cardView = itemView.findViewById(R.id.card_view);
                    if(buttonDelete.getVisibility() == View.GONE)
                    {
                        Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
                        cardView.startAnimation(shake);
                        buttonDelete.setVisibility(View.VISIBLE);
                        Animation fade = AnimationUtils.loadAnimation(mContext, R.anim.fadein);
                        buttonDelete.startAnimation(fade);
                    }
                    else
                    {
                        cardView.clearAnimation();
                        Animation fade = AnimationUtils.loadAnimation(mContext, R.anim.fadeout);
                        buttonDelete.startAnimation(fade);
                        buttonDelete.setVisibility(View.GONE);
                    }
                    return true;
                }
            });
        }

        /**
         *
         * @param building
         */
        public void display(Building building) {

            buttonDelete.setVisibility(View.GONE);

            name.setText(building.getName());
            description.setText(building.getDescription());
            politique.setText(String.valueOf(building.getCoutPolitique()));
            social.setText(String.valueOf(building.getCoutSocial()));
            economique.setText(String.valueOf(building.getCoutEconomique()));
            attractivite.setText(String.valueOf(building.getEffetAttractivite()));
            fluidite.setText(String.valueOf(building.getEffetFluidite()));
            environnnement.setText(String.valueOf(building.getEffetEnvironnemental()));
        }

        public void hideDeleteButton(int position) {

        }
    }
}
