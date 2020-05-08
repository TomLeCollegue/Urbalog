package com.example.urbalog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urbalog.Class.Bet;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Duo;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.Role;
import com.example.urbalog.Class.Signal;
import com.example.urbalog.Class.TransferPackage;

import java.io.IOException;

public class PlayerViewActivity extends AppCompatActivity {

    private static final String TAG = "slt";

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


    private TextView textScorePlayer;

    private Integer numBuildingF;

    private Bet mise;

    private String Ressource1;
    private String Ressource2;

    private Integer[][] financementRessource;
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

    //Images under the building's name
    private ImageView attractiveness1;
    private ImageView attractiveness2;
    private ImageView attractiveness3;
    private ImageView attractiveness4;
    private ImageView attractiveness5;

    private ImageView environment1;
    private ImageView environment2;
    private ImageView environment3;
    private ImageView environment4;
    private ImageView environment5;

    private ImageView fluidity1;
    private ImageView fluidity2;
    private ImageView fluidity3;
    private ImageView fluidity4;
    private ImageView fluidity5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("debug", "PlayerViewActivity creation");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_view_activity);

        /* Link player activity to NetworkHelper */
        PlayerConnexionActivity.net.setCurrentPlayerView(this);
        buttonState = true;

        B1 = findViewById(R.id.infrastructure_1);
        B2 = findViewById(R.id.infrastructure_2);
        B3 = findViewById(R.id.infrastructure_3);
        B4 = findViewById(R.id.infrastructure_4);
        B5 = findViewById(R.id.infrastructure_5);

        poli_1 = findViewById(R.id.poli_1);
        eco_1 = findViewById(R.id.eco_1);
        social_1 = findViewById(R.id.social_1);

        poli_2 = findViewById(R.id.poli_2);
        eco_2 = findViewById(R.id.eco_2);
        social_2 = findViewById(R.id.social_2);

        poli_3 = findViewById(R.id.poli_3);
        eco_3 = findViewById(R.id.eco_3);
        social_3 = findViewById(R.id.social_3);

        poli_4 = findViewById(R.id.poli_4);
        eco_4 = findViewById(R.id.eco_4);
        social_4 = findViewById(R.id.social_4);

        poli_5 = findViewById(R.id.poli_5);
        eco_5 = findViewById(R.id.eco_5);
        social_5 = findViewById(R.id.social_5);

        financementRessource = PlayerConnexionActivity.net.getPlayer().getFinancementRessource();

        textPoliticalResssourcesBuilding1 = findViewById(R.id.text_political_resssources_building_1);
        textPoliticalResssourcesBuilding2 = findViewById(R.id.text_political_resssources_building_2);
        textPoliticalResssourcesBuilding3 = findViewById(R.id.text_political_resssources_building_3);
        textPoliticalResssourcesBuilding4 = findViewById(R.id.text_political_resssources_building_4);
        textPoliticalResssourcesBuilding5 = findViewById(R.id.text_political_resssources_building_5);
        textEcoResssourcesBuilding1 = findViewById(R.id.text_eco_resssources_building_1);
        textEcoResssourcesBuilding2 = findViewById(R.id.text_eco_resssources_building_2);
        textEcoResssourcesBuilding3 = findViewById(R.id.text_eco_resssources_building_3);
        textEcoResssourcesBuilding4 = findViewById(R.id.text_eco_resssources_building_4);
        textEcoResssourcesBuilding5 = findViewById(R.id.text_eco_resssources_building_5);
        textSocialRessourcesBuilding1 = findViewById(R.id.text_social_ressources_building_1);
        textSocialRessourcesBuilding2 = findViewById(R.id.text_social_ressources_building_2);
        textSocialRessourcesBuilding3 = findViewById(R.id.text_social_ressources_building_3);
        textSocialRessourcesBuilding4 = findViewById(R.id.text_social_ressources_building_4);
        textSocialRessourcesBuilding5 = findViewById(R.id.text_social_ressources_building_5);

        textAttractBuilding1 = findViewById(R.id.text_attract_building_1);
        textAttractBuilding2 = findViewById(R.id.text_attract_building_2);
        textAttractBuilding3 = findViewById(R.id.text_attract_building_3);
        textAttractBuilding4 = findViewById(R.id.text_attract_building_4);
        textAttractBuilding5 = findViewById(R.id.text_attract_building_5);

        textEnviBuilding1 = findViewById(R.id.text_envi_building_1);
        textEnviBuilding2 = findViewById(R.id.text_envi_building_2);
        textEnviBuilding3 = findViewById(R.id.text_envi_building_3);
        textEnviBuilding4 = findViewById(R.id.text_envi_building_4);
        textEnviBuilding5 = findViewById(R.id.text_envi_building_5);

        textTraficBuilding1 = findViewById(R.id.text_trafic_building_1);
        textTraficBuilding2 = findViewById(R.id.text_trafic_building_2);
        textTraficBuilding3 = findViewById(R.id.text_trafic_building_3);
        textTraficBuilding4 = findViewById(R.id.text_trafic_building_4);
        textTraficBuilding5 = findViewById(R.id.text_trafic_building_5);

        bTurn = findViewById(R.id.button_turn);

        textNameBuilding1 = findViewById(R.id.text_name_building_1);
        textNameBuilding2 = findViewById(R.id.text_name_building_2);
        textNameBuilding3 = findViewById(R.id.text_name_building_3);
        textNameBuilding4 = findViewById(R.id.text_name_building_4);
        textNameBuilding5 = findViewById(R.id.text_name_building_5);

        icoObjectifLeftRole = findViewById(R.id.ico_objectif_left_role);
        icoObjectifRightRole = findViewById(R.id.ico_objectif_right_role);
        textTitleRole = findViewById(R.id.text_title_role);
        icoRessourceLeftRole = findViewById(R.id.ico_ressource_left_role);
        icoRessourceRightRole = findViewById(R.id.ico_ressource_right_role);
        textRessourceLeftRole = findViewById(R.id.text_ressource_left_role);
        textRessourceRightRole = findViewById(R.id.text_ressource_right_role);


        attractiveness1 = findViewById(R.id.img_attract_building_1);
        attractiveness2 = findViewById(R.id.img_attract_building_2);
        attractiveness3 = findViewById(R.id.img_attract_building_3);
        attractiveness4 = findViewById(R.id.img_attract_building_4);
        attractiveness5 = findViewById(R.id.img_attract_building_5);

        environment1 = findViewById(R.id.img_environment_building_1);
        environment2 = findViewById(R.id.img_environment_building_2);
        environment3 = findViewById(R.id.img_environment_building_3);
        environment4 = findViewById(R.id.img_environment_building_4);
        environment5 = findViewById(R.id.img_environment_building_5);

        fluidity1 = findViewById(R.id.img_fluidity_building_1);
        fluidity2 = findViewById(R.id.img_fluidity_building_2);
        fluidity3 = findViewById(R.id.img_fluidity_building_3);
        fluidity4 = findViewById(R.id.img_fluidity_building_4);
        fluidity5 = findViewById(R.id.img_fluidity_building_5);

        textScorePlayer = findViewById(R.id.text_score_player);

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
        fillInfosView();
    }

    @Override
    public void onBackPressed() {
        AlertDialog diaBox = AskOption();
        diaBox.show();
    }

    /**
     * Create AlertDialog when player hit return button
     * for confimation
     *
     * @return Exit confimation alert dialog
     */
    private AlertDialog AskOption()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Quitter")
                .setMessage("Etes-vous sûr de vouloir quitter la partie?")
                .setIcon(R.drawable.warning_icon)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
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



        textScorePlayer.setText("Score : " + PlayerConnexionActivity.net.getPlayer().getScore());



        colorImpact();
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

        buttonBetPopup = popUpView.findViewById(R.id.button_X);
        textNameBuildingPopup = popUpView.findViewById(R.id.text_name_building_popup);

        textAvancementRessourceTop = popUpView.findViewById(R.id.avancement_ressource_top);
        TextAvancementRessourceBot= popUpView.findViewById(R.id.avancement_ressource_bot);
        icoRessourceBotPopup= popUpView.findViewById(R.id.ico_ressource_bot_popup);
        icoRessourceTopPopup= popUpView.findViewById(R.id.ico_ressource_top_popup);

        descriptionBuildingPopUp = popUpView.findViewById(R.id.text_desc_building_popup);

        buttonMinusTop= popUpView.findViewById(R.id.button_minus_top);
        buttonMinusBot= popUpView.findViewById(R.id.button_minus_bot);
        buttonPlusTop= popUpView.findViewById(R.id.button_plus_top);
        buttonPlusBot= popUpView.findViewById(R.id.button_plus_bot);

        updateStateButton(numBuildingF);


        textAvancementRessourceTop.setText(String.valueOf(financementRessource[numBuildingF][0]));
        TextAvancementRessourceBot.setText(String.valueOf(financementRessource[numBuildingF][1]));
        descriptionBuildingPopUp.setText(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(numBuilding).getDescription());

        setEnabledBetButtons(buttonState);



        textNameBuildingPopup.setText(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(numBuilding).getName());

        imgPiecesRessources();



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


        buttonBetPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpBet.dismiss();
            }
        });

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

        if (!RoleInfo.getBooleanRessource()[0]){
            Ressource1 = "Economical";
            Ressource2 = "Political";
        }
        else if (!RoleInfo.getBooleanRessource()[1]){
            Ressource1 = "Social";
            Ressource2 = "Political";
        }
        else if (!RoleInfo.getBooleanRessource()[2]){
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
        Building building = PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(numBuilding);
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
                    mise = new Bet(building.getName(), numBuilding, 0, 0, 1, PlayerConnexionActivity.net.getPlayer().getDbID());
                    try {
                        PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Duo>(
                                Signal.BET_RECEIVED,
                                new Duo<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise)));
                        RoleInfo.lessSocial();
                        fillRoleCardRessources();
                        financementRessource[numBuilding][ressource]++;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if ((Value == -1) && (building.getAvancementCoutSocial() > 0) && (financementRessource[numBuilding][ressource] != 0)) {
                    buttonState = false;
                    setEnabledBetButtons(buttonState);
                    mise = new Bet(building.getName(), numBuilding, 0, 0, -1, PlayerConnexionActivity.net.getPlayer().getDbID());
                    try {
                        PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Duo>(
                                Signal.BET_RECEIVED,
                                new Duo<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise)));
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
                mise = new Bet(building.getName(), numBuilding, 0, 1, 0, PlayerConnexionActivity.net.getPlayer().getDbID());
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Duo>(
                            Signal.BET_RECEIVED,
                            new Duo<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise)));
                    RoleInfo.lessEco();
                    fillRoleCardRessources();
                    financementRessource[numBuilding][ressource]++;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if ((Value == -1)&&(building.getAvancementCoutEconomique() > 0) && (financementRessource[numBuilding][ressource] != 0)) {
                buttonState = false;
                setEnabledBetButtons(buttonState);
                mise = new Bet(building.getName(), numBuilding, 0, -1, 0, PlayerConnexionActivity.net.getPlayer().getDbID());
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Duo>(
                            Signal.BET_RECEIVED,
                            new Duo<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise)));
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
                mise = new Bet(building.getName(), numBuilding, 1, 0, 0, PlayerConnexionActivity.net.getPlayer().getDbID());
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Duo>(
                            Signal.BET_RECEIVED,
                            new Duo<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise)));
                    RoleInfo.lessPolitical();
                    fillRoleCardRessources();
                    financementRessource[numBuilding][ressource]++;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if ((Value == -1)&&(building.getAvancementCoutPolitique() > 0) && (financementRessource[numBuilding][ressource] != 0)) {
                buttonState = false;
                setEnabledBetButtons(buttonState);
                mise = new Bet(building.getName(), numBuilding, -1, 0, 0, PlayerConnexionActivity.net.getPlayer().getDbID());
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Duo>(
                            Signal.BET_RECEIVED,
                            new Duo<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise)));
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
        updateStateButton(numBuilding);
    }

    public void enableTurnButton() {
        bTurn.setText("Tour suivant");
        nextTurn = false;
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
            /*buttonMinusTop.setEnabled(bool);
            buttonPlusTop.setEnabled(bool);
            buttonMinusBot.setEnabled(bool);
            buttonPlusBot.setEnabled(bool);*/
            updateStateButton(numBuildingF);
            imgPiecesRessources();
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
            }}
        if ((M.getBuildings().get(1).getAvancementCoutEconomique() == M.getBuildings().get(1).getCoutEconomique())){
            if (ressources_bet[1][1] == 1) {
                eco_2.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
        else {
                eco_2.setBackground(getDrawable(R.drawable.ressources_green));
        }}
        if ((M.getBuildings().get(2).getAvancementCoutEconomique() == M.getBuildings().get(2).getCoutEconomique())){
            if (ressources_bet[2][1] == 1) {
                eco_3.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
        else {
                eco_3.setBackground(getDrawable(R.drawable.ressources_green));
        }}
        if ((M.getBuildings().get(3).getAvancementCoutEconomique() == M.getBuildings().get(3).getCoutEconomique())){
            if (ressources_bet[3][1] == 1) {
                eco_4.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
        else {
                eco_4.setBackground(getDrawable(R.drawable.ressources_green));
        }}
        if ((M.getBuildings().get(4).getAvancementCoutEconomique() == M.getBuildings().get(4).getCoutEconomique())){
            if (ressources_bet[4][1] == 1) {
                eco_5.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
        else {
                eco_5.setBackground(getDrawable(R.drawable.ressources_green));
        }}

        //---------------------------------------------Politique-------------------------------------------------------------

        if ((M.getBuildings().get(0).getAvancementCoutPolitique() == M.getBuildings().get(0).getCoutPolitique())){
            if (ressources_bet[0][0] == 1) {
                poli_1.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
        else {
                poli_1.setBackground(getDrawable(R.drawable.ressources_green));
        }}
        if ((M.getBuildings().get(1).getAvancementCoutPolitique() == M.getBuildings().get(1).getCoutPolitique())){
            if (ressources_bet[1][0] == 1) {
                poli_2.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
        else {
                poli_2.setBackground(getDrawable(R.drawable.ressources_green));
        }}
        if ((M.getBuildings().get(2).getAvancementCoutPolitique() == M.getBuildings().get(2).getCoutPolitique())){
            if (ressources_bet[2][0] == 1) {
                poli_3.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
        else {
                poli_3.setBackground(getDrawable(R.drawable.ressources_green));
        }}
        if ((M.getBuildings().get(3).getAvancementCoutPolitique() == M.getBuildings().get(3).getCoutPolitique())){
            if (ressources_bet[3][0] == 1) {
                poli_4.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
        else {
                poli_4.setBackground(getDrawable(R.drawable.ressources_green));
        }}
        if ((M.getBuildings().get(4).getAvancementCoutPolitique() == M.getBuildings().get(4).getCoutPolitique())){
            if (ressources_bet[4][0] == 1) {
                poli_5.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
        else {
                poli_5.setBackground(getDrawable(R.drawable.ressources_green));
        }}

        //---------------------------------------------Social---------------------------------------------------------------

        if ((M.getBuildings().get(0).getAvancementCoutSocial() == M.getBuildings().get(0).getCoutSocial())){
            if (ressources_bet[0][2] == 1) {
                social_1.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }

        else {
                social_1.setBackground(getDrawable(R.drawable.ressources_green));
            }}
        if ((M.getBuildings().get(1).getAvancementCoutSocial() == M.getBuildings().get(1).getCoutSocial())){
            if (ressources_bet[1][2] == 1) {
                social_1.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }

        else {
                social_1.setBackground(getDrawable(R.drawable.ressources_green));
            }}
        if ((M.getBuildings().get(2).getAvancementCoutSocial() == M.getBuildings().get(2).getCoutSocial())){
            if (ressources_bet[2][2] == 1) {
                social_3.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
        else {
                social_3.setBackground(getDrawable(R.drawable.ressources_green));
            }}
        if ((M.getBuildings().get(3).getAvancementCoutSocial() == M.getBuildings().get(3).getCoutSocial())){
            if (ressources_bet[3][2] == 1) {
                social_4.setBackground(getDrawable(R.drawable.ressources_invested_green));
            }
        else {
                social_4.setBackground(getDrawable(R.drawable.ressources_green));
        }}
        if ((M.getBuildings().get(4).getAvancementCoutSocial() == M.getBuildings().get(4).getCoutSocial())){
            if (ressources_bet[4][2] == 1) {
                social_5.setBackground(getDrawable(R.drawable.ressources_invested_green));

        }
        else {
            social_5.setBackground(getDrawable(R.drawable.ressources_green));
        }}
    }


    public void resetColorRessources() {

        eco_1.setBackground(getDrawable(R.drawable.none));
        eco_2.setBackground(getDrawable(R.drawable.none));
        eco_3.setBackground(getDrawable(R.drawable.none));
        eco_4.setBackground(getDrawable(R.drawable.none));
        eco_5.setBackground(getDrawable(R.drawable.none));

        poli_1.setBackground(getDrawable(R.drawable.none));
        poli_2.setBackground(getDrawable(R.drawable.none));
        poli_3.setBackground(getDrawable(R.drawable.none));
        poli_4.setBackground(getDrawable(R.drawable.none));
        poli_5.setBackground(getDrawable(R.drawable.none));

        social_1.setBackground(getDrawable(R.drawable.none));
        social_2.setBackground(getDrawable(R.drawable.none));
        social_3.setBackground(getDrawable(R.drawable.none));
        social_4.setBackground(getDrawable(R.drawable.none));
        social_5.setBackground(getDrawable(R.drawable.none));


    }

    /**
     * Will color the influence of a building in red if negative or in green if positive on the market
     * for each building
     */
    public void colorImpact(){

        Building building1 = PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(0);
        Building building2 = PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(1);
        Building building3 = PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(2);
        Building building4 = PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(3);
        Building building5 = PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(4);

        int colorRed = Color.parseColor("#D22C24");
        int colorGreen = Color.parseColor("#5e7a23");
        int colorBlack = Color.parseColor("#2d2a41");


        //On Building1
            //for attractiveness
        if(building1.getEffetAttractivite() > 0 ){
            attractiveness1.setColorFilter(colorGreen);
            textAttractBuilding1.setTextColor(colorGreen);
        }
        else if(building1.getEffetAttractivite() < 0){
            attractiveness1.setColorFilter(colorRed);
            textAttractBuilding1.setTextColor(colorRed);
        }
        else if (building1.getEffetAttractivite() == 0){
            attractiveness1.setColorFilter(colorBlack);
            textAttractBuilding1.setTextColor(colorBlack);
        }
            //for environment
        if (building1.getEffetEnvironnemental() > 0){
            environment1.setColorFilter(colorGreen);
            textEnviBuilding1.setTextColor(colorGreen);
        }
        else if(building1.getEffetEnvironnemental() < 0){
            environment1.setColorFilter(colorRed);
            textEnviBuilding1.setTextColor(colorRed);
        }
        else if (building1.getEffetEnvironnemental() == 0){
            environment1.setColorFilter(colorBlack);
            textEnviBuilding1.setTextColor(colorBlack);
        }

            //for fluidity
        if (building1.getEffetFluidite() > 0){
            fluidity1.setColorFilter(colorGreen);
            textTraficBuilding1.setTextColor(colorGreen);
        }
        else if(building1.getEffetFluidite() < 0){
            fluidity1.setColorFilter(colorRed);
            textTraficBuilding1.setTextColor(colorRed);
        }
        else if (building1.getEffetFluidite() == 0){
            fluidity1.setColorFilter(colorBlack);
            textTraficBuilding1.setTextColor(colorBlack);
        }

        //On Building2
        //for attractiveness
        if(building2.getEffetAttractivite() > 0 ){
            attractiveness2.setColorFilter(colorGreen);
            textAttractBuilding2.setTextColor(colorGreen);
        }
        else if(building2.getEffetAttractivite() < 0){
            attractiveness2.setColorFilter(colorRed);
            textAttractBuilding2.setTextColor(colorRed);
        }
        else if (building2.getEffetAttractivite() == 0){
            attractiveness2.setColorFilter(colorBlack);
            textAttractBuilding2.setTextColor(colorBlack);
        }
        //for environment
        if (building2.getEffetEnvironnemental() > 0){
            environment2.setColorFilter(colorGreen);
            textEnviBuilding2.setTextColor(colorGreen);
        }
        else if(building2.getEffetEnvironnemental() < 0){
            environment2.setColorFilter(colorRed);
            textEnviBuilding2.setTextColor(colorRed);
        }
        else if (building2.getEffetEnvironnemental() == 0){
            environment2.setColorFilter(colorBlack);
            textEnviBuilding2.setTextColor(colorBlack);
        }

        //for fluidity
        if (building2.getEffetFluidite() > 0){
            fluidity2.setColorFilter(colorGreen);
            textTraficBuilding2.setTextColor(colorGreen);
        }
        else if(building2.getEffetFluidite() < 0){
            fluidity2.setColorFilter(colorRed);
            textTraficBuilding2.setTextColor(colorRed);
        }
        else if (building2.getEffetFluidite() == 0){
            fluidity2.setColorFilter(colorBlack);
            textTraficBuilding2.setTextColor(colorBlack);
        }

        //On Building3
        //for attractiveness
        if(building3.getEffetAttractivite() > 0 ){
            attractiveness3.setColorFilter(colorGreen);
            textAttractBuilding3.setTextColor(colorGreen);
        }
        else if(building3.getEffetAttractivite() < 0){
            attractiveness3.setColorFilter(colorRed);
            textAttractBuilding3.setTextColor(colorRed);
        }
        else if (building3.getEffetAttractivite() == 0){
            attractiveness3.setColorFilter(colorBlack);
            textAttractBuilding3.setTextColor(colorBlack);
        }
        //for environment
        if (building3.getEffetEnvironnemental() > 0){
            environment3.setColorFilter(colorGreen);
            textEnviBuilding3.setTextColor(colorGreen);
        }
        else if(building3.getEffetEnvironnemental() < 0){
            environment3.setColorFilter(colorRed);
            textEnviBuilding3.setTextColor(colorRed);
        }
        else if (building3.getEffetEnvironnemental() == 0){
            environment3.setColorFilter(colorBlack);
            textEnviBuilding3.setTextColor(colorBlack);
        }

        //for fluidity
        if (building3.getEffetFluidite() > 0){
            fluidity3.setColorFilter(colorGreen);
            textTraficBuilding3.setTextColor(colorGreen);
        }
        else if(building3.getEffetFluidite() < 0){
            fluidity3.setColorFilter(colorRed);
            textTraficBuilding3.setTextColor(colorRed);
        }
        else if (building3.getEffetFluidite() == 0){
            fluidity3.setColorFilter(colorBlack);
            textTraficBuilding3.setTextColor(colorBlack);
        }

        //On Building4
        //for attractiveness
        if(building4.getEffetAttractivite() > 0 ){
            attractiveness4.setColorFilter(colorGreen);
            textAttractBuilding4.setTextColor(colorGreen);
        }
        else if(building4.getEffetAttractivite() < 0){
            attractiveness4.setColorFilter(colorRed);
            textAttractBuilding4.setTextColor(colorRed);
        }
        else if (building4.getEffetAttractivite() == 0){
            attractiveness4.setColorFilter(colorBlack);
            textAttractBuilding4.setTextColor(colorBlack);
        }
        //for environment
        if (building4.getEffetEnvironnemental() > 0){
            environment4.setColorFilter(colorGreen);
            textEnviBuilding4.setTextColor(colorGreen);
        }
        else if(building4.getEffetEnvironnemental() < 0){
            environment4.setColorFilter(colorRed);
            textEnviBuilding4.setTextColor(colorRed);
        }
        else if (building4.getEffetEnvironnemental() == 0){
            environment4.setColorFilter(colorBlack);
            textEnviBuilding4.setTextColor(colorBlack);
        }

        //for fluidity
        if (building4.getEffetFluidite() > 0){
            fluidity4.setColorFilter(colorGreen);
            textTraficBuilding4.setTextColor(colorGreen);
        }
        else if(building4.getEffetFluidite() < 0){
            fluidity4.setColorFilter(colorRed);
            textTraficBuilding4.setTextColor(colorRed);
        }
        else if (building4.getEffetFluidite() == 0){
            fluidity4.setColorFilter(colorBlack);
            textTraficBuilding4.setTextColor(colorBlack);
        }

        //On Building5
        //for attractiveness
        if(building5.getEffetAttractivite() > 0 ){
            attractiveness5.setColorFilter(colorGreen);
            textAttractBuilding5.setTextColor(colorGreen);
        }
        else if(building5.getEffetAttractivite() < 0){
            attractiveness5.setColorFilter(colorRed);
            textAttractBuilding5.setTextColor(colorRed);
        }
        else if (building5.getEffetAttractivite() == 0){
            attractiveness5.setColorFilter(colorBlack);
            textAttractBuilding5.setTextColor(colorBlack);
        }
        //for environment
        if (building5.getEffetEnvironnemental() > 0){
            environment5.setColorFilter(colorGreen);
            textEnviBuilding5.setTextColor(colorGreen);
        }
        else if(building5.getEffetEnvironnemental() < 0){
            environment5.setColorFilter(colorRed);
            textEnviBuilding5.setTextColor(colorRed);
        }
        else if (building5.getEffetEnvironnemental() == 0){
            environment5.setColorFilter(colorBlack);
            textEnviBuilding5.setTextColor(colorBlack);
        }

        //for fluidity
        if (building5.getEffetFluidite() > 0){
            fluidity5.setColorFilter(colorGreen);
            textTraficBuilding5.setTextColor(colorGreen);
        }
        else if(building5.getEffetFluidite() < 0){
            fluidity5.setColorFilter(colorRed);
            textTraficBuilding5.setTextColor(colorRed);
        }
        else if (building5.getEffetFluidite() == 0){
            fluidity5.setColorFilter(colorBlack);
            textTraficBuilding5.setTextColor(colorBlack);
        }








    }

    public void updateStateButton(int numBuilding){
        Role RoleInfo = PlayerConnexionActivity.net.getPlayer().getRole();
        if(financementRessource[numBuilding][0] == 0){
            buttonMinusTop.setEnabled(false);
        }
        else{
            buttonMinusTop.setEnabled(true);
        }
        if(financementRessource[numBuilding][1] == 0){
            buttonMinusBot.setEnabled(false);
        }
        else{
            buttonMinusBot.setEnabled(true);
        }

        Log.d(TAG, "update Button Bet");
        // **** Ressource 1 ********* //
        if(Ressource1.equals("Social")) {
            if (RoleInfo.getTokenSocial() == 0) {
                buttonPlusTop.setEnabled(false);
            } else {
                buttonPlusTop.setEnabled(true);
            }
        }
        else if(Ressource1.equals("Economical")){
            if(RoleInfo.getTokenEconomical() == 0){
                buttonPlusTop.setEnabled(false);
            }
            else{
                buttonPlusTop.setEnabled(true);
            }
        }
        else if(Ressource1.equals("Political")){
            if(RoleInfo.getTokenPolitical() == 0){
                buttonPlusTop.setEnabled(false);
            }
            else{
                buttonPlusTop.setEnabled(true);
            }
        }
         // ******** Ressource 2 ******** //
        if(Ressource2.equals("Social")) {
            if (RoleInfo.getTokenSocial() == 0) {
                buttonPlusBot.setEnabled(false);
            } else {
                buttonPlusBot.setEnabled(true);
            }
        }
        else if(Ressource2.equals("Economical")){
            if(RoleInfo.getTokenEconomical() == 0){
                buttonPlusBot.setEnabled(false);
            }
            else{
                buttonPlusBot.setEnabled(true);
            }
        }
        else if(Ressource2.equals("Political")){
            if(RoleInfo.getTokenPolitical() == 0){
                buttonPlusBot.setEnabled(false);
            }
            else{
                buttonPlusBot.setEnabled(true);
            }
        }
    }


    public void imgPiecesRessources(){
        Role RoleInfo = PlayerConnexionActivity.net.getPlayer().getRole();

        // ressource 1
        if(Ressource1.equals("Social")) {
            if (RoleInfo.getTokenSocial() == 0) {
                icoRessourceTopPopup.setImageResource(R.drawable.zero_piece);
            }
            else if (RoleInfo.getTokenSocial() == 1){
                icoRessourceTopPopup.setImageResource(R.drawable.one_s);
            }
            else if (RoleInfo.getTokenSocial() == 2){
                icoRessourceTopPopup.setImageResource(R.drawable.two_s);
            }
            else if (RoleInfo.getTokenSocial() == 3){
                icoRessourceTopPopup.setImageResource(R.drawable.three_s);
            }
            else if (RoleInfo.getTokenSocial() == 4){
                icoRessourceTopPopup.setImageResource(R.drawable.four_s);
            }
            else if (RoleInfo.getTokenSocial() == 5){
                icoRessourceTopPopup.setImageResource(R.drawable.five_s);
            }
            else if (RoleInfo.getTokenSocial() == 6){
                icoRessourceTopPopup.setImageResource(R.drawable.six_s);
            }
            else{
                icoRessourceTopPopup.setImageResource(R.drawable.seven_s);
            }
        }
        if(Ressource1.equals("Political")) {
            if (RoleInfo.getTokenPolitical() == 0) {
                icoRessourceTopPopup.setImageResource(R.drawable.zero_piece);
            }
            else if (RoleInfo.getTokenPolitical() == 1){
                icoRessourceTopPopup.setImageResource(R.drawable.one_p);
            }
            else if (RoleInfo.getTokenPolitical() == 2){
                icoRessourceTopPopup.setImageResource(R.drawable.two_p);
            }
            else if (RoleInfo.getTokenPolitical() == 3){
                icoRessourceTopPopup.setImageResource(R.drawable.three_p);
            }
            else if (RoleInfo.getTokenPolitical() == 4){
                icoRessourceTopPopup.setImageResource(R.drawable.four_p);
            }
            else if (RoleInfo.getTokenPolitical() == 5){
                icoRessourceTopPopup.setImageResource(R.drawable.five_p);
            }
            else if (RoleInfo.getTokenPolitical() == 6){
                icoRessourceTopPopup.setImageResource(R.drawable.six_p);
            }
            else if (RoleInfo.getTokenPolitical() == 7){
                icoRessourceTopPopup.setImageResource(R.drawable.seven_p);
            }
        }
        if(Ressource1.equals("Economical")) {
            if (RoleInfo.getTokenEconomical() == 0) {
                icoRessourceTopPopup.setImageResource(R.drawable.zero_piece);
            }
            else if (RoleInfo.getTokenEconomical() == 1){
                icoRessourceTopPopup.setImageResource(R.drawable.one_e);
            }
            else if (RoleInfo.getTokenEconomical() == 2){
                icoRessourceTopPopup.setImageResource(R.drawable.two_e);
            }
            else if (RoleInfo.getTokenEconomical() == 3){
                icoRessourceTopPopup.setImageResource(R.drawable.three_e);
            }
            else if (RoleInfo.getTokenEconomical() == 4){
                icoRessourceTopPopup.setImageResource(R.drawable.four_e);
            }
            else if (RoleInfo.getTokenEconomical() == 5){
                icoRessourceTopPopup.setImageResource(R.drawable.five_e);
            }
            else if (RoleInfo.getTokenEconomical() == 6){
                icoRessourceTopPopup.setImageResource(R.drawable.six_e);
            }
            else if (RoleInfo.getTokenEconomical() == 7){
                icoRessourceTopPopup.setImageResource(R.drawable.seven_e);
            }
        }

        // ressource 2
        if(Ressource2.equals("Social")) {
            if (RoleInfo.getTokenSocial() == 0) {
                icoRessourceBotPopup.setImageResource(R.drawable.zero_piece);
            }
            else if (RoleInfo.getTokenSocial() == 1){
                icoRessourceBotPopup.setImageResource(R.drawable.one_s);
            }
            else if (RoleInfo.getTokenSocial() == 2){
                icoRessourceBotPopup.setImageResource(R.drawable.two_s);
            }
            else if (RoleInfo.getTokenSocial() == 3){
                icoRessourceBotPopup.setImageResource(R.drawable.three_s);
            }
            else if (RoleInfo.getTokenSocial() == 4){
                icoRessourceBotPopup.setImageResource(R.drawable.four_s);
            }
            else if (RoleInfo.getTokenSocial() == 5){
                icoRessourceBotPopup.setImageResource(R.drawable.five_s);
            }
            else if (RoleInfo.getTokenSocial() == 6){
                icoRessourceBotPopup.setImageResource(R.drawable.six_s);
            }
            else{
                icoRessourceBotPopup.setImageResource(R.drawable.seven_s);
            }
        }
        if(Ressource2.equals("Political")) {
            if (RoleInfo.getTokenPolitical() == 0) {
                icoRessourceBotPopup.setImageResource(R.drawable.zero_piece);
            }
            else if (RoleInfo.getTokenPolitical() == 1){
                icoRessourceBotPopup.setImageResource(R.drawable.one_p);
            }
            else if (RoleInfo.getTokenPolitical() == 2){
                icoRessourceBotPopup.setImageResource(R.drawable.two_p);
            }
            else if (RoleInfo.getTokenPolitical() == 3){
                icoRessourceBotPopup.setImageResource(R.drawable.three_p);
            }
            else if (RoleInfo.getTokenPolitical() == 4){
                icoRessourceBotPopup.setImageResource(R.drawable.four_p);
            }
            else if (RoleInfo.getTokenPolitical() == 5){
                icoRessourceBotPopup.setImageResource(R.drawable.five_p);
            }
            else if (RoleInfo.getTokenPolitical() == 6){
                icoRessourceBotPopup.setImageResource(R.drawable.six_p);
            }
            else if (RoleInfo.getTokenPolitical() == 7){
                icoRessourceBotPopup.setImageResource(R.drawable.seven_p);
            }
        }
        if(Ressource2.equals("Economical")) {
            if (RoleInfo.getTokenEconomical() == 0) {
                icoRessourceBotPopup.setImageResource(R.drawable.zero_piece);
            }
            else if (RoleInfo.getTokenEconomical() == 1){
                icoRessourceBotPopup.setImageResource(R.drawable.one_e);
            }
            else if (RoleInfo.getTokenEconomical() == 2){
                icoRessourceBotPopup.setImageResource(R.drawable.two_e);
            }
            else if (RoleInfo.getTokenEconomical() == 3){
                icoRessourceBotPopup.setImageResource(R.drawable.three_e);
            }
            else if (RoleInfo.getTokenEconomical() == 4){
                icoRessourceBotPopup.setImageResource(R.drawable.four_e);
            }
            else if (RoleInfo.getTokenEconomical() == 5){
                icoRessourceBotPopup.setImageResource(R.drawable.five_e);
            }
            else if (RoleInfo.getTokenEconomical() == 6){
                icoRessourceBotPopup.setImageResource(R.drawable.six_e);
            }
            else if (RoleInfo.getTokenEconomical() == 7){
                icoRessourceBotPopup.setImageResource(R.drawable.seven_e);
            }
        }
    }
}