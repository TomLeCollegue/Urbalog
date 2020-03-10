package com.example.urbalog.stats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.urbalog.ConfigurationActivity;
import com.example.urbalog.Json.JsonStats;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcs_percent);

        JsonStats.giveContext(this);

        pieChart = (PieChart) findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(0, 0, 0, 0);

        pieChart.setDragDecelerationFrictionCoef(0.1f);

        pieChart.setEntryLabelColor(Color.rgb(0, 0, 0));

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

    public void onBackPressed(){
        Intent intent = new Intent(PcsPercentActivity.this, StatsActivity.class);
        startActivity(intent);
        finish();
        return;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}