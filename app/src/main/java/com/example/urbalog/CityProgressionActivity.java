package com.example.urbalog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbalog.Adapter.CityProgressionAdapter;
import com.example.urbalog.Class.Building;

import java.util.ArrayList;

/* This activity is opened whenever a player wants to see the city
 progression during gameplay */
public class CityProgressionActivity extends AppCompatActivity {

    private ArrayList<Building> buildings = new ArrayList<Building>();
    private RecyclerView rv;
    private CityProgressionAdapter mProgressionAdapter;

    private ImageView camionEnvi;
    private ImageView camionFluid;
    private ImageView camionAttract;
    private Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_progression);

        camionEnvi = (ImageView) findViewById(R.id.camion);
        camionFluid =  (ImageView) findViewById(R.id.camion2);
        camionAttract =  (ImageView) findViewById(R.id.camion3);
        refresh = (Button) findViewById(R.id.button2);


        int scoreEnvi = AdminConnectionActivity.net.getCurrentGame().getScoreEnvironnemental();
        int scoreFluid = AdminConnectionActivity.net.getCurrentGame().getScoreFluidite();
        int scoreAttract = AdminConnectionActivity.net.getCurrentGame().getScoreAttractivite();


        final FrameLayout.LayoutParams lpEnvi = (FrameLayout.LayoutParams) camionEnvi.getLayoutParams();
        lpEnvi.setMargins(900 + 100*scoreEnvi,0, 0, 0);

        camionEnvi.setLayoutParams(lpEnvi);

        final FrameLayout.LayoutParams lpFluid = (FrameLayout.LayoutParams) camionFluid.getLayoutParams();
        lpFluid.setMargins(900 + 100*scoreFluid,100, 0, 0);

        camionFluid.setLayoutParams(lpFluid);

        final FrameLayout.LayoutParams lpAttract = (FrameLayout.LayoutParams) camionAttract.getLayoutParams();
        lpAttract.setMargins(900 + 100*scoreAttract,195, 0, 0);

        camionAttract.setLayoutParams(lpAttract);

        buildings.addAll(AdminConnectionActivity.net.getCurrentGame().getCity().getBuildings());

        Building b = new Building("Emplacement Libre"," ",0,0,0,0,0,0,0, " ");

        int size = buildings.size();

        for (int i = 0; i< 6 - size; i++){
            buildings.add(b);
        }

        mProgressionAdapter = new CityProgressionAdapter(buildings);
        rv = findViewById(R.id.recyclerListBuildingsInCity);
        rv.setLayoutManager(new LinearLayoutManager(CityProgressionActivity.this,LinearLayoutManager.HORIZONTAL,false));

        rv.setAdapter(mProgressionAdapter);

        AdminConnectionActivity.net.setCurrentAdminView(this);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AdminConnectionActivity.net.setCurrentAdminView(null);
    }

    public void updateView(){ // a appeler pour refresh la vue
        buildings.clear();
        buildings.addAll(AdminConnectionActivity.net.getCurrentGame().getCity().getBuildings());
        Building bVide = new Building("Emplacement Libre"," ",0,0,0,0,0,0,0, " ");
        int size = buildings.size();

        for (int i = 0; i< 6 - size; i++){
            buildings.add(bVide);
        }

        mProgressionAdapter = new CityProgressionAdapter(buildings);
        rv.setLayoutManager(new LinearLayoutManager(CityProgressionActivity.this,LinearLayoutManager.HORIZONTAL,false));

        rv.setAdapter(mProgressionAdapter);

        int scoreEnvi = AdminConnectionActivity.net.getCurrentGame().getScoreEnvironnemental();
        int scoreFluid = AdminConnectionActivity.net.getCurrentGame().getScoreFluidite();
        int scoreAttract = AdminConnectionActivity.net.getCurrentGame().getScoreAttractivite();


        final FrameLayout.LayoutParams lpEnvi = (FrameLayout.LayoutParams) camionEnvi.getLayoutParams();
        lpEnvi.setMargins(900 + 100*scoreEnvi,0, 0, 0);

        camionEnvi.setLayoutParams(lpEnvi);

        final FrameLayout.LayoutParams lpFluid = (FrameLayout.LayoutParams) camionFluid.getLayoutParams();
        lpFluid.setMargins(900 + 100*scoreFluid,100, 0, 0);

        camionFluid.setLayoutParams(lpFluid);

        final FrameLayout.LayoutParams lpAttract = (FrameLayout.LayoutParams) camionAttract.getLayoutParams();
        lpAttract.setMargins(900 + 100*scoreAttract,195, 0, 0);

        camionAttract.setLayoutParams(lpAttract);
    }
}
