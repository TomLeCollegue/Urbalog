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

    public static boolean fichierExiste()
    {
        File file = context.getFileStreamPath(fileNameBuilding);
        if(file.exists()){
            return true;
        }
        else{
            return false;
        }
    }

    public static void deleteJson()
    {
        Log.d("debug", "delete...");
        File file = context.getFileStreamPath(fileNameBuilding);
        file.delete();
    }

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

    public static void addRoles(){
        writeText("{ \n" +
                "  \"roles\": [ \n" +
                "    { \"name\": \"Collectivité Locale\", \"description\": \"L'objectif est atteint quand ces deux conditions sont satisfaites. Le joueur marque alors un point\", \"hold\" : \"Environnement\", \"improve\" : \"Attractivité\", \"ressources\":[ false, true, true ], \"tokenSocial\" : 0, \"tokenEconomical\" : 6, \"tokenPolitical\" : 4 }, \n" +
                "    { \"name\": \"Transporteur\", \"description\": \"Description de l'objet\", \"hold\" : \"Attractivité\", \"improve\" : \"Fluidité\", \"ressources\":[ false, true, true ], \"tokenSocial\" : 0, \"tokenEconomical\" : 7, \"tokenPolitical\" : 3 }, \n" +
                "    { \"name\": \"Habitant\", \"description\": \"L'objectif est atteint quand ces deux conditions sont satisfaites. Le joueur marque alors un point\", \"hold\" : \"Fluidité\", \"improve\" : \"Environnement\", \"ressources\":[ true, false, true ], \"tokenSocial\" : 7, \"tokenEconomical\" : 0, \"tokenPolitical\" : 3 }, \n" +
                "    { \"name\": \"Commerçant\", \"description\": \"Description de l'objet\", \"hold\" : \"Fluidité\", \"improve\" : \"Attractivité\", \"ressources\":[ true, true, false ], \"tokenSocial\" : 6, \"tokenEconomical\" : 4, \"tokenPolitical\" : 0 }, \n" +
                "    { \"name\": \"Opérateur de transport public\", \"description\": \"Description de l'objet\", \"hold\" : \"Environnement\", \"improve\" : \"Fluidité\", \"ressources\":[ false, true, true ], \"tokenSocial\" : 0, \"tokenEconomical\" : 4, \"tokenPolitical\" : 6 }\n" +
                "    ]\n" +
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
                Integer tokenSocial;
                Integer tokenEconomical;
                Integer tokenPolitical;
                JSONArray booleanRessources = jsonRole.getJSONArray("ressources");
                if((Boolean) booleanRessources.get(0) == true){
                    tokenSocial = jsonRole.getInt("tokenSocial");
                } else {
                    tokenSocial = null;
                }
                if((Boolean) booleanRessources.get(1) == true){
                    tokenEconomical = jsonRole.getInt("tokenEconomical");
                } else {
                    tokenEconomical = null;
                }
                if((Boolean) booleanRessources.get(2) == true){
                    tokenPolitical = jsonRole.getInt("tokenPolitical");
                } else {
                    tokenPolitical = null;
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
