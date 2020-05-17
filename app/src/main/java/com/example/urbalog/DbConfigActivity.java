package com.example.urbalog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DbConfigActivity extends AppCompatActivity {
    private Button resetDb;
    private Button exportCsv;
    private Button exportJson;
    private Button syncDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_config);

        resetDb = findViewById(R.id.resetDb);
        exportCsv = findViewById(R.id.exportCsv);
        exportJson = findViewById(R.id.exportJson);
        syncDb = findViewById(R.id.syncDb);

        resetDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });

        exportCsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.net.getDb().exportDbToCSV();
                Toast.makeText(DbConfigActivity.this, "Base de donnée exportée", Toast.LENGTH_LONG).show();
            }
        });

        exportJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.net.getDb().exportDbToJSON();
                Toast.makeText(DbConfigActivity.this, "Base de donnée exportée", Toast.LENGTH_LONG).show();
            }
        });

        syncDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.net.getDb().syncDb();
                Toast.makeText(DbConfigActivity.this, "Base de donnée synchronisée", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Create AlertDialog when player hit reset db button
     * for confimation
     *
     * @return Reset db confirmation alert dialog
     */
    private AlertDialog AskOption()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Réinitialiser bdd")
                .setMessage("Êtes-vous sûr de vouloir réinitialiser la base de donnée locale?\nCette action entraînera la perte de toutes les données non exportées ou synchronisées.")
                .setIcon(R.drawable.warning_icon)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        MainActivity.net.getDb().resetDB();
                        Toast.makeText(DbConfigActivity.this, "Base de donnée réinitialisée", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
    }

    public void onBackPressed(){
        Intent intent = new Intent(DbConfigActivity.this, ConfigurationActivity.class);
        startActivity(intent);
        finish();
    }
}
