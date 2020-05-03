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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class NbPlayerByGameActivity extends AppCompatActivity{

    private LineChart mChart;
    private DatabaseHandler db;
    private final ArrayList<String> ar = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nb_player_by_game);

        db = new DatabaseHandler(this);

        HashMap<Date, Integer> nbPlayerByGame = getNumberPlayerByGame();
        Map<Date, Integer> nbPlayerByGameSorted = new TreeMap<Date, Integer>(nbPlayerByGame);

        mChart = findViewById(R.id.linechart);
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

        int i = 0;
        for(Map.Entry<Date, Integer> entry: nbPlayerByGameSorted.entrySet()) {
            yValues.add(new Entry(i, entry.getValue()));
            String str = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(entry.getKey());
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

    private HashMap<Date, Integer> getNumberPlayerByGame(){
        Log.d("debug", "getNumberPlayerByGame...");
        HashMap<Date, Integer> res = new HashMap<>();
        Date date;
        int nbPlayer;

        Cursor mCursor = db.getMultipleDataFromTable(DatabaseHandler.GAME_TABLE_NAME, new String[]{DatabaseHandler.GAME_NB_PLAYER, DatabaseHandler.GAME_CREATED_AT});
        try {
            while(mCursor.moveToNext()){
                if(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_CREATED_AT)) != null && mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_NB_PLAYER)) != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.getDefault());
                    date = dateFormat.parse(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_CREATED_AT)));
                    nbPlayer = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.GAME_NB_PLAYER)));
                    res.put(date, nbPlayer);
                }
            }
        } catch(ParseException e){
            e.printStackTrace();
        }
        mCursor.close();
        Log.d(NetworkHelper.TAG, "Res = "+res.toString());
        return res;
    }

    public void onBackPressed(){
        Intent intent = new Intent(NbPlayerByGameActivity.this, StatsActivity.class);
        startActivity(intent);
        finish();
    }
}