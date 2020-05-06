package com.example.urbalog.EndGameStats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.urbalog.R;
import com.github.mikephil.charting.charts.LineChart;

public class StatsScorePerRoundActivity extends AppCompatActivity {

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_score_per_round);


    }
}