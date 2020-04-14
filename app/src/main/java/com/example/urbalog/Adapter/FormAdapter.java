package com.example.urbalog.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.urbalog.R;

public class FormAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    TextView title1;
    TextView title2;
    TextView title3;

    private EditText firstName;
    private EditText name;
    private Spinner sexe;

    private Spinner age;
    private EditText residence;
    private Spinner statutActivité;

    private Spinner profession;
    private Spinner secteurActivité;
    private EditText entreprise;

    /*---------------------------*/

    private String finalFirstName;
    private String finalName;
    private String finalSexe;

    private String finalAge;
    private String finalResidence;
    private String finalStatutActivite;

    private String finalProfession;
    private String finalSecteurActivite;
    private String finalEntreprise;


    public FormAdapter(Context context){
        this.context = context;
        this.finalFirstName = "";
        this.finalName = "";
        this.finalSexe = "";
        this.finalAge = "";
        this.finalResidence = "";
        this.finalStatutActivite = "";
        this.finalProfession = "";
        this.finalSecteurActivite = "";
        this.finalEntreprise = "";

    }

    public String[] slide_titles_1 = {
            "Quel est votre prénom ?",
            "Quel est votre age ?",
            "Quel est votre statut d'activité ?"
    };

    public String[] slide_titles_2 = {
            "Quel est votre nom ?",
            "Quel est votre lieu de résidence ?",
            "Quelle est votre profession ?"
    };

    public String[] slide_titles_3 = {
            "Quel est votre sexe ?",
            "Quelle est votre entreprise?",
            "Quel est votre secteur d'activité ?"
    };

    private String[] slide_age = {
            "Choisir...",
            "Moins de 15 ans",
            "De 15 à 18 ans",
            "De 19 à 24 ans",
            "De 25 à 34 ans",
            "De 35 à 44 ans",
            "De 45 à 54 ans",
            "De 55 à 64 ans",
            "65 ans et plus"
    };

    private String[] slide_sexe = {
            "Choisir...",
            "Féminin",
            "Masculin",
            "Ni l'un ni l'autre"
    };

    private String[] slide_statut_activite = {
            "Choisir...",
            "Salarié",
            "Indépendant",
            "Chômeur",
            "Retraité",
            "Autres inactifs (dont étudiants)"
    };

    private String[] slide_profession = {
            "Choisir...",
            "Agriculteur",
            "Artisant, commerçant, chef d'entreprise",
            "Cadre",
            "Profession intermédiaire",
            "Employé",
            "Ouvier"
    };

    public String[] slide_secteur_activite = {
            "Choisir...",
            "Agriculture",
            "Industrie",
            "Construction",
            "Commerce",
            "Hébergement et restauration",
            "Information communication",
            "Finance, assurance, immobilier",
            "Activités scientifiques, techniques, services administratifs",
            "Administration publique",
            "Enseignement",
            "Activité pour la santé humaine",
            "Hébergement médico-social et social et action sociale sans hébergement",
            "Autres services",
            "Activité indéterminée"
    };


    @Override
    public int getCount() {
        return slide_titles_1.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        title1 = view.findViewById(R.id.slide_title_1);
        title2 = view.findViewById(R.id.slide_title_2);
        title3 = view.findViewById(R.id.slide_title_3);

        title1.setText(slide_titles_1[position]);
        title2.setText(slide_titles_2[position]);
        title3.setText(slide_titles_3[position]);


        firstName = view.findViewById(R.id.slide_firstName);
        firstName.setHint("Votre prénom...");
        firstName.setText(finalFirstName);
        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                finalFirstName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        name = view.findViewById(R.id.slide_name);
        name.setHint("Votre nom...");
        name.setText(finalName);
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

        sexe = view.findViewById(R.id.slide_sexe);
        ArrayAdapter<String> dataAdapterSexe = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, slide_sexe);
        dataAdapterSexe.setDropDownViewResource(android.R.layout.select_dialog_item);
        sexe.setAdapter(dataAdapterSexe);
        sexe.setGravity(Gravity.CENTER);
        sexe.setSelection(dataAdapterSexe.getPosition(finalSexe));
        sexe.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    finalSexe = slide_sexe[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        age = view.findViewById(R.id.slide_age);
        ArrayAdapter<String> dataAdapterAge = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, slide_age);
        dataAdapterAge.setDropDownViewResource(android.R.layout.select_dialog_item);
        age.setAdapter(dataAdapterAge);
        age.setGravity(Gravity.CENTER);
        age.setSelection(dataAdapterAge.getPosition(finalAge));
        age.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    finalAge = slide_age[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        residence = view.findViewById(R.id.slide_residence);
        residence.setText(finalResidence);
        residence.setHint("Votre code postal...");
        residence.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                finalResidence = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        profession = view.findViewById(R.id.slide_profession);
        ArrayAdapter<String> dataAdapterProfession = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, slide_profession);
        dataAdapterProfession.setDropDownViewResource(android.R.layout.select_dialog_item);
        profession.setAdapter(dataAdapterProfession);
        profession.setGravity(Gravity.CENTER);
        profession.setSelection(dataAdapterProfession.getPosition(finalProfession));
        profession.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    finalProfession = slide_profession[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        statutActivité = view.findViewById(R.id.slide_statutActivite);
        ArrayAdapter<String> dataAdapterStatutActivite = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, slide_statut_activite);
        dataAdapterStatutActivite.setDropDownViewResource(android.R.layout.select_dialog_item);
        statutActivité.setAdapter(dataAdapterStatutActivite);
        statutActivité.setGravity(Gravity.CENTER);
        statutActivité.setSelection(dataAdapterStatutActivite.getPosition(finalStatutActivite));
        statutActivité.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    finalStatutActivite = slide_statut_activite[position];
                }
                switch(position) {
                    case 1:
                        finalProfession = "";
                        title2.setVisibility(View.VISIBLE);
                        profession.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        finalProfession = "";
                        title2.setVisibility(View.VISIBLE);
                        profession.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        finalProfession = "";
                        title2.setVisibility(View.VISIBLE);
                        profession.setVisibility(View.VISIBLE);
                        break;
                    default:
                        finalProfession = null;
                        title2.setVisibility(View.GONE);
                        profession.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        secteurActivité = view.findViewById(R.id.slide_secteurActivite);
        ArrayAdapter<String> dataAdapterSecteurActivite = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, slide_secteur_activite);
        dataAdapterSecteurActivite.setDropDownViewResource(android.R.layout.select_dialog_item);
        secteurActivité.setAdapter(dataAdapterSecteurActivite);
        secteurActivité.setGravity(Gravity.CENTER);
        secteurActivité.setSelection(dataAdapterSecteurActivite.getPosition(finalSecteurActivite));
        secteurActivité.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    finalSecteurActivite = slide_secteur_activite[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        entreprise = view.findViewById(R.id.slide_entreprise);
        entreprise.setHint("Le nom de l'entreprise qui vous emploie");
        entreprise.setText(finalEntreprise);
        entreprise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                finalEntreprise = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        switch(position){
            case 0:
                age.setVisibility(View.GONE);
                residence.setVisibility(View.GONE);
                entreprise.setVisibility(View.GONE);
                statutActivité.setVisibility(View.GONE);
                profession.setVisibility(View.GONE);
                secteurActivité.setVisibility(View.GONE);
                break;
            case 1:
                firstName.setVisibility(View.GONE);
                name.setVisibility(View.GONE);
                sexe.setVisibility(View.GONE);
                secteurActivité.setVisibility(View.GONE);
                profession.setVisibility(View.GONE);
                statutActivité.setVisibility(View.GONE);
                break;
            case 2:
                firstName.setVisibility(View.GONE);
                name.setVisibility(View.GONE);
                sexe.setVisibility(View.GONE);
                age.setVisibility(View.GONE);
                residence.setVisibility(View.GONE);
                entreprise.setVisibility(View.GONE);

                profession.setVisibility(View.GONE);
                title2.setVisibility(View.GONE);
                break;
        }

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }



    public String getFinalName() {
        return finalName;
    }

    public String getFinalSexe() {
        return finalSexe;
    }

    public String getFinalAge() {
        return finalAge;
    }

    public String getFinalResidence() {
        return finalResidence;
    }

    public String getFinalStatutActivite() {
        return finalStatutActivite;
    }

    public String getFinalProfession() {
        return finalProfession;
    }

    public String getFinalSecteurActivite() {
        return finalSecteurActivite;
    }

    public String getFinalEntreprise() {
        return finalEntreprise;
    }

    public String getFinalFirstName(){
        return finalFirstName;
    }

    public EditText getEditTextName(){
        return name;
    }

    public void setNameError(int position){
        getEditTextName().setError("Il n'y a pas de nom");
    }



}
