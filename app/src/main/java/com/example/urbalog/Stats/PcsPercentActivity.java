package com.example.urbalog.Stats;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urbalog.Database.DatabaseHandler;
import com.example.urbalog.NetworkHelper;
import com.example.urbalog.R;
import com.example.urbalog.StatsActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PcsPercentActivity extends AppCompatActivity implements OnChartValueSelectedListener{

    PieChart pieChart;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcs_percent);

        db = new DatabaseHandler(this);

        pieChart = findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(0, 0, 0, 0);

        pieChart.setDragDecelerationFrictionCoef(0.1f);

        pieChart.setEntryLabelColor(Color.rgb(0, 0, 0));

        pieChart.setDrawHoleEnabled(false);
        pieChart.setTransparentCircleRadius(1f);
        HashMap<String, Integer> res = getPercentPcs();

        ArrayList<PieEntry> yValues = new ArrayList<>();

        for(Map.Entry<String, Integer> entry: res.entrySet()) {
            //Log.d("debug", "key = " + entry.getKey() + "  --  value = " + entry.getValue());
            yValues.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        pieChart.animateY(1000, Easing.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);

        //example of array of colors
        int[] testColor = {
                Color.rgb(227, 86, 103),
                Color.rgb(227, 137, 85),
                Color.rgb(226,207,86),
                Color.rgb(173, 226, 86),
                Color.rgb(104, 227, 87),
                Color.rgb(86,226,137),
                Color.rgb(87, 226, 207),
                Color.rgb(87, 174, 227),
                Color.rgb(86,105,226),
                Color.rgb(139, 86, 226)
        };
        List<Integer> test = ColorTemplate.createColors(testColor);

        dataSet.setColors(test);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(20f);
        data.setValueTextColors(Collections.singletonList(Color.BLACK));
        pieChart.setData(data);


        pieChart.setOnChartValueSelectedListener(this);


    }

    private HashMap<String, Integer> getPercentPcs(){
        Log.d("debug", "getPercentPcs...");
        HashMap<String, Integer> res = new HashMap<>();

        Cursor mCursor = db.getOneDataFromTable(DatabaseHandler.PLAYER_TABLE_NAME, DatabaseHandler.PLAYER_JOB);
        String pcs;

        int nbEtudiant =0;
        int nbSansEmploi=0;
        int nbOuvrier=0;
        int nbRetraite=0;
        int nbAgriculteur=0;
        int nbEmploye=0;
        int nbCadre=0;
        int nbMain=0;
        int nbArtisant=0;
        int nbProfession=0;
        while(mCursor.moveToNext()) {
            pcs = mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.PLAYER_JOB));
            switch (pcs) {
                case "Etudiant":
                    nbEtudiant++;
                    break;
                case "Sans emploi":
                    nbSansEmploi++;
                    break;
                case "Ouvrier qualifié":
                    nbOuvrier++;
                    break;
                case "Employé et personnel de service":
                    nbEmploye++;
                    break;
                case "Cadres et profession intellectuelle supérieure":
                    nbCadre++;
                    break;
                case "Retraité":
                    nbRetraite++;
                    break;
                case "Agriculteur, exploitant":
                    nbAgriculteur++;
                    break;
                case "Main d'oeuvre et ouvrier specialisé":
                    nbMain++;
                    break;
                case "Artisan, commerçant, chef d'entreprise, profession libérale":
                    nbArtisant++;
                    break;
                case "Profession intérmediaire, cadre moyen":
                    nbProfession++;
                    break;
            }
        }

        if(nbEtudiant != 0)
        {
            res.put("Etudiant", nbEtudiant);
        }
        if(nbSansEmploi != 0)
        {
            res.put("Sans emploi", nbSansEmploi);
        }
        if(nbOuvrier != 0)
        {
            res.put("Ouvrier qualifié", nbOuvrier);
        }
        if(nbEmploye != 0)
        {
            res.put("Employé et personnel de service", nbEmploye);
        }
        if(nbCadre != 0)
        {
            res.put("Cadres et profession intellectuelle supérieure", nbCadre);
        }
        if(nbRetraite != 0)
        {
            res.put("Retraité", nbRetraite);
        }
        if(nbAgriculteur != 0)
        {
            res.put("Agriculteur, exploitant", nbAgriculteur);
        }
        if(nbMain != 0)
        {
            res.put("Main d'oeuvre et ouvrier specialisé", nbMain);
        }
        if(nbArtisant != 0)
        {
            res.put("Artisan, commerçant, chef d'entreprise, profession libérale", nbArtisant);
        }
        if(nbProfession != 0) {
            res.put("Profession intérmediaire, cadre moyen", nbProfession);
        }
        Log.d(NetworkHelper.TAG, "Res = "+res.toString());
        return res;
    }

    public void onBackPressed(){
        Intent intent = new Intent(PcsPercentActivity.this, StatsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}