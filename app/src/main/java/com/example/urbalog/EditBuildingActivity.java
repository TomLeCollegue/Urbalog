package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urbalog.Class.Building;
import com.example.urbalog.Json.JsonBuilding;

import static java.lang.String.valueOf;

public class EditBuildingActivity extends AppCompatActivity {

    public TextView name;
    public TextView description;
    public TextView politique;
    public TextView social;
    public TextView economique;
    public TextView attractivite;
    public TextView fluidite;
    public TextView environnemental;

    public TextView scoreLogistique;
    public TextView explicationLogistique;

    public Building building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_edit_building);

        building = (Building) getIntent().getSerializableExtra("building");

        name = findViewById(R.id.nameBuildingEdit);
        name.setText(building.getName());
        name.setHint(building.getName());

        description = findViewById(R.id.descriptionBuildingEdit);
        description.setText(building.getDescription());
        description.setHint(building.getDescription());

        politique = findViewById(R.id.politiqueBuildingEdit);
        politique.setText(valueOf(building.getCoutPolitique()));
        politique.setHint(valueOf(building.getCoutPolitique()));

        social = findViewById(R.id.socialBuildingEdit);
        social.setText(valueOf(building.getCoutSocial()));
        social.setHint(valueOf(building.getCoutSocial()));

        economique = findViewById(R.id.economiqueBuildingEdit);
        economique.setText(valueOf(building.getCoutEconomique()));
        economique.setHint(valueOf(building.getCoutEconomique()));

        attractivite = findViewById(R.id.attractiviteBuildingEdit);
        attractivite.setText(valueOf(building.getEffetAttractivite()));
        attractivite.setHint(valueOf(building.getEffetAttractivite()));

        fluidite = findViewById(R.id.fluiditeBuildingEdit);
        fluidite.setText(valueOf(building.getEffetFluidite()));
        fluidite.setHint(valueOf(building.getEffetFluidite()));

        environnemental = findViewById(R.id.environnementalBuildingEdit);
        environnemental.setText(valueOf(building.getEffetEnvironnemental()));
        environnemental.setHint(valueOf(building.getEffetEnvironnemental()));

        scoreLogistique = findViewById(R.id.scoreLogistiqueBuildingEdit);
        scoreLogistique.setText(valueOf(building.getScoreLogistique()));
        scoreLogistique.setHint(valueOf(building.getScoreLogistique()));

        explicationLogistique = findViewById(R.id.explicationLogistiqueBuildingEdit);
        explicationLogistique.setText(building.getExplicationLogistique());
        explicationLogistique.setHint(building.getExplicationLogistique());
    }

    public void validerBuildingEdit(View view) {
        if(name.getText().toString().matches("") ||
                description.getText().toString().matches("") ||
                politique.getText().toString().matches("") ||
                social.getText().toString().matches("") ||
                economique.getText().toString().matches("") ||
                attractivite.getText().toString().matches("") ||
                fluidite.getText().toString().matches("") ||
                environnemental.getText().toString().matches("") ||
                scoreLogistique.getText().toString().matches("") ||
                explicationLogistique.getText().toString().matches("")
                )
        {
            Toast.makeText(this, "impossible", Toast.LENGTH_SHORT).show();
        }else{
            if((name.getHint().toString()).equals(name.getText().toString()))
            {
                Building newBuilding = new Building(name.getText().toString(), description.getText().toString(), Integer.parseInt(politique.getText().toString()), Integer.parseInt(social.getText().toString()),  Integer.parseInt(economique.getText().toString()), Integer.parseInt(attractivite.getText().toString()), Integer.parseInt(fluidite.getText().toString()), Integer.parseInt(environnemental.getText().toString()), Integer.parseInt(scoreLogistique.getText().toString()), explicationLogistique.getText().toString() );
                JsonBuilding.modificationBuilding(newBuilding, name.getHint().toString());
                Intent intent = new Intent(EditBuildingActivity.this, ListBuildingsActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                if(JsonBuilding.buildingAlreadyInList(name.getText().toString())){
                    Toast.makeText(this, "Il y a deja un batiment avec le meme nom", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Le batiment a été modifié", Toast.LENGTH_SHORT).show();
                    Building newBuilding = new Building(name.getText().toString(), description.getText().toString(), Integer.parseInt(politique.getText().toString()), Integer.parseInt(social.getText().toString()),  Integer.parseInt(economique.getText().toString()), Integer.parseInt(attractivite.getText().toString()), Integer.parseInt(fluidite.getText().toString()), Integer.parseInt(environnemental.getText().toString()), Integer.parseInt(scoreLogistique.getText().toString()), explicationLogistique.getText().toString());
                    JsonBuilding.modificationBuilding(newBuilding, name.getHint().toString());
                    Intent intent = new Intent(EditBuildingActivity.this, ListBuildingsActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    public void onBackPressed(){
        Intent intent = new Intent(EditBuildingActivity.this, ListBuildingsActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}