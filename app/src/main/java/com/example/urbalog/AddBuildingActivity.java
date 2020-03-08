package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    /**
     * Funtion to validate the form
     * check if name is not already taken
     * check if fields are not empty
     * call when we click on validate button
     * listener on XML
     *
     * @param view
     */
    public void validerBuildingAdd(View view) {
        boolean finish = true;
        String finalName = name.getText().toString().trim();
        String finalDescription = description.getText().toString().trim();
        String finalPolitique = politique.getText().toString().trim();
        String finalSocial = social.getText().toString().trim();
        String finalEconomique = economique.getText().toString().trim();
        String finalAttractivite = attractivite.getText().toString().trim();
        String finalFluidite = fluidite.getText().toString().trim();
        String finalEnvironnemental = environnemental.getText().toString().trim();
        String finalScoreLogistique = scoreLogistique.getText().toString().trim();
        String finalExplicationLogistique = explicationLogistique.getText().toString().trim();


        if(finalName.matches("")){
            name.setError("champs vide");
            finish = false;
        }
        if(finalDescription.matches("")){
            description.setError("champs vide");
            finish = false;
        }
        if(finalPolitique.matches("")){
            politique.setError("champs vide");
            finish = false;
        }
        if(finalSocial.matches("")){
            social.setError("champs vide");
            finish = false;
        }
        if(finalEconomique.matches("")){
            economique.setError("champs vide");
            finish = false;
        }
        if(finalAttractivite.matches("")){
            attractivite.setError("champs vide");
            finish = false;
        }
        if(finalFluidite.matches("")){
            fluidite.setError("champs vide");
            finish = false;
        }
        if(finalEnvironnemental.matches("")){
            environnemental.setError("champs vide");
            finish = false;
        }
        if(finalScoreLogistique.matches("")){
            scoreLogistique.setError("champs vide");
            finish = false;
        }
        if(finalExplicationLogistique.matches("")){
            explicationLogistique.setError("champs vide");
            finish = false;
        }

        if(finish == true){
            if(JsonBuilding.buildingAlreadyInList(finalName)){
                name.setError("Il y a déja un batiment avec le même nom");
            }else{
                Building newBuilding = new Building(finalName, finalDescription, Integer.parseInt(finalPolitique), Integer.parseInt(finalSocial),  Integer.parseInt(finalEconomique), Integer.parseInt(finalAttractivite), Integer.parseInt(finalFluidite), Integer.parseInt(finalEnvironnemental), Integer.parseInt(finalScoreLogistique), finalExplicationLogistique);
                JsonBuilding.writeBuilding(newBuilding);
                Toast.makeText(this, "Le batiment à été ajouté", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddBuildingActivity.this, ConfigurationActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    /**
     * fonction to finish activity
     * call when we click on back button
     */
    public void onBackPressed(){
        Intent intent = new Intent(AddBuildingActivity.this, ConfigurationActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}