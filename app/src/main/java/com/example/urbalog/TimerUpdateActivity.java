package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class TimerUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_update);
    }
    public void onBackPressed(){
        Intent intent = new Intent(TimerUpdateActivity.this, ConfigurationActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}
