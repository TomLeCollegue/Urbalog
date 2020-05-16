package com.example.urbalog;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbalog.Adapter.CityProgressionAdapter;
import com.example.urbalog.Class.Building;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* This activity is opened whenever a player wants to see the city
 progression during gameplay */
public class CityProgressionActivity extends AppCompatActivity {

    private ArrayList<Building> buildings = new ArrayList<Building>();
    private RecyclerView rv;
    private CityProgressionAdapter mProgressionAdapter;

    private ImageView camionEnvi;
    private ImageView camionFluid;
    private ImageView camionAttract;

    private TextView gameTimerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_progression);

        camionEnvi = findViewById(R.id.camion);
        camionFluid = findViewById(R.id.camion2);
        camionAttract = findViewById(R.id.camion3);

        gameTimerText = findViewById(R.id.gameTimerText);

        int scoreEnvi = MainActivity.net.getCurrentGame().getScoreEnvironnemental();
        int scoreFluid = MainActivity.net.getCurrentGame().getScoreFluidite();
        int scoreAttract = MainActivity.net.getCurrentGame().getScoreAttractivite();

        final FrameLayout.LayoutParams lpEnvi = (FrameLayout.LayoutParams) camionEnvi.getLayoutParams();
        lpEnvi.setMargins(900 + 100*scoreEnvi,0, 0, 0);

        camionEnvi.setLayoutParams(lpEnvi);

        final FrameLayout.LayoutParams lpFluid = (FrameLayout.LayoutParams) camionFluid.getLayoutParams();
        lpFluid.setMargins(900 + 100*scoreFluid,100, 0, 0);

        camionFluid.setLayoutParams(lpFluid);

        final FrameLayout.LayoutParams lpAttract = (FrameLayout.LayoutParams) camionAttract.getLayoutParams();
        lpAttract.setMargins(900 + 100*scoreAttract,195, 0, 0);

        camionAttract.setLayoutParams(lpAttract);

        buildings.addAll(MainActivity.net.getCurrentGame().getCity().getBuildings());

        Building b = new Building("Emplacement Libre"," ",0,0,0,0,0,0,0, " ");

        int size = buildings.size();

        for (int i = 0; i< 6 - size; i++){
            buildings.add(b);
        }

        mProgressionAdapter = new CityProgressionAdapter(buildings);
        rv = findViewById(R.id.recyclerListBuildingsInCity);
        rv.setLayoutManager(new LinearLayoutManager(CityProgressionActivity.this,LinearLayoutManager.HORIZONTAL,false));

        rv.setAdapter(mProgressionAdapter);

        MainActivity.net.setCurrentAdminView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.net.setCurrentAdminView(null);
    }

    public void updateView(){ // a appeler pour refresh la vue
        buildings.clear();
        buildings.addAll(MainActivity.net.getCurrentGame().getCity().getBuildings());
        Building bVide = new Building("Emplacement Libre"," ",0,0,0,0,0,0,0, " ");
        int size = buildings.size();

        for (int i = 0; i< 6 - size; i++){
            buildings.add(bVide);
        }

        mProgressionAdapter = new CityProgressionAdapter(buildings);
        rv.setLayoutManager(new LinearLayoutManager(CityProgressionActivity.this,LinearLayoutManager.HORIZONTAL,false));

        rv.setAdapter(mProgressionAdapter);

        int scoreEnvi = MainActivity.net.getCurrentGame().getScoreEnvironnemental();
        int scoreFluid = MainActivity.net.getCurrentGame().getScoreFluidite();
        int scoreAttract = MainActivity.net.getCurrentGame().getScoreAttractivite();


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

    public void setTextGameTimer(long millisLeft){
        long hours = TimeUnit.MILLISECONDS.toHours(millisLeft);
        millisLeft -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millisLeft);
        millisLeft -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millisLeft);

        StringBuilder sb = new StringBuilder(64);
        sb.append("Fin de la partie dans : ");
        sb.append(hours);
        sb.append(":");
        sb.append(minutes);
        sb.append(":");
        sb.append(seconds);

        gameTimerText.setText(sb);
    }

    public void appearGameTimer(){
        gameTimerText.setVisibility(View.VISIBLE);
    }
}
