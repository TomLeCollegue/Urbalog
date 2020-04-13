package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DbConfigActivity extends AppCompatActivity {
    private Button resetDb;
    private Button exportCsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_config);

        resetDb = findViewById(R.id.resetDb);
        exportCsv = findViewById(R.id.exportCsv);

        resetDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminConnectionActivity.net.getDb().resetDB();
                Toast.makeText(DbConfigActivity.this, "Base de donnée réinitialisée", Toast.LENGTH_LONG).show();
            }
        });

        exportCsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminConnectionActivity.net.getDb().exportDbToCSV();
                Toast.makeText(DbConfigActivity.this, "Base de donnée exportée", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(DbConfigActivity.this, ConfigurationActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}
