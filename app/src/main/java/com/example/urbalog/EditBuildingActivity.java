package com.example.urbalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urbalog.Class.Building;
import com.example.urbalog.Json.JsonBuilding;

import static java.lang.String.valueOf;

public class EditBuildingActivity extends AppCompatActivity {

    private TextView name;
    private TextView description;
    private TextView politique;
    private TextView social;
    private TextView economique;
    private TextView attractivite;
    private TextView fluidite;
    private TextView environnemental;

    private TextView scoreLogistique;
    private TextView explicationLogistique;

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
        social.setText(valueOf(building.getCoutSocial()));
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
        economique.setText(valueOf(building.getCoutEconomique()));
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
        attractivite.setText(valueOf(building.getEffetAttractivite()));
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
        fluidite.setText(valueOf(building.getEffetFluidite()));
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
        environnemental.setText(valueOf(building.getEffetEnvironnemental()));
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
        scoreLogistique.setText(valueOf(building.getScoreLogistique()));
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
        explicationLogistique.setText(building.getExplicationLogistique());
        explicationLogistique.setHint(building.getExplicationLogistique());
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
    public void validerBuildingEdit(View view) {
        boolean finish = true;
        String finalName = name.getText().toString().trim();
        String finalDescription = description.getText().toString().trim();
        String finalExplicationLogistique = explicationLogistique.getText().toString().trim();

        if(finalName.matches(""))
        {
            name.setError("champs vide");
            finish = false;
        }
        if(finalDescription.matches(""))
        {
            description.setError("champs vide");
            finish = false;
        }
        if(finalExplicationLogistique.matches(""))
        {
            explicationLogistique.setError("champs vide");
            finish = false;
        }

        if(finish == true){
            if((building.getName()).equals(finalName))
            {
                Building newBuilding = new Building(finalName, finalDescription, Integer.parseInt(politique.getText().toString()), Integer.parseInt(social.getText().toString()),  Integer.parseInt(economique.getText().toString()), Integer.parseInt(attractivite.getText().toString()), Integer.parseInt(fluidite.getText().toString()), Integer.parseInt(environnemental.getText().toString()), Integer.parseInt(scoreLogistique.getText().toString()), finalExplicationLogistique);
                JsonBuilding.modificationBuilding(newBuilding, name.getHint().toString());
                Intent intent = new Intent(EditBuildingActivity.this, ListBuildingsActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                if(JsonBuilding.buildingAlreadyInList(finalName)){
                    name.setError("Il y a déja un batiment avec le même nom");
                }
                else{
                    Toast.makeText(this, "Le batiment a été modifié", Toast.LENGTH_SHORT).show();
                    Building newBuilding = new Building(finalName, finalDescription, Integer.parseInt(politique.getText().toString()), Integer.parseInt(social.getText().toString()),  Integer.parseInt(economique.getText().toString()), Integer.parseInt(attractivite.getText().toString()), Integer.parseInt(fluidite.getText().toString()), Integer.parseInt(environnemental.getText().toString()), Integer.parseInt(scoreLogistique.getText().toString()), finalExplicationLogistique);
                    JsonBuilding.modificationBuilding(newBuilding, name.getHint().toString());
                    Intent intent = new Intent(EditBuildingActivity.this, ListBuildingsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    /**
     * fonction to finish activity
     * call when we click on back button
     */
    public void onBackPressed(){
        Intent intent = new Intent(EditBuildingActivity.this, ListBuildingsActivity.class);
        startActivity(intent);
        finish();
        return;
    }

    /**
     * function to init the building values
     * call when button is clicked
     * 
     * listener on XML
     * @param view
     */
    public void initBuildingEdit(View view) {
        name.setText(building.getName());
        description.setText(building.getDescription());
        politique.setText(valueOf(building.getCoutPolitique()));
        social.setText(valueOf(building.getCoutSocial()));
        economique.setText(valueOf(building.getCoutEconomique()));
        attractivite.setText(valueOf(building.getEffetAttractivite()));
        fluidite.setText(valueOf(building.getEffetFluidite()));
        environnemental.setText(valueOf(building.getEffetEnvironnemental()));
        scoreLogistique.setText(valueOf(building.getScoreLogistique()));
        explicationLogistique.setText(building.getExplicationLogistique());
        Toast.makeText(this, "Valeurs réinitialisées", Toast.LENGTH_SHORT).show();
    }
}