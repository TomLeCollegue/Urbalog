package com.example.urbalog;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.TransferPackage;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {

    private TextView textPoliticalResssourcesBuilding1;
    private TextView textEcoResssourcesBuilding1;
    private TextView textSocialRessourcesBuilding1;
    private TextView textPoliticalResssourcesBuilding2;
    private TextView textEcoResssourcesBuilding2;
    private TextView textSocialRessourcesBuilding2;
    private TextView textPoliticalResssourcesBuilding3;
    private TextView textEcoResssourcesBuilding3;
    private TextView textSocialRessourcesBuilding3;
    private TextView textPoliticalResssourcesBuilding4;
    private TextView textEcoResssourcesBuilding4;
    private TextView textSocialRessourcesBuilding4;
    private TextView textPoliticalResssourcesBuilding5;
    private TextView textEcoResssourcesBuilding5;
    private TextView textSocialRessourcesBuilding5;

    private TextView textAttractBuilding1;
    private TextView textEnviBuilding1;
    private TextView textTraficBuilding1;
    private TextView textAttractBuilding2;
    private TextView textEnviBuilding2;
    private TextView textTraficBuilding2;
    private TextView textAttractBuilding3;
    private TextView textEnviBuilding3;
    private TextView textTraficBuilding3;
    private TextView textAttractBuilding4;
    private TextView textEnviBuilding4;
    private TextView textTraficBuilding4;
    private TextView textAttractBuilding5;
    private TextView textEnviBuilding5;
    private TextView textTraficBuilding5;

    private TextView textNameBuilding1;
    private TextView textNameBuilding2;
    private TextView textNameBuilding3;
    private TextView textNameBuilding4;
    private TextView textNameBuilding5;

    private TextView textScoreCityEnvi;
    private TextView textScoreCityTrafic;
    private TextView textScoreCityAttract;

    private ImageView icoObjectifLeftRole;
    private ImageView icoObjectifRightRole;

    private TextView textTitleRole;

    private ImageView icoRessourceLeftRole;
    private ImageView icoRessourceRightRole;
    private TextView textRessourceLeftRole;
    private TextView textRessourceRightRole;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textPoliticalResssourcesBuilding1 = (TextView) findViewById(R.id.text_political_resssources_building_1);
        textPoliticalResssourcesBuilding2 = (TextView) findViewById(R.id.text_political_resssources_building_2);
        textPoliticalResssourcesBuilding3 = (TextView) findViewById(R.id.text_political_resssources_building_3);
        textPoliticalResssourcesBuilding4 = (TextView) findViewById(R.id.text_political_resssources_building_4);
        textPoliticalResssourcesBuilding5 = (TextView) findViewById(R.id.text_political_resssources_building_5);
        textEcoResssourcesBuilding1 = (TextView) findViewById(R.id.text_eco_resssources_building_1);
        textEcoResssourcesBuilding2 = (TextView) findViewById(R.id.text_eco_resssources_building_2);
        textEcoResssourcesBuilding3 = (TextView) findViewById(R.id.text_eco_resssources_building_3);
        textEcoResssourcesBuilding4 = (TextView) findViewById(R.id.text_eco_resssources_building_4);
        textEcoResssourcesBuilding5 = (TextView) findViewById(R.id.text_eco_resssources_building_5);
        textSocialRessourcesBuilding1 = (TextView) findViewById(R.id.text_social_ressources_building_1);
        textSocialRessourcesBuilding2 = (TextView) findViewById(R.id.text_social_ressources_building_2);
        textSocialRessourcesBuilding3 = (TextView) findViewById(R.id.text_social_ressources_building_3);
        textSocialRessourcesBuilding4 = (TextView) findViewById(R.id.text_social_ressources_building_4);
        textSocialRessourcesBuilding5 = (TextView) findViewById(R.id.text_social_ressources_building_5);

        textAttractBuilding1 = (TextView) findViewById(R.id.text_attract_building_1);
        textAttractBuilding2 = (TextView) findViewById(R.id.text_attract_building_2);
        textAttractBuilding3 = (TextView) findViewById(R.id.text_attract_building_3);
        textAttractBuilding4 = (TextView) findViewById(R.id.text_attract_building_4);
        textAttractBuilding5 = (TextView) findViewById(R.id.text_attract_building_5);

        textEnviBuilding1 = (TextView) findViewById(R.id.text_envi_building_1);
        textEnviBuilding2 = (TextView) findViewById(R.id.text_envi_building_2);
        textEnviBuilding3 = (TextView) findViewById(R.id.text_envi_building_3);
        textEnviBuilding4 = (TextView) findViewById(R.id.text_envi_building_4);
        textEnviBuilding5 = (TextView) findViewById(R.id.text_envi_building_5);

        textTraficBuilding1 = (TextView) findViewById(R.id.text_trafic_building_1);
        textTraficBuilding2 = (TextView) findViewById(R.id.text_trafic_building_2);
        textTraficBuilding3 = (TextView) findViewById(R.id.text_trafic_building_3);
        textTraficBuilding4 = (TextView) findViewById(R.id.text_trafic_building_4);
        textTraficBuilding5 = (TextView) findViewById(R.id.text_trafic_building_5);

        textScoreCityEnvi = (TextView) findViewById(R.id.text_score_city_envi);
        textScoreCityAttract = (TextView) findViewById(R.id.text_score_city_attract);
        textScoreCityTrafic = (TextView) findViewById(R.id.text_score_city_trafic);

        textNameBuilding1 = (TextView) findViewById(R.id.text_name_building_1);
        textNameBuilding2 = (TextView) findViewById(R.id.text_name_building_2);
        textNameBuilding3 = (TextView) findViewById(R.id.text_name_building_3);
        textNameBuilding4 = (TextView) findViewById(R.id.text_name_building_4);
        textNameBuilding5 = (TextView) findViewById(R.id.text_name_building_5);

        icoObjectifLeftRole = (ImageView) findViewById(R.id.ico_objectif_left_role);
        icoObjectifRightRole = (ImageView) findViewById(R.id.ico_objectif_right_role);
        textTitleRole = (TextView) findViewById(R.id.text_title_role);
        icoRessourceLeftRole = (ImageView) findViewById(R.id.ico_ressource_left_role);
        icoRessourceRightRole = (ImageView) findViewById(R.id.ico_ressource_right_role);
        textRessourceLeftRole = (TextView) findViewById(R.id.text_ressource_left_role);
        textRessourceRightRole = (TextView) findViewById(R.id.text_ressource_right_role);

        Market M = new Market();

        fillInfoNameBuildingsView(M);

    }

    void fillInfoNameBuildingsView(Market M){
        ArrayList<Building> ListBuildings;
        ListBuildings = M.getBuildings();

        textNameBuilding1.setText(ListBuildings.get(1).getName());
        textNameBuilding2.setText(ListBuildings.get(2).getName());
        textNameBuilding3.setText(ListBuildings.get(3).getName());
        textNameBuilding4.setText(ListBuildings.get(4).getName());
        textNameBuilding5.setText(ListBuildings.get(5).getName());
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
}
