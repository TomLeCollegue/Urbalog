package com.example.urbalog.Json;

import android.content.Context;
import android.util.Log;

import com.example.urbalog.Class.Role;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class JsonRole {
    private static String fileNameBuilding = "roles.json";
    private static Context context;

    /**
     *Check if JsonRole file exist
     */
    public static boolean fichierExiste()
    {
        File file = context.getFileStreamPath(fileNameBuilding);
        return file.exists();
    }

    /**
     * Delete JsonRole file
     */
    public static void deleteJson()
    {
        Log.d("debug", "delete...");
        File file = context.getFileStreamPath(fileNameBuilding);
        file.delete();
    }


    /**
     * init the JsonRole file
     * If he doesn't exit, create it
     *
     * @param mContext
     */
    public static void init(Context mContext){
        context = mContext;
        deleteJson();
        if(fichierExiste())
        {
            Log.d("debug", "le fichier existe deja");
        } else {
            Log.d("debug", "Création du fichier");
            addRoles();
        }
    }


    /**
     * Add initial roles in file
     */
    public static void addRoles(){
        writeText("{\n" +
                "  \"roles\": [\n" +
                "    {\n" +
                "      \"name\": \"Collectivité Locale\",\n" +
                "      \"description\": \"L'objectif est atteint quand ces deux conditions sont satisfaites. Le joueur marque alors un point\",\n" +
                "      \"hold\": \"Environnement\",\n" +
                "      \"improve\": \"Attractivité\",\n" +
                "      \"ressources\": [\n" +
                "        false,\n" +
                "        true,\n" +
                "        true\n" +
                "      ],\n" +
                "      \"tokenSocial\": 0,\n" +
                "      \"tokenEconomical\": 6,\n" +
                "      \"tokenPolitical\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Transporteur\",\n" +
                "      \"description\": \"Description de l'objet\",\n" +
                "      \"hold\": \"Attractivité\",\n" +
                "      \"improve\": \"Fluidité\",\n" +
                "      \"ressources\": [\n" +
                "        false,\n" +
                "        true,\n" +
                "        true\n" +
                "      ],\n" +
                "      \"tokenSocial\": 0,\n" +
                "      \"tokenEconomical\": 7,\n" +
                "      \"tokenPolitical\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Habitant\",\n" +
                "      \"description\": \"Description de l'objet\",\n" +
                "      \"hold\": \"Fluidité\",\n" +
                "      \"improve\": \"Environnement\",\n" +
                "      \"ressources\": [\n" +
                "        true,\n" +
                "        false,\n" +
                "        true\n" +
                "      ],\n" +
                "      \"tokenSocial\": 7,\n" +
                "      \"tokenEconomical\": 0,\n" +
                "      \"tokenPolitical\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Commerçant\",\n" +
                "      \"description\": \"Description de l'objet\",\n" +
                "      \"hold\": \"Fluidité\",\n" +
                "      \"improve\": \"Attractivité\",\n" +
                "      \"ressources\": [\n" +
                "        true,\n" +
                "        true,\n" +
                "        false\n" +
                "      ],\n" +
                "      \"tokenSocial\": 6,\n" +
                "      \"tokenEconomical\": 4,\n" +
                "      \"tokenPolitical\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Opérateur de transport public\",\n" +
                "      \"description\": \"Description de l'objet\",\n" +
                "      \"hold\": \"Environnement\",\n" +
                "      \"improve\": \"Fluidité\",\n" +
                "      \"ressources\": [\n" +
                "        false,\n" +
                "        true,\n" +
                "        true\n" +
                "      ],\n" +
                "      \"tokenSocial\": 0,\n" +
                "      \"tokenEconomical\": 4,\n" +
                "      \"tokenPolitical\": 6\n" +
                "    }\n" +
                "  ]\n" +
                "}");
    }


    public static void writeText(String data){
        FileOutputStream fOut = null;
        OutputStreamWriter osw = null;
        try{
            fOut = context.openFileOutput(fileNameBuilding, Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(fOut);
            osw.write(data);
            osw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                osw.close();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readText() throws IOException {
        File file = context.getFileStreamPath(fileNameBuilding);
        if (!file.exists()){
            return "fichier inexistant";
        }
        else{
            InputStream is = context.openFileInput(fileNameBuilding);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String s = null;
            while((s = br.readLine()) != null){
                sb.append(s);
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    public static ArrayList<Role> readRole(){
        Log.d("debug", "readRole...");
        File file = new File(fileNameBuilding);
        String jsonText = null;
        JSONObject jsonRoot = null;
        JSONArray jsonRoles = null;
        JSONObject jsonRole = null;
        ArrayList<Role> listeRole = new ArrayList<>();
        try {
            jsonText = readText();
            jsonRoot = new JSONObject(jsonText);
            jsonRoles = jsonRoot.getJSONArray("roles");
            for (int i=0; i<jsonRoles.length(); i++){ 
                jsonRole = jsonRoles.getJSONObject(i);
                String name = jsonRole.getString("name");
                String objective = jsonRole.getString("description");
                String hold = jsonRole.getString("hold");
                String improve = jsonRole.getString("improve");
                int tokenSocial;
                int tokenEconomical;
                int tokenPolitical;
                JSONArray booleanRessources = jsonRole.getJSONArray("ressources");
                if((Boolean) booleanRessources.get(0) == true){
                    tokenSocial = jsonRole.getInt("tokenSocial");
                } else {
                    tokenSocial = 0;
                }
                if((Boolean) booleanRessources.get(1) == true){
                    tokenEconomical = jsonRole.getInt("tokenEconomical");
                } else {
                    tokenEconomical = 0;
                }
                if((Boolean) booleanRessources.get(2) == true){
                    tokenPolitical = jsonRole.getInt("tokenPolitical");
                } else {
                    tokenPolitical = 0;
                }
                boolean[] bool = new boolean[3];
                for(int k=0; k<booleanRessources.length(); k++)
                {
                    bool[k] = booleanRessources.getBoolean(k);
                }
                Role role = new Role(name, bool, tokenSocial, tokenEconomical, tokenPolitical, objective, hold, improve);
                listeRole.add(role);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return listeRole;
    }
}
