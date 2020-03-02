package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urbalog.Class.Building;
import com.example.urbalog.Json.JsonBuilding;

import static java.lang.String.valueOf;

public class AddBuildingActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_building);

        name = findViewById(R.id.nameBuildingEdit);
        description = findViewById(R.id.descriptionBuildingEdit);
        politique = findViewById(R.id.politiqueBuildingEdit);
        social = findViewById(R.id.socialBuildingEdit);
        economique = findViewById(R.id.economiqueBuildingEdit);
        attractivite = findViewById(R.id.attractiviteBuildingEdit);
        fluidite = findViewById(R.id.fluiditeBuildingEdit);
        environnemental = findViewById(R.id.environnementalBuildingEdit);
        scoreLogistique = findViewById(R.id.scoreLogistiqueBuildingEdit);
        explicationLogistique = findViewById(R.id.explicationLogistiqueBuildingEdit);
    }

    public void validerBuildingAdd(View view) {
        boolean finish = true;
        if(name.getText().toString().matches("")){
            name.setError("champs vide");
            finish = false;
        }
        if(description.getText().toString().matches("")){
            description.setError("champs vide");
            finish = false;
        }
        if(politique.getText().toString().matches("")){
            politique.setError("champs vide");
            finish = false;
        }
        if(social.getText().toString().matches("")){
            social.setError("champs vide");
            finish = false;
        }
        if(economique.getText().toString().matches("")){
            economique.setError("champs vide");
            finish = false;
        }
        if(attractivite.getText().toString().matches("")){
            attractivite.setError("champs vide");
            finish = false;
        }
        if(fluidite.getText().toString().matches("")){
            fluidite.setError("champs vide");
            finish = false;
        }
        if(environnemental.getText().toString().matches("")){
            environnemental.setError("champs vide");
            finish = false;
        }
        if(scoreLogistique.getText().toString().matches("")){
            scoreLogistique.setError("champs vide");
            finish = false;
        }
        if(explicationLogistique.getText().toString().matches("")){
            explicationLogistique.setError("champs vide");
            finish = false;
        }

        if(finish == true){
            if(JsonBuilding.buildingAlreadyInList(name.getText().toString())){
                Toast.makeText(this, "Il y a deja un batiment avec le meme nom", Toast.LENGTH_SHORT).show();
            }else{
                Building newBuilding = new Building(name.getText().toString(), description.getText().toString(), Integer.parseInt(politique.getText().toString()), Integer.parseInt(social.getText().toString()),  Integer.parseInt(economique.getText().toString()), Integer.parseInt(attractivite.getText().toString()), Integer.parseInt(fluidite.getText().toString()), Integer.parseInt(environnemental.getText().toString()), Integer.parseInt(scoreLogistique.getText().toString()), explicationLogistique.getText().toString() );
                JsonBuilding.writeBuilding(newBuilding);
                Toast.makeText(this, "Le batiment à été ajouté", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddBuildingActivity.this, ConfigurationActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    public void onBackPressed(){
        Intent intent = new Intent(AddBuildingActivity.this, ConfigurationActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}