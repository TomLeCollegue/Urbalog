package com.example.urbalog.EndGameStats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.urbalog.AdminConnectionActivity;
import com.example.urbalog.Class.Game;
import com.example.urbalog.ConfigurationActivity;
import com.example.urbalog.EndGameActivity;
import com.example.urbalog.Json.JsonStats;
import com.example.urbalog.R;
import com.example.urbalog.StatsActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StatsScorePerRoundActivity extends AppCompatActivity {

    private LineChart mChart;
    private ArrayList<Game> arrayListGame = new ArrayList<Game>();
    private final ArrayList<String> ar = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_score_per_round);



        HashMap<Integer, Integer> nbScoreLogistiqueByTurn = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> nbScoreAttractiviteByTurn = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> nbScoreFluiditeByTurn = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> nbScoreEnvironnementalByTurn = new HashMap<Integer, Integer>();


            /* Fake game for testing
            Game a = new Game();
            a.setnTurn(1);
            a.setScoreLogistique(0);
            a.setScoreAttractivite(2);
            a.setScoreFluidite(3);
            a.setScoreEnvironnemental(2);

            arrayListGame.add(a);

            Game b = new Game();
            b.setnTurn(2);
            b.setScoreLogistique(-2);
            b.setScoreAttractivite(4);
            b.setScoreFluidite(0);
            b.setScoreEnvironnemental(1);
            arrayListGame.add(b);
            Game c = new Game();
            c.setnTurn(3);
            c.setScoreLogistique(-4);
            c.setScoreAttractivite(7);
            c.setScoreFluidite(-1);
            c.setScoreEnvironnemental(5);
            arrayListGame.add(c);
           */

        arrayListGame = AdminConnectionActivity.net.getCurrentGame().getArrayGame();

        for(int i=0; i<arrayListGame.size(); ++i){
            Game game = arrayListGame.get(i);
            nbScoreLogistiqueByTurn.put(game.getnTurn(), game.getScoreLogistique());
            nbScoreAttractiviteByTurn.put(game.getnTurn(), game.getScoreAttractivite());
            nbScoreFluiditeByTurn.put(game.getnTurn(), game.getScoreFluidite());
            nbScoreEnvironnementalByTurn.put(game.getnTurn(), game.getScoreEnvironnemental());
        }

        mChart = findViewById(R.id.linechart);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setExtraOffsets(10, 10, 10, 10);
        mChart.getDescription().setEnabled(false);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setAxisMaximum(10);
        leftAxis.setAxisMinimum(-10);
        leftAxis.setLabelCount(20);
        leftAxis.setDrawGridLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);



        ArrayList<Entry> yValues1 = new ArrayList<>();
        int nb =0;
        for(Map.Entry<Integer, Integer> entry: nbScoreLogistiqueByTurn.entrySet()){
            yValues1.add(new Entry(nb, entry.getValue()));
            ar.add("tour : " + entry.getKey().toString());
            nb++;
        }

        ArrayList<Entry> yValues2 = new ArrayList<>();
        nb =0;
        for(Map.Entry<Integer, Integer> entry: nbScoreAttractiviteByTurn.entrySet()){
            yValues2.add(new Entry(nb, entry.getValue()));
            nb++;
        }

        ArrayList<Entry> yValues3 = new ArrayList<>();
        nb =0;
        for(Map.Entry<Integer, Integer> entry: nbScoreFluiditeByTurn.entrySet()){
            yValues3.add(new Entry(nb, entry.getValue()));
            nb++;
        }

        ArrayList<Entry> yValues4 = new ArrayList<>();
        nb =0;
        for(Map.Entry<Integer, Integer> entry: nbScoreEnvironnementalByTurn.entrySet()){
            yValues4.add(new Entry(nb, entry.getValue()));
            nb++;
        }

        LineDataSet set1 = new LineDataSet(yValues1, "Score Logistique");
        LineDataSet set2 = new LineDataSet(yValues2, "Score Attractivité");
        LineDataSet set3 = new LineDataSet(yValues3, "Score Fluidité");
        LineDataSet set4 = new LineDataSet(yValues4, "Score Environnemental");

        set1.setColor(Color.RED);
        set2.setColor(Color.BLUE);
        set3.setColor(Color.YELLOW);
        set4.setColor(Color.GREEN);


        set1.setCircleColor(Color.TRANSPARENT);
        set2.setCircleColor(Color.TRANSPARENT);
        set3.setCircleColor(Color.TRANSPARENT);
        set4.setCircleColor(Color.TRANSPARENT);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        dataSets.add(set4);
        LineData data = new LineData(dataSets);

        mChart.setData(data);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setLabelCount(nb-1);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ar.get((int)value);
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(StatsScorePerRoundActivity.this, EndGameActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}