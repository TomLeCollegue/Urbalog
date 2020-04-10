package com.example.urbalog.Stats;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urbalog.Database.DatabaseHandler;
import com.example.urbalog.Json.JsonStats;
import com.example.urbalog.NetworkHelper;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class AverageScoreActivity extends AppCompatActivity {

    private LineChart mChart;
    private DatabaseHandler db;
    private final ArrayList<String> ar = new ArrayList<String>();
    private final ArrayList<String> ar2 = new ArrayList<String>();
    private final ArrayList<String> ar3 = new ArrayList<String>();
    private final ArrayList<String> ar4 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_score);

        db = new DatabaseHandler(this);
        HashMap<Date, Integer> nbScoreLogistiqueByGame = getScoreLogistiqueByGame();
        Map<Date, Integer> nbScoreLogistiqueByGameSorted = new TreeMap<Date, Integer>(nbScoreLogistiqueByGame);

        HashMap<Date, Integer> nbScoreAttractiviteByGame = getScoreAttractiviteByGame();
        Map<Date, Integer> nbScoreAttractiviteByGameSorted = new TreeMap<Date, Integer>(nbScoreAttractiviteByGame);

        HashMap<Date, Integer> nbScoreFluiditeByGame = getScoreFluiditeByGame();
        Map<Date, Integer> nbScoreFluiditeByGameSorted = new TreeMap<Date, Integer>(nbScoreFluiditeByGame);

        HashMap<Date, Integer> nbScoreEnvironnementalByGame = getScoreEnvironnementalByGame();
        Map<Date, Integer> nbScoreEnvironnementalByGameSorted = new TreeMap<Date, Integer>(nbScoreEnvironnementalByGame);

        mChart = findViewById(R.id.linechart);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setExtraOffsets(10, 10, 10, 10);
        mChart.getDescription().setEnabled(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(10);
        leftAxis.setAxisMinimum(-10);
        leftAxis.setLabelCount(20);
        leftAxis.setDrawGridLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        ArrayList<Entry> yValues1 = new ArrayList<>();
        ArrayList<Entry> yValues2 = new ArrayList<>();
        ArrayList<Entry> yValues3 = new ArrayList<>();
        ArrayList<Entry> yValues4 = new ArrayList<>();

        int i = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm");

        for(Map.Entry<Date, Integer> entry: nbScoreLogistiqueByGameSorted.entrySet()) {
            yValues1.add(new Entry(i, entry.getValue()));
            String str = dateFormat.format(entry.getKey());
            ar.add(str);
            i++;
        }

        i = 0;

        for(Map.Entry<Date, Integer> entry: nbScoreAttractiviteByGameSorted.entrySet()) {
            yValues2.add(new Entry(i, entry.getValue()));
            String str = dateFormat.format(entry.getKey());
            ar2.add(str);
            i++;
        }

        i = 0;

        for(Map.Entry<Date, Integer> entry: nbScoreFluiditeByGameSorted.entrySet()) {
            yValues3.add(new Entry(i, entry.getValue()));
            String str = dateFormat.format(entry.getKey());
            ar3.add(str);
            i++;
        }

        i = 0;

        for(Map.Entry<Date, Integer> entry: nbScoreEnvironnementalByGameSorted.entrySet()) {
            yValues4.add(new Entry(i, entry.getValue()));
            String str = dateFormat.format(entry.getKey());
            ar4.add(str);
            i++;
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
        xAxis.setLabelCount(i-1);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ar.get((int)value);
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(AverageScoreActivity.this, StatsActivity.class);
        startActivity(intent);
        finish();
    }

    private HashMap<Date, Integer> getScoreLogistiqueByGame(){
        Log.d("debug", "getScoreLogistiqueByGame...");
        HashMap<Date, Integer> res = new HashMap<>();
        Date date;
        int nbScore;

        Cursor mCursor = db.getMultipleDataFromTable(DatabaseHandler.GAME_TABLE_NAME, new String[]{DatabaseHandler.GAME_SCORE_LOG, DatabaseHandler.GAME_CREATED_AT});
        try {
            while(mCursor.moveToNext()){
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.getDefault());
                date = dateFormat.parse(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_CREATED_AT)));
                nbScore = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_SCORE_LOG)));
                res.put(date, nbScore);
            }
        } catch(ParseException e){
            e.printStackTrace();
        }
        mCursor.close();
        Log.d(NetworkHelper.TAG, "Res = "+res.toString());
        return res;
    }

    private HashMap<Date, Integer> getScoreAttractiviteByGame(){
        Log.d("debug", "getScoreAttractiviteByGame...");
        HashMap<Date, Integer> res = new HashMap<>();
        Date date;
        int nbScore;

        Cursor mCursor = db.getMultipleDataFromTable(DatabaseHandler.GAME_TABLE_NAME, new String[]{DatabaseHandler.GAME_SCORE_ATTR, DatabaseHandler.GAME_CREATED_AT});
        try {
            while(mCursor.moveToNext()){
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.getDefault());
                date = dateFormat.parse(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_CREATED_AT)));
                nbScore = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_SCORE_ATTR)));
                res.put(date, nbScore);
            }
        } catch(ParseException e){
            e.printStackTrace();
        }
        mCursor.close();
        Log.d(NetworkHelper.TAG, "Res = "+res.toString());
        return res;
    }

    private HashMap<Date, Integer> getScoreFluiditeByGame(){
        Log.d("debug", "getScoreFluiditeByGame...");
        HashMap<Date, Integer> res = new HashMap<>();
        Date date;
        int nbScore;

        Cursor mCursor = db.getMultipleDataFromTable(DatabaseHandler.GAME_TABLE_NAME, new String[]{DatabaseHandler.GAME_SCORE_FLUID, DatabaseHandler.GAME_CREATED_AT});
        try {
            while(mCursor.moveToNext()){
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.getDefault());
                date = dateFormat.parse(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_CREATED_AT)));
                nbScore = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_SCORE_FLUID)));
                res.put(date, nbScore);
            }
        } catch(ParseException e){
            e.printStackTrace();
        }
        mCursor.close();
        Log.d(NetworkHelper.TAG, "Res = "+res.toString());
        return res;
    }

    private HashMap<Date, Integer> getScoreEnvironnementalByGame(){
        Log.d("debug", "getScoreEnvironnementalByGame...");
        HashMap<Date, Integer> res = new HashMap<>();
        Date date;
        int nbScore;

        Cursor mCursor = db.getMultipleDataFromTable(DatabaseHandler.GAME_TABLE_NAME, new String[]{DatabaseHandler.GAME_SCORE_ENV, DatabaseHandler.GAME_CREATED_AT});
        try {
            while(mCursor.moveToNext()){
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.getDefault());
                date = dateFormat.parse(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_CREATED_AT)));
                nbScore = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_SCORE_ENV)));
                res.put(date, nbScore);
            }
        } catch(ParseException e){
            e.printStackTrace();
        }
        mCursor.close();
        Log.d(NetworkHelper.TAG, "Res = "+res.toString());
        return res;
    }
}