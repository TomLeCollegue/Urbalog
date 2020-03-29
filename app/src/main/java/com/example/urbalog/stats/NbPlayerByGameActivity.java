package com.example.urbalog.Stats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class NbPlayerByGameActivity extends AppCompatActivity{

    private LineChart mChart;
    private int i = 0;
    private final ArrayList<String> ar = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nb_player_by_game);

        JsonStats.giveContext(this);

        HashMap<Date, Integer> nbPlayerByGame = JsonStats.getNumberPlayerByGame();
        Map<Date, Integer> nbPlayerByGameSorted = new TreeMap<Date, Integer>(nbPlayerByGame);

        mChart = (LineChart) findViewById(R.id.linechart);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setExtraOffsets(10, 10, 10, 10);
        mChart.getDescription().setEnabled(false);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(10);
        leftAxis.setAxisMinimum(0);
        leftAxis.setDrawGridLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        for(Map.Entry<Date, Integer> entry: nbPlayerByGameSorted.entrySet()) {
            yValues.add(new Entry(i, entry.getValue()));
            String str = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(entry.getKey());
            ar.add(str);
            i++;
        }


        LineDataSet set1 = new LineDataSet(yValues, "Nombre de joueur");

        set1.setFillAlpha(110);
        set1.setCircleColor(Color.RED);
        set1.setColor(Color.BLUE);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);

        mChart.setData(data);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setLabelCount(i-1);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ar.get((int)value);
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(NbPlayerByGameActivity.this, StatsActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}