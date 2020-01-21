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

public class MainActivity extends AppCompatActivity {

    private Button bAdvertising;
    private Button bDiscovery;
    private Button bDisconect;
    private TextView tAdvertising;
    private TextView tDiscovery;
    private static TextView tConnected;
    private com.example.urbalog.NetworkHelper net;

    private static final String[] REQUIRED_PERMISSIONS =
            new String[] {
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            };

    private static final int REQUEST_CODE_REQUIRED_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bAdvertising = (Button)findViewById(R.id.advertising);
        bDiscovery = (Button)findViewById(R.id.discovery);
        bDisconect = (Button)findViewById(R.id.disconectBut);
        tConnected = (TextView)findViewById(R.id.connectedText);
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
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if (!hasPermissions(this, REQUIRED_PERMISSIONS)) {
            requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_REQUIRED_PERMISSIONS);
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

        if (requestCode != REQUEST_CODE_REQUIRED_PERMISSIONS) {
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

}
