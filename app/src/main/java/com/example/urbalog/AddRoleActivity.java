package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.String.valueOf;

public class AddRoleActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_role);

        name = findViewById(R.id.nameRoleEdit);

        description = findViewById(R.id.descriptionRoleEdit);

        social = findViewById(R.id.socialRoleEdit);
        social.setText("0");
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
        politique.setText("0");
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
        economique.setText("0");
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

    }


    /**
     * fonction to finish activity
     * call when we click on back button
     */
    public void onBackPressed(){
        Intent intent = new Intent(AddRoleActivity.this, ConfigurationActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}