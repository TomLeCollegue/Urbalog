package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.urbalog.Adapter.ListBuildingsAdapter;
import com.example.urbalog.Adapter.RolesAdapter;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Role;
import com.example.urbalog.Json.JsonRole;

import java.util.ArrayList;

public class RecyclerViewRolesActivity extends AppCompatActivity implements RolesAdapter.OnItemClickListener{
    private RecyclerView rv;
    private RolesAdapter mMyadapter;
    private ArrayList<Role> roles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view_roles);

        rv =findViewById(R.id.recyclerview_roles);
        roles = JsonRole.readRole();

        rv.setLayoutManager(new LinearLayoutManager(RecyclerViewRolesActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mMyadapter = new RolesAdapter(roles, this);
        rv.setAdapter(mMyadapter);
        mMyadapter.setonItemClickListener(RecyclerViewRolesActivity.this);

    }

    public void onItemClick(int position) {
        Role clickedItem = roles.get(position);
        Intent intent = new Intent(RecyclerViewRolesActivity.this, ModifRoleActivity.class);
        intent.putExtra("role", clickedItem);
        startActivity(intent);
        finish();
    }
}
