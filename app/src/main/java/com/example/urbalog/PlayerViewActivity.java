package com.example.urbalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

import java.io.IOException;
import java.util.ArrayList;

import com.example.urbalog.Class.Bet;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.Player;
import com.example.urbalog.Class.TransferPackage;
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
    private TextView textAvancementRessourceTop;
    private TextView TextAvancementRessourceBot;

    private Button buttonMinusTop;
    private Button buttonMinusBot;
    private Button buttonPlusTop;
    private Button buttonPlusBot;

    private Integer AjoutFinancementSocial;
    private Integer AjoutFinancementEco;
    private Integer AjoutFinancementPolitique;

    private Integer numBuildingF;

    private Bet mise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_view_activity);

        /* Link player activity to NetworkHelper */
        PlayerConnexionActivity.net.setCurrentPlayerView(this);

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
                //showPopUp(v, 4);
                fillInfosView();
            }
        });

        fillInfosView();

    }

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
    }

    public void showPopUp(View v, int numBuilding)
    {
        numBuildingF = numBuilding;
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.bet_popup, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        popUpBet = new PopupWindow(popUpView, width, height, focusable);

        buttonBetPopup = (Button)popUpView.findViewById(R.id.button_bet_popup);
        textNameBuildingPopup = (TextView)popUpView.findViewById(R.id.text_name_building_popup);

        textAvancementRessourceTop = (TextView) popUpView.findViewById(R.id.avancement_ressource_top);
        TextAvancementRessourceBot= (TextView) popUpView.findViewById(R.id.avancement_ressource_bot);

        buttonMinusTop= (Button) popUpView.findViewById(R.id.button_minus_top);
        buttonMinusBot= (Button) popUpView.findViewById(R.id.button_minus_bot);
        buttonPlusTop= (Button) popUpView.findViewById(R.id.button_plus_top);
        buttonPlusBot= (Button) popUpView.findViewById(R.id.button_plus_bot);

        AjoutFinancementSocial = 0;
        AjoutFinancementEco= 0;
        AjoutFinancementPolitique=0;

        textNameBuildingPopup.setText(PlayerConnexionActivity.net.getCurrentGame().getMarket().getBuildings().get(numBuilding).getName());
        textAvancementRessourceTop.setText(String.valueOf(AjoutFinancementSocial));
        TextAvancementRessourceBot.setText(String.valueOf(AjoutFinancementEco));

        buttonPlusTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AjoutFinancementSocial++;
                //textAvancementRessourceTop.setText(String.valueOf(AjoutFinancementSocial));
                mise = new Bet(numBuildingF, 0, 0, 1);
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonMinusTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AjoutFinancementSocial--;
                //textAvancementRessourceTop.setText(String.valueOf(AjoutFinancementSocial));
                mise = new Bet(numBuildingF, 0, 0, -1);
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonPlusBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjoutFinancementEco++;
                TextAvancementRessourceBot.setText(String.valueOf(AjoutFinancementEco));
                mise = new Bet(numBuildingF, 0, 1, 0);
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonMinusBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjoutFinancementEco--;
                TextAvancementRessourceBot.setText(String.valueOf(AjoutFinancementEco));
                mise = new Bet(numBuildingF, 0, -1, 0);
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

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
                /*mise = new Bet(numBuildingF, AjoutFinancementPolitique, AjoutFinancementEco, AjoutFinancementSocial);
                try {
                    PlayerConnexionActivity.net.sendToAllClients(new TransferPackage<Game, Bet> (PlayerConnexionActivity.net.getCurrentGame(), mise));
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
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
