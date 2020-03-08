package com.example.urbalog.stats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.urbalog.ConfigurationActivity;
import com.example.urbalog.Json.JsonStats;
import com.example.urbalog.R;
import com.example.urbalog.StatsActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NbPlayerByGameActivity extends AppCompatActivity{

    private LineChart mChart;
    private int i = 0;
    private final ArrayList<String> ar = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nb_player_by_game);

        JsonStats.giveContext(this);
        HashMap<String, Integer> nbPlayerByGame = JsonStats.getNumberPlayerByGame();


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

        for(Map.Entry<String, Integer> entry: nbPlayerByGame.entrySet()) {
            yValues.add(new Entry(i, entry.getValue()));
            ar.add(entry.getKey());
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