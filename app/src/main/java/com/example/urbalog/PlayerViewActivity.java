package com.example.urbalog;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urbalog.Class.Bet;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.Player;
import com.example.urbalog.Class.Role;
import com.example.urbalog.Class.Signal;
import com.example.urbalog.Class.TransferPackage;

import java.io.IOException;

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

    private Button textNameBuilding1;
    private Button textNameBuilding2;
    private Button textNameBuilding3;
    private Button textNameBuilding4;
    private Button textNameBuilding5;

    private TextView textScoreCityEnvi;
    private TextView textScoreCityTrafic;
    private TextView textScoreCityAttract;
    private TextView textScore;
    private Button bTurn;
    private boolean nextTurn;

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
    private TextView textAvancementRessourceTop;
    private TextView TextAvancementRessourceBot;
    private ImageView icoRessourceTopPopup;
    private ImageView icoRessourceBotPopup;

    private Button buttonMinusTop;
    private Button buttonMinusBot;
    private Button buttonPlusTop;
    private Button buttonPlusBot;

    private Button buttonCityState;

    private TextView textScorePlayer;

    private Integer numBuildingF;

    private Bet mise;

    private String Ressource1;
    private String Ressource2;

    private Integer financementRessource[][];
    boolean buttonState;


    private LinearLayout B1;
    private LinearLayout B2;
    private LinearLayout B3;
    private LinearLayout B4;
    private LinearLayout B5;

    private LinearLayout poli_1;
    private LinearLayout eco_1;
    private LinearLayout social_1;

    private LinearLayout poli_2;
    private LinearLayout eco_2;
    private LinearLayout social_2;

    private LinearLayout poli_3;
    private LinearLayout eco_3;
    private LinearLayout social_3;

    private LinearLayout poli_4;
    private LinearLayout eco_4;
    private LinearLayout social_4;

    private LinearLayout poli_5;
    private LinearLayout eco_5;
    private LinearLayout social_5;





    private TextView descriptionBuildingPopUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("debug", "PlayerViewActivity creation");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_view_activity);

        /* Link player activity to NetworkHelper */
        PlayerConnexionActivity.net.setCurrentPlayerView(this);
        buttonState = true;


        B1 = (LinearLayout) findViewById(R.id.infrastructure_1);
        B2 = (LinearLayout) findViewById(R.id.infrastructure_2);
        B3 = (LinearLayout) findViewById(R.id.infrastructure_3);
        B4 = (LinearLayout) findViewById(R.id.infrastructure_4);
        B5 = (LinearLayout) findViewById(R.id.infrastructure_5);

        poli_1 = (LinearLayout) findViewById(R.id.poli_1);
        eco_1 = (LinearLayout) findViewById(R.id.eco_1);
        social_1 = (LinearLayout) findViewById(R.id.social_1);

        poli_2 = (LinearLayout) findViewById(R.id.poli_2);
        eco_2 = (LinearLayout) findViewById(R.id.eco_2);
        social_2 = (LinearLayout) findViewById(R.id.social_2);

        poli_3 = (LinearLayout) findViewById(R.id.poli_3);
        eco_3 = (LinearLayout) findViewById(R.id.eco_3);
        social_3 = (LinearLayout) findViewById(R.id.social_3);

        poli_4 = (LinearLayout) findViewById(R.id.poli_4);
        eco_4 = (LinearLayout) findViewById(R.id.eco_4);
        social_4 = (LinearLayout) findViewById(R.id.social_4);

        poli_5 = (LinearLayout) findViewById(R.id.poli_5);
        eco_5 = (LinearLayout) findViewById(R.id.eco_5);
        social_5 = (LinearLayout) findViewById(R.id.social_5);


        financementRessource = PlayerConnexionActivity.net.getPlayer().getFinancementRessource();

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
        bTurn = (Button) findViewById(R.id.button_turn);
        buttonCityState = (Button)findViewById(R.id.button_etat_de_la_ville);

        textScore = (TextView) findViewById(R.id.text_score);

        textNameBuilding1 = (Button) findViewById(R.id.text_name_building_1);
        textNameBuilding2 = (Button) findViewById(R.id.text_name_building_2);
        textNameBuilding3 = (Button) findViewById(R.id.text_name_building_3);
        textNameBuilding4 = (Button) findViewById(R.id.text_name_building_4);
        textNameBuilding5 = (Button) findViewById(R.id.text_name_building_5);

        icoObjectifLeftRole = (ImageView) findViewById(R.id.ico_objectif_left_role);
        icoObjectifRightRole = (ImageView) findViewById(R.id.ico_objectif_right_role);
        textTitleRole = (TextView) findViewById(R.id.text_title_role);
        icoRessourceLeftRole = (ImageView) findViewById(R.id.ico_ressource_left_role);
        icoRessourceRightRole = (ImageView) findViewById(R.id.ico_ressource_right_role);
        textRessourceLeftRole = (TextView) findViewById(R.id.text_ressource_left_role);
        textRessourceRightRole = (TextView) findViewById(R.id.text_ressource_right_role);

        textScorePlayer = (TextView) findViewById(R.id.text_score_player);

        textTitleRole.setText(PlayerConnexionActivity.net.getPlayer().getRole().getTypeRole());

        fillRoleCardObjectives();
        fillRessources();
        fillRoleCardRessources();

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

        nextTurn = false;
        bTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nextTurn){
                    try {
                        PlayerConnexionActivity.net.sendToAllClients(Signal.NEXT_TURN);
                        bTurn.setText("Annuler");
                        nextTurn = true;
                    }
                    catch(IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        PlayerConnexionActivity.net.sendToAllClients(Signal.CANCEL_NEXT_TURN);
                        bTurn.setText("Tour suivant");
                        nextTurn = false;
                    }
                    catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        buttonCityState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cityIntent = new Intent(PlayerViewActivity.this, CityProgressionActivity.class);
                startActivity(cityIntent);
            }
        });
        fillInfosView();
    }

    //button to test the building view


    void fillInfosView(){

        textNameBuilding1.setText(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(0).getName());
        textNameBuilding2.setText(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(1).getName());
        textNameBuilding3.setText(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(2).getName());
        textNameBuilding4.setText(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(3).getName());
        textNameBuilding5.setText(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(4).getName());

        textEnviBuilding1.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(0).getEffetEnvironnemental()));
        textEnviBuilding2.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(1).getEffetEnvironnemental()));
        textEnviBuilding3.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(2).getEffetEnvironnemental()));
        textEnviBuilding4.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(3).getEffetEnvironnemental()));
        textEnviBuilding5.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(4).getEffetEnvironnemental()));

        textAttractBuilding1.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(0).getEffetAttractivite()));
        textAttractBuilding2.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(1).getEffetAttractivite()));
        textAttractBuilding3.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(2).getEffetAttractivite()));
        textAttractBuilding4.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(3).getEffetAttractivite()));
        textAttractBuilding5.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(4).getEffetAttractivite()));

        textTraficBuilding1.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(0).getEffetFluidite()));
        textTraficBuilding2.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(1).getEffetFluidite()));
        textTraficBuilding3.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(2).getEffetFluidite()));
        textTraficBuilding4.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(3).getEffetFluidite()));
        textTraficBuilding5.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(4).getEffetFluidite()));

        Building Building1 = PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(0);
        Building Building2 = PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(1);
        Building Building3 = PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(2);
        Building Building4 = PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(3);
        Building Building5 = PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(4);

        textSocialRessourcesBuilding1.setText(Building1.getAvancementCoutSocial() + "/" + Building1.getCoutSocial());
        textSocialRessourcesBuilding2.setText(Building2.getAvancementCoutSocial() + "/" + Building2.getCoutSocial());
        textSocialRessourcesBuilding3.setText(Building3.getAvancementCoutSocial() + "/" + Building3.getCoutSocial());
        textSocialRessourcesBuilding4.setText(Building4.getAvancementCoutSocial() + "/" + Building4.getCoutSocial());
        textSocialRessourcesBuilding5.setText(Building5.getAvancementCoutSocial() + "/" + Building5.getCoutSocial());

        textEcoResssourcesBuilding1.setText(Building1.getAvancementCoutEconomique() + "/" + Building1.getCoutEconomique());
        textEcoResssourcesBuilding2.setText(Building2.getAvancementCoutEconomique() + "/" + Building2.getCoutEconomique());
        textEcoResssourcesBuilding3.setText(Building3.getAvancementCoutEconomique() + "/" + Building3.getCoutEconomique());
        textEcoResssourcesBuilding4.setText(Building4.getAvancementCoutEconomique() + "/" + Building4.getCoutEconomique());
        textEcoResssourcesBuilding5.setText(Building5.getAvancementCoutEconomique() + "/" + Building5.getCoutEconomique());

        textPoliticalResssourcesBuilding1.setText(Building1.getAvancementCoutPolitique() + "/" + Building1.getCoutPolitique());
        textPoliticalResssourcesBuilding2.setText(Building2.getAvancementCoutPolitique() + "/" + Building2.getCoutPolitique());
        textPoliticalResssourcesBuilding3.setText(Building3.getAvancementCoutPolitique() + "/" + Building3.getCoutPolitique());
        textPoliticalResssourcesBuilding4.setText(Building4.getAvancementCoutPolitique() + "/" + Building4.getCoutPolitique());
        textPoliticalResssourcesBuilding5.setText(Building5.getAvancementCoutPolitique() + "/" + Building5.getCoutPolitique());

        textScoreCityAttract.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreAttractivite()));
        textScoreCityEnvi.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreEnvironnemental()));
        textScoreCityTrafic.setText(String.valueOf(PlayerConnexionActivity.net.getCurrentGame().getScoreFluidite()));

        textScorePlayer.setText("Score : " + PlayerConnexionActivity.net.getPlayer().getScore());
        textScore.setText("Tour n°"+PlayerConnexionActivity.net.getCurrentGame().getnTurn());

        colorRessources();
    }

    public void showPopUp(View v, int numBuilding)
    {
        numBuildingF = numBuilding;
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.bet_popup, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        popUpBet = new PopupWindow(popUpView, width, height, focusable);

        buttonBetPopup = (Button)popUpView.findViewById(R.id.button_X);
        textNameBuildingPopup = (TextView)popUpView.findViewById(R.id.text_name_building_popup);

        textAvancementRessourceTop = (TextView) popUpView.findViewById(R.id.avancement_ressource_top);
        TextAvancementRessourceBot= (TextView) popUpView.findViewById(R.id.avancement_ressource_bot);
        icoRessourceBotPopup= (ImageView) popUpView.findViewById(R.id.ico_ressource_bot_popup);
        icoRessourceTopPopup= (ImageView) popUpView.findViewById(R.id.ico_ressource_top_popup);

        descriptionBuildingPopUp = (TextView) popUpView.findViewById(R.id.text_desc_building_popup);

        buttonMinusTop= (Button) popUpView.findViewById(R.id.button_minus_top);
        buttonMinusBot= (Button) popUpView.findViewById(R.id.button_minus_bot);
        buttonPlusTop= (Button) popUpView.findViewById(R.id.button_plus_top);
        buttonPlusBot= (Button) popUpView.findViewById(R.id.button_plus_bot);


        textAvancementRessourceTop.setText(String.valueOf(financementRessource[numBuildingF][0]));
        TextAvancementRessourceBot.setText(String.valueOf(financementRessource[numBuildingF][1]));
        descriptionBuildingPopUp.setText(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(numBuilding).getDescription());

        setEnabledBetButtons(buttonState);


        textNameBuildingPopup.setText(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(numBuilding).getName());

        if (Ressource1.equals("Social")){
            icoRessourceTopPopup.setImageResource(R.mipmap.img_ressource_social_foreground);
        }
        else if(Ressource1.equals("Economical")){
            icoRessourceTopPopup.setImageResource(R.mipmap.img_ressource_eco_foreground);
        }
        else {
            icoRessourceTopPopup.setImageResource(R.mipmap.img_ressource_political_foreground);
        }

        if (Ressource2.equals("Social")){
            icoRessourceBotPopup.setImageResource(R.mipmap.img_attract_city_foreground);
        }
        else if(Ressource2.equals("Economical")){
            icoRessourceBotPopup.setImageResource(R.mipmap.img_ressource_eco_foreground);
        }
        else {
            icoRessourceBotPopup.setImageResource(R.mipmap.img_ressource_political_foreground);
        }



        buttonPlusTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betFromButton(numBuildingF,0,1);
            }
        });

        buttonMinusTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betFromButton(numBuildingF,0,-1);
            }
        });

        buttonPlusBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betFromButton(numBuildingF,1,1);
            }
        });

        buttonMinusBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betFromButton(numBuildingF,1,-1);
            }
        });


        popUpBet.showAtLocation(v, Gravity.CENTER, 0, 0);
        // Assombrissement de l'arrière plan
        //dimBehind(popUpBet);


        buttonBetPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpBet.dismiss();
            }
        });
    }

    private void dimBehind(PopupWindow popupWindow)
    {
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

    private void fillRoleCardObjectives(){

        Role RoleInfo = PlayerConnexionActivity.net.getPlayer().getRole();

        if (RoleInfo.getHold().equals("Attractivité")){
           icoObjectifLeftRole.setImageResource(R.mipmap.img_impact_on_city_foreground);
        }
        else if (RoleInfo.getHold().equals("Fluidité")){
            icoObjectifLeftRole.setImageResource(R.mipmap.img_fluid_city_foreground);
        }
        else {
            icoObjectifLeftRole.setImageResource(R.mipmap.img_environment_city_foreground);
        }

        if (RoleInfo.getImprove().equals("Attractivité")){
            icoObjectifRightRole.setImageResource(R.mipmap.img_impact_on_city_foreground);
        }
        else if (RoleInfo.getImprove().equals("Fluidité")){
            icoObjectifRightRole.setImageResource(R.mipmap.img_fluid_city_foreground);
        }
        else {
            icoObjectifRightRole.setImageResource(R.mipmap.img_environment_city_foreground);
        }
    }

    private void fillRessources(){
        Role RoleInfo = PlayerConnexionActivity.net.getPlayer().getRole();

        if (RoleInfo.getBooleanRessource()[0] == false){
            Ressource1 = "Economical";
            Ressource2 = "Political";
        }
        if (RoleInfo.getBooleanRessource()[1] == false){
            Ressource1 = "Social";
            Ressource2 = "Political";
        }
        if (RoleInfo.getBooleanRessource()[2] == false){
            Ressource1 = "Social";
            Ressource2 = "Economical";
        }


    }

    private void fillRoleCardRessources(){

        Role RoleInfo = PlayerConnexionActivity.net.getPlayer().getRole();

        if (Ressource1.equals("Social")){
            icoRessourceLeftRole.setImageResource(R.mipmap.img_ressource_social_foreground);
            textRessourceLeftRole.setText(String.valueOf(RoleInfo.getTokenSocial()));
        }
        else if(Ressource1.equals("Economical")){
            icoRessourceLeftRole.setImageResource(R.mipmap.img_ressource_eco_foreground);
            textRessourceLeftRole.setText(String.valueOf(RoleInfo.getTokenEconomical()));
        }
        else {
            icoRessourceLeftRole.setImageResource(R.mipmap.img_ressource_political_foreground);
            textRessourceLeftRole.setText(String.valueOf(RoleInfo.getTokenPolitical()));
        }

        if (Ressource2.equals("Social")){
            icoRessourceRightRole.setImageResource(R.mipmap.img_attract_city_foreground);
            textRessourceRightRole.setText(String.valueOf(RoleInfo.getTokenSocial()));
        }
        else if(Ressource2.equals("Economical")){
            icoRessourceRightRole.setImageResource(R.mipmap.img_ressource_eco_foreground);
            textRessourceRightRole.setText(String.valueOf(RoleInfo.getTokenEconomical()));
        }
        else {
            icoRessourceRightRole.setImageResource(R.mipmap.img_ressource_political_foreground);
            textRessourceRightRole.setText(String.valueOf(RoleInfo.getTokenPolitical()));
        }
    }

    private void betFromButton(int numBuilding, int ressource, int Value){

        Role RoleInfo = PlayerConnexionActivity.net.getPlayer().getRole();
        Building building= PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(numBuilding);
        String valeurRessource;
        if (ressource == 0){
            valeurRessource = Ressource1;
        }
        else {
            valeurRessource = Ressource2;
        }

        if(valeurRessource.equals("Social")){

                if(((Value == 1) && (RoleInfo.getTokenSocial() > 0)) && (building.getAvancementCoutSocial() < building.getCoutSocial())){
                    buttonState = false;
                    setEnabledBetButtons(buttonState);
                    mise = new Bet(numBuilding, 0, 0, 1);
                    try {
                        PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise));
                        RoleInfo.lessSocial();
                        fillRoleCardRessources();
                        financementRessource[numBuilding][ressource]++;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if ((Value == -1) && (building.getAvancementCoutSocial() > 0)) {
                    buttonState = false;
                    setEnabledBetButtons(buttonState);
                    mise = new Bet(numBuilding, 0, 0, -1);
                    try {
                        PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise));
                        RoleInfo.addSocial();
                        fillRoleCardRessources();
                        financementRessource[numBuilding][ressource]--;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        }
        if(valeurRessource.equals("Economical")){

            if(((Value == 1) && (RoleInfo.getTokenEconomical() > 0)) && (building.getAvancementCoutEconomique() < building.getCoutEconomique())){
                buttonState = false;
                setEnabledBetButtons(buttonState);
                mise = new Bet(numBuilding, 0, 1, 0);
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise));
                    RoleInfo.lessEco();
                    fillRoleCardRessources();
                    financementRessource[numBuilding][ressource]++;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if ((Value == -1)&&(building.getAvancementCoutEconomique() > 0)) {
                buttonState = false;
                setEnabledBetButtons(buttonState);
                mise = new Bet(numBuilding, 0, -1, 0);
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise));
                    RoleInfo.addEco();
                    fillRoleCardRessources();
                    financementRessource[numBuilding][ressource]--;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        if(valeurRessource.equals("Political")){

            if(((Value == 1) && (RoleInfo.getTokenPolitical() > 0)) && (building.getAvancementCoutPolitique() < building.getCoutPolitique())){
                buttonState = false;
                setEnabledBetButtons(buttonState);
                mise = new Bet(numBuilding, 1, 0, 0);
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise));
                    RoleInfo.lessPolitical();
                    fillRoleCardRessources();
                    financementRessource[numBuilding][ressource]++;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if ((Value == -1)&&(building.getAvancementCoutPolitique() > 0)) {
                buttonState = false;
                setEnabledBetButtons(buttonState);
                mise = new Bet(numBuilding, -1, 0, 0);
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise));
                    RoleInfo.addPolitical();
                    fillRoleCardRessources();
                    financementRessource[numBuilding][ressource]--;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        PlayerConnexionActivity.net.getPlayer().setFinancementRessource(financementRessource);
        textAvancementRessourceTop.setText(String.valueOf(financementRessource[numBuildingF][0]));
        TextAvancementRessourceBot.setText(String.valueOf(financementRessource[numBuildingF][1]));

    }


    public void resetTurnButton() {
            nextTurn = false;
            bTurn.setText("Tour suivant");
            Integer[][] financementRessourceReset= {{0,0},{0,0},{0,0},{0,0},{0,0}};
            financementRessource = financementRessourceReset;
            PlayerConnexionActivity.net.getPlayer().setFinancementRessource(financementRessource);
        }

    public void setEnabledBetButtons(boolean bool){
        if(buttonMinusTop != null && buttonMinusBot != null && buttonPlusBot != null && buttonPlusTop != null) {
            buttonMinusTop.setEnabled(bool);
            buttonPlusTop.setEnabled(bool);
            buttonMinusBot.setEnabled(bool);
            buttonPlusBot.setEnabled(bool);
        }
    }

    public void setButtonState(boolean buttonState) {
        this.buttonState = buttonState;
    }


    public void colorBuildingBet(){
        Market M = PlayerConnexionActivity.net.getCurrentGame().getMarket();
        if( M.getBuildings().get(0).isFilled()){
            B1.setBackground(getDrawable(R.drawable.style_market_view_green));
        }
        else {
            B1.setBackground(getDrawable(R.drawable.style_market_view));
        }

        if( M.getBuildings().get(1).isFilled()){
            B2.setBackground(getDrawable(R.drawable.style_market_view_green));
        }
        else {
            B2.setBackground(getDrawable(R.drawable.style_market_view));
        }

        if( M.getBuildings().get(2).isFilled()){
            B3.setBackground(getDrawable(R.drawable.style_market_view_green));
        }
        else {
            B3.setBackground(getDrawable(R.drawable.style_market_view));
        }

        if( M.getBuildings().get(3).isFilled()){
            B4.setBackground(getDrawable(R.drawable.style_market_view_green));
        }
        else {
            B4.setBackground(getDrawable(R.drawable.style_market_view));
    }

        if( M.getBuildings().get(4).isFilled()){
            B5.setBackground(getDrawable(R.drawable.style_market_view_green));
        }
        else {
            B5.setBackground(getDrawable(R.drawable.style_market_view));
        }
    }

    public void colorRessources(){

        int[][] ressources_bet = new int[][]{{0, 0, 0}, {0, 0 ,0}, {0, 0 ,0}, {0, 0 ,0}, {0, 0 ,0}};

        if (Ressource1.equals("Economical")){

            if (financementRessource[0][0] != 0){
                eco_1.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[0][1] = 1;
            }
            else{
                eco_1.setBackground(getDrawable(R.drawable.none));
                ressources_bet[0][1] = 0;
            }

            if (financementRessource[1][0] != 0){
                eco_2.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[1][1] = 1;
            }
            else{
                eco_2.setBackground(getDrawable(R.drawable.none));
                ressources_bet[1][1] = 0;
            }

            if (financementRessource[2][0] != 0){
                eco_3.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[2][1] = 1;
            }
            else{
                eco_3.setBackground(getDrawable(R.drawable.none));
                ressources_bet[2][1] = 0;
            }

            if (financementRessource[3][0] != 0){
                eco_4.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[3][1] = 1;
            }
            else{
                eco_4.setBackground(getDrawable(R.drawable.none));
                ressources_bet[3][1] = 0;
            }

            if (financementRessource[4][0] != 0){
                eco_5.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[4][1] = 1;
            }
            else{
                eco_5.setBackground(getDrawable(R.drawable.none));
                ressources_bet[4][1] = 0;
            }

        }
        if (Ressource2.equals("Economical")){

            if (financementRessource[0][1] != 0){
                eco_1.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[0][1] = 1;
            }
            else{
                eco_1.setBackground(getDrawable(R.drawable.none));
                ressources_bet[0][1] = 0;
            }

            if (financementRessource[1][1] != 0){
                eco_2.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[1][1] = 1;
            }
            else{
                eco_2.setBackground(getDrawable(R.drawable.none));
                ressources_bet[1][1] = 0;
            }

            if (financementRessource[2][1] != 0){
                eco_3.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[2][1] = 1;
            }
            else{
                eco_3.setBackground(getDrawable(R.drawable.none));
                ressources_bet[2][1] = 0;

            }

            if (financementRessource[3][1] != 0){
                eco_4.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[3][1] = 1;
            }
            else{
                eco_4.setBackground(getDrawable(R.drawable.none));
                ressources_bet[3][1] = 0;
            }

            if (financementRessource[4][1] != 0){
                eco_5.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[4][1] = 1;
            }
            else{
                eco_5.setBackground(getDrawable(R.drawable.none));
                ressources_bet[4][1] = 0;
            }
        }

        if (Ressource1.equals("Political")){

            if (financementRessource[0][0] != 0){
                poli_1.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[0][0] = 1;
            }
            else{
                poli_1.setBackground(getDrawable(R.drawable.none));

            }

            if (financementRessource[1][0] != 0){
                poli_2.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[1][0] = 1;
            }
            else{
                poli_2.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[2][0] != 0){
                poli_3.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[2][0] = 1;
            }
            else{
                poli_3.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[3][0] != 0){
                poli_4.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[3][0] = 1;
            }
            else{
                poli_4.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[4][0] != 0){
                poli_5.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[4][0] = 1;
            }
            else{
                poli_5.setBackground(getDrawable(R.drawable.none));
            }

        }
        if (Ressource2.equals("Political")){

            if (financementRessource[0][1] != 0){
                poli_1.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[0][0] = 1;
            }
            else{
                poli_1.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[1][1] != 0){
                poli_2.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[1][0] = 1;
            }
            else{
                poli_2.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[2][1] != 0){
                poli_3.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[2][0] = 1;
            }
            else{
                poli_3.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[3][1] != 0){
                poli_4.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[3][0] = 1;
            }
            else{
                poli_4.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[4][1] != 0){
                poli_5.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[4][0] = 1;
            }
            else{
                poli_5.setBackground(getDrawable(R.drawable.none));
            }
        }

        if (Ressource1.equals("Social")){

            if (financementRessource[0][0] != 0){
                social_1.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[0][2] = 1;
            }
            else{
                social_1.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[1][0] != 0){
                social_2.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[1][2] = 1;
            }
            else{
                social_2.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[2][0] != 0){
                social_3.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[2][2] = 1;
            }
            else{
                social_3.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[3][0] != 0){
                social_4.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[3][2] = 1;
            }
            else{
                social_4.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[4][0] != 0){
                social_5.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[4][2] = 1;
            }
            else{
                social_5.setBackground(getDrawable(R.drawable.none));
            }

        }
        if (Ressource2.equals("Social")){

            if (financementRessource[0][1] != 0){
                social_1.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[0][2] = 1;
            }
            else{
                social_1.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[1][1] != 0){
                social_2.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[1][2] = 1;
            }
            else{
                social_2.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[2][1] != 0){
                social_3.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[2][2] = 1;

            }
            else{
                social_3.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[3][1] != 0){
                social_4.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[3][2] = 1;
            }
            else{
                social_4.setBackground(getDrawable(R.drawable.none));
            }

            if (financementRessource[4][1] != 0){
                social_5.setBackground(getDrawable(R.drawable.ressources_invested));
                ressources_bet[4][2] = 1;
            }
            else{
                social_5.setBackground(getDrawable(R.drawable.none));
            }
        }

        Market M = PlayerConnexionActivity.net.getCurrentGame().getMarket();

        //---------------------------------------------Economique------------------------------------------------------------

        if ((M.getBuildings().get(0).getAvancementCoutEconomique() == M.getBuildings().get(0).getCoutEconomique())){
            if (ressources_bet[0][1] == 1) {
                eco_1.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                eco_1.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(1).getAvancementCoutEconomique() == M.getBuildings().get(1).getCoutEconomique())){
            if (ressources_bet[1][1] == 1) {
                eco_2.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                eco_2.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(2).getAvancementCoutEconomique() == M.getBuildings().get(2).getCoutEconomique())){
            if (ressources_bet[2][1] == 1) {
                eco_3.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                eco_3.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(3).getAvancementCoutEconomique() == M.getBuildings().get(3).getCoutEconomique())){
            if (ressources_bet[3][1] == 1) {
                eco_4.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                eco_4.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(4).getAvancementCoutEconomique() == M.getBuildings().get(4).getCoutEconomique())){
            if (ressources_bet[4][1] == 1) {
                eco_5.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                eco_5.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }

        //---------------------------------------------Politique-------------------------------------------------------------

        if ((M.getBuildings().get(0).getAvancementCoutPolitique() == M.getBuildings().get(0).getCoutPolitique())){
            if (ressources_bet[0][0] == 1) {
                poli_1.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                poli_1.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(1).getAvancementCoutPolitique() == M.getBuildings().get(1).getCoutPolitique())){
            if (ressources_bet[1][0] == 1) {
                poli_2.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                poli_2.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(2).getAvancementCoutPolitique() == M.getBuildings().get(2).getCoutPolitique())){
            if (ressources_bet[2][0] == 1) {
                poli_3.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                poli_3.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(3).getAvancementCoutPolitique() == M.getBuildings().get(3).getCoutPolitique())){
            if (ressources_bet[3][0] == 1) {
                poli_4.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                poli_4.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(4).getAvancementCoutPolitique() == M.getBuildings().get(4).getCoutPolitique())){
            if (ressources_bet[4][0] == 1) {
                poli_5.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                poli_5.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }

        //---------------------------------------------Social---------------------------------------------------------------

        if ((M.getBuildings().get(0).getAvancementCoutSocial() == M.getBuildings().get(0).getCoutSocial())){
            if (ressources_bet[0][2] == 1) {
                social_1.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                social_1.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(1).getAvancementCoutSocial() == M.getBuildings().get(1).getCoutSocial())){
            if (ressources_bet[1][2] == 1) {
                social_1.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                social_1.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(2).getAvancementCoutSocial() == M.getBuildings().get(2).getCoutSocial())){
            if (ressources_bet[2][2] == 1) {
                social_3.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                social_3.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(3).getAvancementCoutSocial() == M.getBuildings().get(3).getCoutSocial())){
            if (ressources_bet[3][2] == 1) {
                social_4.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                social_4.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
        if ((M.getBuildings().get(4).getAvancementCoutSocial() == M.getBuildings().get(4).getCoutSocial())){
            if (ressources_bet[4][2] == 1) {
                social_5.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
            else {
                social_5.setBackground(getDrawable(R.drawable.ressources_green));
            }
        }
    }
}