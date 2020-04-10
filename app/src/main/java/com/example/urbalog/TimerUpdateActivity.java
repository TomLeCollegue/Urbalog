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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_update);
        numberUpdateTimer = findViewById(R.id.numberUpdateTimer);
    }

    public void validerTimerUpdate(View view){
        boolean finish = true;
        String finalTime = numberUpdateTimer.getText().toString().trim();

        if(finalTime.matches("")){
            numberUpdateTimer.setError("champs vide");
            finish = false;
        }

        if(finish) {
            AdminConnectionActivity.net.setTURN_TIME(Integer.parseInt(finalTime));
            Log.i("Urbalog", "Turn game: " + AdminConnectionActivity.net.getTURN_TIME());
            Toast.makeText(this, "Le temps de mise a été mis a jour", Toast.LENGTH_SHORT).show();
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