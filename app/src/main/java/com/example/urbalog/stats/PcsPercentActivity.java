package com.example.urbalog.stats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.urbalog.ConfigurationActivity;
import com.example.urbalog.Json.JsonStats;
import com.example.urbalog.R;
import com.example.urbalog.StatsActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PcsPercentActivity extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcs_percent);

        JsonStats.init(this);

        pieChart = (PieChart) findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 5, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.1f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setTransparentCircleRadius(1f);

        HashMap<String, Integer> res = JsonStats.getPercentPcs();

        ArrayList<PieEntry> yValues = new ArrayList<>();

        for(Map.Entry<String, Integer> entry: res.entrySet()) {
            //Log.d("debug", "key = " + entry.getKey() + "  --  value = " + entry.getValue());
            yValues.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        pieChart.animateY(1000, Easing.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(40f);

        int[] testColor = {
                Color.rgb(221,160,221), Color.rgb(255,215,0),
                Color.rgb(0,128,128), Color.rgb(0,0,255),
                Color.rgb(255,160,122), Color.rgb(128,128,0),
                Color.rgb(72,209,204), Color.rgb(139,0,139),
                Color.rgb(255,215,0), Color.rgb(124,252,0)
        };




        List<Integer> test = ColorTemplate.createColors(testColor);

        dataSet.setColors(test);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(20f);
        data.setValueTextColors(Collections.singletonList(Color.BLACK));


        pieChart.setData(data);
    }

    public void onBackPressed(){
        Intent intent = new Intent(PcsPercentActivity.this, StatsActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}