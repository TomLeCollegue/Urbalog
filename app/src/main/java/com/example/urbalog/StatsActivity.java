package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.urbalog.stats.NbPlayerByGameActivity;
import com.example.urbalog.stats.PcsPercentActivity;


public class StatsActivity extends AppCompatActivity {

    private Button pcsPercent;
    private Button nbPlayerGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        pcsPercent = (Button) findViewById(R.id.pcsPercent);
        nbPlayerGame = (Button) findViewById(R.id.nbPlayerGame);


        pcsPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, PcsPercentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nbPlayerGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, NbPlayerByGameActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(StatsActivity.this, ConfigurationActivity.class);
        startActivity(intent);
        finish();
        return;
    }

}