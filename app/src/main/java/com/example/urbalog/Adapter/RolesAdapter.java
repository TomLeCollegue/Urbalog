package com.example.urbalog.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Role;
import com.example.urbalog.R;

import java.util.ArrayList;

public class RolesAdapter extends RecyclerView.Adapter<RolesAdapter.MyViewHolder1>{

    public ArrayList<Role> roles;
    private OnItemClickListener Listener;
    private Context context;

    public RolesAdapter(ArrayList<Role> roles, Context context) {
        this.roles = roles;
        this.context = context;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setonItemClickListener(RolesAdapter.OnItemClickListener listener)
    {
        Listener = listener;
    }

    @NonNull
    @Override
    public RolesAdapter.MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.card_view_role, parent, false);
        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RolesAdapter.MyViewHolder1 holder, int position) {
        Role role = roles.get(position);
        holder.display(role);
    }

    @Override
    public int getItemCount() {
        return roles.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView nameRole;
        private final TextView improve;
        private final TextView hold;
        private final TextView nb_eco;
        private final TextView nb_poli;
        private final TextView nb_social;


        public MyViewHolder1(@NonNull final View itemView) {
            super(itemView);

            nameRole = itemView.findViewById(R.id.name_role);
            improve = itemView.findViewById(R.id.improve);
            hold = itemView.findViewById(R.id.hold);
            nb_eco = itemView.findViewById(R.id.nb_eco);
            nb_poli = itemView.findViewById(R.id.nb_poli);
            nb_social = itemView.findViewById(R.id.nb_social);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            Listener.onItemClick(position);
                        }
                    }
                }
            });

        }

        public void display(Role role) {

            nameRole.setText(role.getTypeRole());
            improve.setText("improve : " + role.getImprove());
            hold.setText("hold : " + role.getHold());
            nb_eco.setText("" + role.getTokenEconomical());
            nb_poli.setText("" + role.getTokenPolitical());
            nb_social.setText("" + role.getTokenSocial());

        }
    }

}
