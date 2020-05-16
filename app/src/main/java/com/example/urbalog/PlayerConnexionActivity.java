package com.example.urbalog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urbalog.Class.Player;

public class PlayerConnexionActivity extends AppCompatActivity {
    private Button bCancel;
    private Button bSearch;
    private static TextView tStatus;

    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_connexion);

        this.bSearch = findViewById(R.id.button_research);
        this.bCancel = findViewById(R.id.button_cancel_research);
        this.name = findViewById(R.id.player_name);
        tStatus = findViewById(R.id.textStatus);

        if(MainActivity.net == null || MainActivity.net.isHost()) {
            MainActivity.net = new NetworkHelper(this, false);
            MainActivity.net.setHost(false);
        }
        MainActivity.net.setPlayer((Player) getIntent().getSerializableExtra("player"));

        if(MainActivity.net.getPlayer().getName() == null){
            name.setText("Formulaire pas rempli");
        }else{
            name.setText(String.format("Bonjour %s", MainActivity.net.getPlayer().getFirstName()));
            Log.d("debug", MainActivity.net.getPlayer().toString());
        }

        this.bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.net.searchGame();
            }
        });
        this.bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.net.disconnectFromAllEndpoints();
                setStatus("Disconnected");
            }
        });
    }

    public static void setStatus(String t){
        tStatus.setText(t);
    }
}
