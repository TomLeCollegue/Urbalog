package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PlayerConnexionActivity extends AppCompatActivity {
    public static NetworkHelper net; // Temporary until class can be parcelable and send in intent

    private Button bCancel;
    private Button bSearch;
    private ImageButton bRefresh;
    private static TextView tStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_connexion);

        this.bSearch = (Button) findViewById(R.id.button_research);
        this.bCancel = (Button) findViewById(R.id.button_cancel_research);
        this.bRefresh = (ImageButton) findViewById(R.id.button_refresh_list);
        this.tStatus = (TextView) findViewById(R.id.textStatus);
        net = new NetworkHelper(this);

        this.bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net.searchGame(getCurrentFocus());
            }
        });
        this.bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net.stop();
                setStatus("Disconnected");
            }
        });
    }

    public static void setStatus(String t){
        tStatus.setText(t);
    }
}
