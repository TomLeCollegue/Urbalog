package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Boolean.valueOf;

public class ChangeNbBuildingPerTurn extends AppCompatActivity {

    private EditText nbBuildingPerTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nb_building_per_turn);

        nbBuildingPerTurn = findViewById(R.id.inputNbBuilding);
        nbBuildingPerTurn.setText(String.valueOf(MainActivity.net.getNbBuildingsPerTurn()));


    }

    public void confirmNbBuilding(View view){
        String chosenNbBuilding = nbBuildingPerTurn.getText().toString().trim();
        Log.e("test",chosenNbBuilding);
        boolean finish = true;

        if (chosenNbBuilding.matches("")){
            Toast.makeText(this, "Le champ est vide", Toast.LENGTH_LONG).show();
            finish = false;
        }
        else if (Integer.parseInt(chosenNbBuilding) > 5){
            Toast.makeText(this, "Le maximum possible est de 5", Toast.LENGTH_LONG).show();
            finish = false;
        }

        if (finish){
            MainActivity.net.setNbBuildingsPerTurn(Integer.parseInt(chosenNbBuilding));
            Toast.makeText(this, "Le nombre d'aménagements possible que l'on peut construire par tour a été mis à jour", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ChangeNbBuildingPerTurn.this, BuildingConfigActivity.class);
        startActivity(intent);
        finish();
    }


}
