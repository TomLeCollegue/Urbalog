package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.Role;
import com.example.urbalog.Json.JsonBuilding;
import com.example.urbalog.Json.JsonRole;

public class PlayerViewActivity extends AppCompatActivity {

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


    private PopupWindow popUpBet;
    private Button buttonBetPopup;
    private TextView textNameBuildingPopup;
    private TextView textFinancementTopPopup;
    private TextView textFinancementBotPopup;

    private Market M;
    private ArrayList<Role> Roles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_view_activity);
        JsonBuilding.init(PlayerViewActivity.this);
        JsonBuilding.addBuildings();

        JsonRole.init(PlayerViewActivity.this);
        JsonRole.addRoles();




        M = new Market();
        Roles = JsonRole.readRole();






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



        textNameBuilding1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v, 0);
            }
        });

        textNameBuilding2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v, 1);
            }
        });

        textNameBuilding3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v, 2);
            }
        });

        textNameBuilding4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v, 3);
            }
        });

        textNameBuilding5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v, 4);
            }
        });



        fillInfosView();

    }

    void fillInfosView(){
        ArrayList<Building> ListBuildings;
        ListBuildings = M.getBuildings();

        Integer betPolitic1 = 0;
        Integer betPolitic2 = 0;
        Integer betPolitic3 = 0;
        Integer betPolitic4 = 0;
        Integer betPolitic5 = 0;
        Integer betEco1 = 0;
        Integer betEco2 = 0;
        Integer betEco3 = 0;
        Integer betEco4 = 0;
        Integer betEco5 = 0;
        Integer betSocial1 = 0;
        Integer betSocial2 = 0;
        Integer betSocial3 = 0;
        Integer betSocial4 = 0;
        Integer betSocial5 = 0;


        textNameBuilding1.setText(ListBuildings.get(0).getName());
        textNameBuilding2.setText(ListBuildings.get(1).getName());
        textNameBuilding3.setText(ListBuildings.get(2).getName());
        textNameBuilding4.setText(ListBuildings.get(3).getName());
        textNameBuilding5.setText(ListBuildings.get(4).getName());

        textEnviBuilding1.setText(String.valueOf(ListBuildings.get(0).getEffetEnvironnemental()));
        textEnviBuilding2.setText(String.valueOf(ListBuildings.get(1).getEffetEnvironnemental()));
        textEnviBuilding3.setText(String.valueOf(ListBuildings.get(2).getEffetEnvironnemental()));
        textEnviBuilding4.setText(String.valueOf(ListBuildings.get(3).getEffetEnvironnemental()));
        textEnviBuilding5.setText(String.valueOf(ListBuildings.get(4).getEffetEnvironnemental()));
        textAttractBuilding1.setText(String.valueOf(ListBuildings.get(0).getEffetAttractivite()));
        textAttractBuilding2.setText(String.valueOf(ListBuildings.get(1).getEffetAttractivite()));
        textAttractBuilding3.setText(String.valueOf(ListBuildings.get(2).getEffetAttractivite()));
        textAttractBuilding4.setText(String.valueOf(ListBuildings.get(3).getEffetAttractivite()));
        textAttractBuilding5.setText(String.valueOf(ListBuildings.get(4).getEffetAttractivite()));
        textTraficBuilding1.setText(String.valueOf(ListBuildings.get(0).getEffetFluidite()));
        textTraficBuilding2.setText(String.valueOf(ListBuildings.get(1).getEffetFluidite()));
        textTraficBuilding3.setText(String.valueOf(ListBuildings.get(2).getEffetFluidite()));
        textTraficBuilding4.setText(String.valueOf(ListBuildings.get(3).getEffetFluidite()));
        textTraficBuilding5.setText(String.valueOf(ListBuildings.get(4).getEffetFluidite()));

        textPoliticalResssourcesBuilding1.setText(betPolitic1 + "/" + ListBuildings.get(0).getCoutPolitique());
        textPoliticalResssourcesBuilding2.setText(betPolitic2 + "/" + ListBuildings.get(1).getCoutPolitique());
        textPoliticalResssourcesBuilding3.setText(betPolitic3+ "/" + ListBuildings.get(2).getCoutPolitique());
        textPoliticalResssourcesBuilding4.setText(betPolitic4 + "/" + ListBuildings.get(3).getCoutPolitique());
        textPoliticalResssourcesBuilding5.setText(betPolitic5 + "/" + ListBuildings.get(4).getCoutPolitique());

        textSocialRessourcesBuilding1.setText(betSocial1 + "/" + ListBuildings.get(0).getCoutSocial()) ;
        textSocialRessourcesBuilding2.setText(betSocial2 + "/" + ListBuildings.get(1).getCoutSocial()) ;
        textSocialRessourcesBuilding3.setText(betSocial3 + "/" + ListBuildings.get(2).getCoutSocial()) ;
        textSocialRessourcesBuilding4.setText(betSocial4 + "/" + ListBuildings.get(3).getCoutSocial()) ;
        textSocialRessourcesBuilding5.setText(betSocial5 + "/" + ListBuildings.get(4).getCoutSocial()) ;

        textEcoResssourcesBuilding1.setText(betEco1 + "/" + ListBuildings.get(0).getCoutEconomique());
        textEcoResssourcesBuilding2.setText(betEco2 + "/" + ListBuildings.get(1).getCoutEconomique());
        textEcoResssourcesBuilding3.setText(betEco3 + "/" + ListBuildings.get(2).getCoutEconomique());
        textEcoResssourcesBuilding4.setText(betEco4 + "/" + ListBuildings.get(3).getCoutEconomique());
        textEcoResssourcesBuilding5.setText(betEco5 + "/" + ListBuildings.get(4).getCoutEconomique());
   

        textTitleRole.setText(Roles.get(0).getTypeRole());


        //image du Maintenir Role
        if(Roles.get(0).getHold().equals("Attractivité")){
            icoObjectifLeftRole.setImageResource(R.mipmap.img_attract_city_foreground);
        }
        else if (Roles.get(0).getHold().equals("Environnement")){
            icoObjectifLeftRole.setImageResource(R.mipmap.img_environment_city_foreground);
        }
        else{
            icoObjectifLeftRole.setImageResource(R.mipmap.img_fluid_city_foreground);
        }

        //Image du ameliorer Role
        if(Roles.get(0).getImprove().equals("Attractivité")){
            icoObjectifRightRole.setImageResource(R.mipmap.img_impact_on_city_foreground);
        }
        else if (Roles.get(0).getImprove().equals("Environnement")){
            icoObjectifRightRole.setImageResource(R.mipmap.img_environment_city_foreground);
        }
        else{
            icoObjectifRightRole.setImageResource(R.mipmap.img_fluid_city_foreground);
        }




    }

    public void showPopUp(View v, Integer buildingNumber)
    {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.bet_popup, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        popUpBet = new PopupWindow(popUpView, width, height, focusable);

        buttonBetPopup = (Button)popUpView.findViewById(R.id.button_bet_popup);
        textNameBuildingPopup = (TextView)popUpView.findViewById(R.id.text_name_building_popup);

        textFinancementTopPopup = (TextView)popUpView.findViewById(R.id.text_finance_ressource_1_popup);
        textFinancementBotPopup = (TextView)popUpView.findViewById(R.id.text_finance_ressource_2_popup);



        textNameBuildingPopup.setText(M.getBuildings().get(buildingNumber).getName());
        textFinancementTopPopup.setText("0/" + M.getBuildings().get(buildingNumber).getCoutSocial());
        textFinancementBotPopup.setText("0/"+M.getBuildings().get(buildingNumber).getCoutEconomique());

        popUpBet.showAtLocation(v, Gravity.CENTER, 0, 0);
        // Assombrissement de l'arrière plan
        dimBehind(popUpBet);

        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popUpBet.dismiss();
                return true;
            }
        });

        buttonBetPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpBet.dismiss();
            }
        });


    }

    private void dimBehind(PopupWindow popupWindow) {
        View container;
        if (popupWindow.getBackground() == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                container = (View)popupWindow.getContentView().getParent();
            } else {
                container = popupWindow.getContentView();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                container = (View)popupWindow.getContentView().getParent().getParent();
            } else {
                container = (View)popupWindow.getContentView().getParent();
            }
        }
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.5f;
        wm.updateViewLayout(container, p);
        }
}
