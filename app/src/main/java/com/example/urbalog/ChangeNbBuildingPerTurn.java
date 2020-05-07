package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ChangeNbBuildingPerTurn extends AppCompatActivity {

    private EditText nbBuildingPerTurn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nb_building_per_turn);

        nbBuildingPerTurn = findViewById(R.id.inputNbBuilding);
        nbBuildingPerTurn.setTransformationMethod(null);

    }
}
