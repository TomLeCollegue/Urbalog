package com.example.urbalog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TimerUpdateActivity extends AppCompatActivity {

    private EditText numberUpdateTimer;
    private EditText gameUpdateTimer;
    private Switch switchGameTimer;
    private Switch switchTurnTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_update);
        numberUpdateTimer = findViewById(R.id.numberUpdateTimer);
        gameUpdateTimer = findViewById(R.id.gameTimerInput);
        switchGameTimer = findViewById(R.id.disableGameTimer);
        switchTurnTimer = findViewById(R.id.disableTurnTimer);

        numberUpdateTimer.setText(String.valueOf(AdminConnectionActivity.net.getTURN_TIME()));
        gameUpdateTimer.setText(String.valueOf(AdminConnectionActivity.net.getGAME_TIME()));

        if(!AdminConnectionActivity.net.isGAME_TIME_ENABLE()){
            switchGameTimer.setChecked(true);
            gameUpdateTimer.setEnabled(false);
        }
        if(!AdminConnectionActivity.net.isTURN_TIME_ENABLE()){
            switchTurnTimer.setChecked(true);
            numberUpdateTimer.setEnabled(false);
        }

        switchGameTimer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AdminConnectionActivity.net.setGAME_TIME_ENABLE(false);
                    gameUpdateTimer.setEnabled(false);
                }
                else{
                    AdminConnectionActivity.net.setGAME_TIME_ENABLE(true);
                    gameUpdateTimer.setEnabled(true);
                }
            }
        });
        switchTurnTimer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AdminConnectionActivity.net.setTURN_TIME_ENABLE(false);
                    numberUpdateTimer.setEnabled(false);
                }
                else{
                    AdminConnectionActivity.net.setTURN_TIME_ENABLE(true);
                    numberUpdateTimer.setEnabled(true);
                }
            }
        });
    }

    public void validerTimerUpdate(View view){
        boolean finish = true;
        String finalTurnTime = numberUpdateTimer.getText().toString().trim();
        String finalGameTime = gameUpdateTimer.getText().toString().trim();

        if(finalTurnTime.matches("")){
            numberUpdateTimer.setError("Champs manquant");
            finish = false;
        } else if (finalGameTime.matches("")) {
            gameUpdateTimer.setError("Champs manquant");
            finish = false;
        }

        if(finish) {
            AdminConnectionActivity.net.setTURN_TIME(Integer.parseInt(finalTurnTime));
            AdminConnectionActivity.net.setGAME_TIME(Integer.parseInt(finalGameTime));
            Toast.makeText(this, "Les timers ont bien été mis à jour !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TimerUpdateActivity.this, ConfigurationActivity.class);
            startActivity(intent);
            finish();
        }
        
    }
    public void onBackPressed(){
        Intent intent = new Intent(TimerUpdateActivity.this, ConfigurationActivity.class);
        startActivity(intent);
        finish();
    }
}
