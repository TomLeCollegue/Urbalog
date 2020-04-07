package com.example.urbalog;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity {

    public final static String VERSION = "© Alpha V3.113";
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createLocationRequest();
        setContentView(R.layout.activity_main);

        TextView version_text = findViewById(R.id.textView_version);
        version_text.setText(VERSION);
    }

    public void changeActivityPlayer(View view) {
        checkGPS();
    }


    public void changeActivityAdmin(View view) {
        Intent myIntent = new Intent(this, AdminConnectionActivity.class);
        startActivity(myIntent);
    }

    /**
     * Request needed permissions to user when application start if they are not already allowed
     */
    @Override
    protected void onStart()
    {
        super.onStart();
        if (!hasPermissions(this, NetworkHelper.getRequiredPermissions())) {
            requestPermissions(NetworkHelper.getRequiredPermissions(), NetworkHelper.getRequestCodeRequiredPermissions());
        }
    }

    /**
     * Check permissions status for the application
     * @param context
     * @param permissions
     * @return boolean
     */
    private static boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Request needed permissions to user through UI
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @CallSuper
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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

    /**
     * Configure location settings for the app
     */
    private void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000); // Interval between refresh
        locationRequest.setFastestInterval(5000); // Max interval if needed
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // Enable high accuracy mode
    }

    /**
     * Check gps state for player and open dialog if it's disable
     * Launch activity if it's already enabled
     */
    private void checkGPS() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(MainActivity.this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                Intent myIntent = new Intent(getApplicationContext(), FormActivity.class);
                startActivity(myIntent);
            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(MainActivity.this,
                                REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore exception
                    }
                }
            }
        });
    }

    /**
     * Analyse requestcode result when activity result
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CHECK_SETTINGS){
            if(resultCode==RESULT_OK){
                Intent myIntent = new Intent(this, FormActivity.class);
                startActivity(myIntent);
                Log.d("result ok",data.toString());
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "La localisation est nécéssaire pour ce connecter au host, merci de l'activer pour pouvoir jouer", Toast.LENGTH_SHORT).show();
                Log.d("result cancelled",data.toString());
            }
        }
    }
}
