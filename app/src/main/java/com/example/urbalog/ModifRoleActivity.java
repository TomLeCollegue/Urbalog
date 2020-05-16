package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Role;
import com.example.urbalog.Json.JsonBuilding;
import com.example.urbalog.Json.JsonRole;

import static java.lang.String.valueOf;

public class ModifRoleActivity extends AppCompatActivity {
    public TextView name;
    public TextView description;
    public TextView social;
    public TextView economique;
    public TextView politique;

    private Button mbSocialRole;
    private Button pbSocialRole;
    private Button mbEconomiqueRole;
    private Button pbEconomiqueRole;
    private Button mbPolitiqueRole;
    private Button pbPolitiqueRole;
    private Button modifRole;

    private RadioGroup improveRadio;
    private RadioGroup holdRadio;

    private RadioButton radioButtonImprove;
    private RadioButton radioButtonHold;

    public Role role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_role);
        role = (Role) getIntent().getSerializableExtra("role");

        name = findViewById(R.id.nameRoleEdit);
        name.setText(role.getTypeRole());
        description = findViewById(R.id.descriptionRoleEdit);        description.setText(role.getObjective());

        social = findViewById(R.id.socialRoleEdit);
        social.setText(""+ role.getTokenSocial());
        mbSocialRole = findViewById(R.id.mbSocialRole);
        mbSocialRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                social.setText(valueOf(Integer.parseInt(valueOf(social.getText())) - 1));
            }
        });
        pbSocialRole = findViewById(R.id.pbSocialRole);
        pbSocialRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                social.setText(valueOf(Integer.parseInt(valueOf(social.getText())) + 1));
            }
        });

        politique = findViewById(R.id.politiqueRoleEdit);
        politique.setText(""+ role.getTokenPolitical());
        mbPolitiqueRole = findViewById(R.id.mbPolitiqueRole);
        mbPolitiqueRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                politique.setText(valueOf(Integer.parseInt(valueOf(politique.getText())) - 1));
            }
        });
        pbPolitiqueRole = findViewById(R.id.pbPolitiqueRole);
        pbPolitiqueRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                politique.setText(valueOf(Integer.parseInt(valueOf(politique.getText())) + 1));
            }
        });

        economique = findViewById(R.id.economiqueRoleEdit);
        economique.setText(""+ role.getTokenEconomical());
        mbEconomiqueRole = findViewById(R.id.mbEconomiqueRole);
        mbEconomiqueRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                economique.setText(valueOf(Integer.parseInt(valueOf(economique.getText())) - 1));
            }
        });
        pbEconomiqueRole = findViewById(R.id.pbEconomiqueRole);
        pbEconomiqueRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                economique.setText(valueOf(Integer.parseInt(valueOf(economique.getText())) + 1));
            }
        });

        improveRadio = findViewById(R.id.improve_radio);
        holdRadio = findViewById(R.id.hold_radio);


        if(role.getImprove().equals("Environnement")){
            improveRadio.check(R.id.improve_Environnement);
        }
        else if(role.getImprove().equals("Attractivité")){
            improveRadio.check(R.id.improve_Attractivite);
        }
        else {
            improveRadio.check(R.id.improve_fluidite);
        }

        if(role.getHold().equals("Environnement")){
            holdRadio.check(R.id.hold_Environnement);
        }
        else if(role.getHold().equals("Attractivité")){
            holdRadio.check(R.id.hold_Attractivite);
        }
        else {
            holdRadio.check(R.id.hold_fluidite);
        }


        modifRole= findViewById(R.id.ButtonRoleEdit);
        modifRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifRoleFunction();
            }
        });

}
    public void modifRoleFunction(){
        boolean finish = true;
        String finalName = name.getText().toString().trim();
        String finalDescription = description.getText().toString().trim();

        int radioButtonHoldId = holdRadio.getCheckedRadioButtonId();
        RadioButton radioButtonHold = (RadioButton) holdRadio.findViewById(radioButtonHoldId);
        String selectedTextHold = (String) radioButtonHold.getText();

        int radioButtonImproveId = improveRadio.getCheckedRadioButtonId();
        RadioButton radioButtonImprove = (RadioButton) improveRadio.findViewById(radioButtonImproveId);
        String selectedTextImprove = (String) radioButtonImprove.getText();

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
        if(selectedTextHold.equals(selectedTextImprove)){
            Toast.makeText(this, "Impossible de mettre les mêmes objectifs", Toast.LENGTH_SHORT).show();
            finish = false;
        }

        if(finish == true){
            if(role.getTypeRole().equals(finalName))
            {


            }
            else{
                if(JsonRole.roleAlreadyInList(finalName)){
                    name.setError("Il y a déja un role avec le même nom");
                }
                else{
                    Toast.makeText(this, "Le role a été modifié", Toast.LENGTH_SHORT).show();

                }
            }
        }


    }



}
