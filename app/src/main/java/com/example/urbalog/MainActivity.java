package com.example.urbalog;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urbalog.R;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button bAdvertising;
    private Button bDiscovery;
    private Button bDisconect;
    private Button bSend;
    private static TextView tData;
    private TextView tAdvertising;
    private TextView tDiscovery;
    private static TextView tConnected;
    private com.example.urbalog.NetworkHelper net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bAdvertising = (Button)findViewById(R.id.advertising);
        bDiscovery = (Button)findViewById(R.id.discovery);
        bDisconect = (Button)findViewById(R.id.disconectBut);
        bSend = (Button)findViewById(R.id.dataButton);
        tConnected = (TextView)findViewById(R.id.connectedText);
        tData = (TextView)findViewById(R.id.textContent);
        net = new com.example.urbalog.NetworkHelper(getApplicationContext());

        bAdvertising.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net.hostGame(getCurrentFocus());
            }
        });
        bDiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net.searchGame(getCurrentFocus());
            }
        });
        bDisconect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net.disconnect(getCurrentFocus());
            }
        });
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    net.sendToAllClients("test");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if (!hasPermissions(this, NetworkHelper.getRequiredPermissions())) {
            requestPermissions(NetworkHelper.getRequiredPermissions(), NetworkHelper.getRequestCodeRequiredPermissions());
        }
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @CallSuper
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != NetworkHelper.getRequestCodeRequiredPermissions()) {
            return;
        }

        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this,"Permissions manquantes", Toast.LENGTH_LONG).show();
                finish();
                return;
            }
        }
        recreate();
    }

    public static void setStatusText(String text) {
        tConnected.setText(text);
    }

    public static void setDataText(String text) {
        tData.setText(text);
    }
}
