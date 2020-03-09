package com.example.urbalog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.urbalog.Adapter.FormAdapter;
import com.example.urbalog.Class.Player;

public class FormActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;

    private TextView[] mDots;

    private FormAdapter formAdapter;

    private Button back;
    private Button next;
    private int currentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotsLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        next = (Button)findViewById(R.id.form_button_next);
        back = (Button)findViewById(R.id.form_button_back);

        formAdapter = new FormAdapter(this);
        mSlideViewPager.setAdapter(formAdapter);



        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPage == formAdapter.getCount()-1){
                    String name = formAdapter.getName();
                    int age = formAdapter.getAge();
                    String pcs = formAdapter.getPcs();
                    if(name == null || name.trim().matches(""))
                    {
                        Log.d("debug", "name null");
                        getViewPager().setCurrentItem(0);
                        Toast.makeText(FormActivity.this, "le champ n'est pas rempli", Toast.LENGTH_SHORT).show();
                    }
                    else if(age == 0){
                        Log.d("debug", "age pas correct");
                        getViewPager().setCurrentItem(1);
                        Toast.makeText(FormActivity.this, "le champ n'est pas rempli", Toast.LENGTH_SHORT).show();
                    }
                    else if(pcs == null)
                    {
                        Log.d("debug", "pcs null");
                        getViewPager().setCurrentItem(2);
                        Toast.makeText(FormActivity.this, "le champ n'est pas rempli", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        name = name.trim();
                        Player player = new Player(name, age, pcs, null);
                        Intent myIntent = new Intent(FormActivity.this, PlayerConnexionActivity.class);
                        myIntent.putExtra("player", player);
                        startActivity(myIntent);
                        finish();
                    }
                }else{
                    mSlideViewPager.setCurrentItem(currentPage + 1);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPage == 0){
                    Player player = new Player(null);
                    Intent myIntent = new Intent(FormActivity.this, PlayerConnexionActivity.class);
                    myIntent.putExtra("player", player);
                    startActivity(myIntent);
                    finish();
                }
                mSlideViewPager.setCurrentItem(currentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotsLayout.removeAllViews();

        for (int i=0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(50);
            mDots[i].setTextColor(getResources().getColor(R.color.white));
            mDotsLayout.addView(mDots[i]);
        }

        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.mediumPink));
        }
    }

    public ViewPager getViewPager(){
        return this.mSlideViewPager;
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;

            if(position == 0){
                next.setEnabled(true);
                back.setEnabled(true);
                back.setText("Passer");
                next.setText("Suivant");
            } else if (position == mDots.length -1 ){
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                next.setText("Terminer");
                back.setText("Précédent");
            } else {
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                next.setText("Suivant");
                back.setText("Précédent");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}