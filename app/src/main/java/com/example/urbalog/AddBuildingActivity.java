package com.example.urbalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    private Button mbPolitiqueBuilding;
    private Button pbPolitiqueBuilding;
    private Button mbSocialBuilding;
    private Button pbSocialBuilding;
    private Button mbEconomiqueBuilding;
    private Button pbEconomiqueBuilding;

    private Button mbAttractiviteBuilding;
    private Button pbAttractiviteBuilding;
    private Button mbFluiditeBuilding;
    private Button pbFluiditeBuilding;
    private Button mbEnvironnementalBuilding;
    private Button pbEnvironnementalBuilding;

    private Button mbScoreLogistiqueBuilding;
    private Button pbScoreLogistiqueBuilding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_building);

        name = findViewById(R.id.nameBuildingEdit);

        description = findViewById(R.id.descriptionBuildingEdit);

        politique = findViewById(R.id.politiqueBuildingEdit);
        politique.setText("0");
        mbPolitiqueBuilding = findViewById(R.id.mbPolitiqueBuilding);
        mbPolitiqueBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                politique.setText(valueOf(Integer.parseInt(valueOf(politique.getText())) - 1));
            }
        });
        pbPolitiqueBuilding = findViewById(R.id.pbPolitiqueBuilding);
        pbPolitiqueBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                politique.setText(valueOf(Integer.parseInt(valueOf(politique.getText())) + 1));
            }
        });

        social = findViewById(R.id.socialBuildingEdit);
        social.setText("0");
        mbSocialBuilding = findViewById(R.id.mbSocialBuilding);
        mbSocialBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                social.setText(valueOf(Integer.parseInt(valueOf(social.getText())) - 1));
            }
        });
        pbSocialBuilding = findViewById(R.id.pbSocialBuilding);
        pbSocialBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                social.setText(valueOf(Integer.parseInt(valueOf(social.getText())) + 1));
            }
        });

        economique = findViewById(R.id.economiqueBuildingEdit);
        economique.setText("0");
        mbEconomiqueBuilding = findViewById(R.id.mbEconomiqueBuilding);
        mbEconomiqueBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                economique.setText(valueOf(Integer.parseInt(valueOf(economique.getText())) - 1));
            }
        });
        pbEconomiqueBuilding = findViewById(R.id.pbEconomiqueBuilding);
        pbEconomiqueBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                economique.setText(valueOf(Integer.parseInt(valueOf(economique.getText())) + 1));
            }
        });

        attractivite = findViewById(R.id.attractiviteBuildingEdit);
        attractivite.setText("0");
        mbAttractiviteBuilding = findViewById(R.id.mbAttractiviteBuilding);
        mbAttractiviteBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attractivite.setText(valueOf(Integer.parseInt(valueOf(attractivite.getText())) - 1));
            }
        });
        pbAttractiviteBuilding = findViewById(R.id.pbAttractiviteBuilding);
        pbAttractiviteBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attractivite.setText(valueOf(Integer.parseInt(valueOf(attractivite.getText())) + 1));
            }
        });

        fluidite = findViewById(R.id.fluiditeBuildingEdit);
        fluidite.setText("0");
        mbFluiditeBuilding = findViewById(R.id.mbFluiditeBuilding);
        mbFluiditeBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fluidite.setText(valueOf(Integer.parseInt(valueOf(fluidite.getText())) - 1));
            }
        });
        pbFluiditeBuilding = findViewById(R.id.pbFluiditeBuilding);
        pbFluiditeBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fluidite.setText(valueOf(Integer.parseInt(valueOf(fluidite.getText())) + 1));
            }
        });

        environnemental = findViewById(R.id.environnementalBuildingEdit);
        environnemental.setText("0");
        mbEnvironnementalBuilding = findViewById(R.id.mbEnvironnementalBuilding);
        mbEnvironnementalBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                environnemental.setText(valueOf(Integer.parseInt(valueOf(environnemental.getText())) - 1));
            }
        });
        pbEnvironnementalBuilding = findViewById(R.id.pbEnvironnementalBuilding);
        pbEnvironnementalBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                environnemental.setText(valueOf(Integer.parseInt(valueOf(environnemental.getText())) + 1));
            }
        });

        scoreLogistique = findViewById(R.id.scoreLogistiqueBuildingEdit);
        scoreLogistique.setText("0");
        mbScoreLogistiqueBuilding = findViewById(R.id.mbScoreLogistiqueBuilding);
        mbScoreLogistiqueBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreLogistique.setText(valueOf(Integer.parseInt(valueOf(scoreLogistique.getText())) - 1));
            }
        });
        pbScoreLogistiqueBuilding = findViewById(R.id.pbScoreLogistiqueBuilding);
        pbScoreLogistiqueBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreLogistique.setText(valueOf(Integer.parseInt(valueOf(scoreLogistique.getText())) + 1));
            }
        });

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
                name.setError("Il y a déja un batiment avec le même nom");
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