package com.example.urbalog.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.urbalog.FormActivity;
import com.example.urbalog.R;

import java.util.Arrays;

public class FormAdapter extends PagerAdapter{

    private Context context;
    private LayoutInflater layoutInflater;
    private Button pcs;
    private NumberPicker age;
    private EditText name;

    private int finalAge;
    private String finalName;
    private String finalPcs;


    public FormAdapter(Context context){
        this.context = context;

    }

    public String[] slide_titles = {
            "Quel est votre prénom ?",
            "Quel est votre age ?",
            "Dans quelle catégorie socio-professionnelle vous situez-vous ?"
    };

    public String[] slide_hints = {
            "prénom...",
            "30",
            "Dans quelle catégorie socio-professionnelle vous situez-vous ?"
    };

    public String[] slide_pcs = {
            "Cadres et profession intellectuelle supérieure",
            "Profession intérmediaire, cadre moyen",
            "Etudiant",
            "Ouvrier qualifié",
            "Artisan, commerçant, chef d'entreprise, profession libérale",
            "Employé et personnel de service",
            "Sans emploi",
            "Main d'oeuvre et ouvrier specialisé",
            "Agriculteur, exploitant",
            "Retraité"
    };


    @Override
    public int getCount() {
        return slide_titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        TextView title = view.findViewById(R.id.slide_titre);
        title.setText(slide_titles[position]);

        name = view.findViewById(R.id.slide_res);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                finalName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pcs = view.findViewById(R.id.button_form_pcs);
        pcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Votre PCS");
                builder.setSingleChoiceItems(slide_pcs,
                        -1,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedItem = Arrays.asList(slide_pcs).get(which);
                                pcs.setText(selectedItem);
                                finalPcs = selectedItem;
                            }
                        });
                builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //dismiss the alert dialog after selection
                    }
                });
                // Create the alert dialog
                AlertDialog dialog = builder.create();
                //display the alert dialog
                dialog.show();
            }
        });

        age = (NumberPicker) view.findViewById(R.id.slide_age);
        age.setMinValue(1);
        age.setValue(20);
        finalAge = age.getValue();
        age.setMaxValue(99);
        age.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                finalAge = newVal;
            }
        });

        switch(position){
            case 0:
                name.setHint(slide_hints[position]);
                pcs.setVisibility(View.GONE);
                age.setVisibility(View.GONE);
                break;
            case 1:
                name.setVisibility(View.GONE);
                pcs.setVisibility(View.GONE);
                break;
            case 2:
                name.setVisibility(View.GONE);
                age.setVisibility(View.GONE);
                break;
        }

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

    public int getAge(){
        return finalAge;
    }

    public String getName(){
        return finalName;
    }

    public String getPcs(){
        return finalPcs;
    }

    public EditText getEditTextName(){
        return name;
    }

    public void setNameError(int position){
        getEditTextName().setError("Il n'y a pas de nom");
    }



}
