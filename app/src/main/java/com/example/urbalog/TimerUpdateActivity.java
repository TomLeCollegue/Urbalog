package com.example.urbalog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TimerUpdateActivity extends AppCompatActivity {

    private EditText numberUpdateTimer;
    private EditText gameUpdateTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_update);
        numberUpdateTimer = findViewById(R.id.numberUpdateTimer);
        gameUpdateTimer = findViewById(R.id.gameTimerInput);
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
