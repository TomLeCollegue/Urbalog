package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.urbalog.Class.Player;

public class PlayerConnexionActivity extends AppCompatActivity {
    public static NetworkHelper net; // Temporary until class can be parcelable and send in intent

    private Button bCancel;
    private Button bSearch;
    private static TextView tStatus;

    private Player player;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_connexion);

        this.bSearch = (Button) findViewById(R.id.button_research);
        this.bCancel = (Button) findViewById(R.id.button_cancel_research);
        this.tStatus = (TextView) findViewById(R.id.textStatus);
        this.name = (TextView) findViewById(R.id.player_name);


        net = new NetworkHelper(this);
        net.setHost(false);
        
        player = (Player) getIntent().getSerializableExtra("player");

        name.setText("bonjour " + player.getName());

        this.bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net.searchGame(getCurrentFocus());
            }
        });
        this.bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net.disconnectFromAllEndpoints();
                setStatus("Disconnected");
            }
        });
    }

    public static void setStatus(String t){
        tStatus.setText(t);
    }
}
