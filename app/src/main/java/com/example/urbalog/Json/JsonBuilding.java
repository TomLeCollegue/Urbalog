package com.example.urbalog.Json;

import android.content.Context;
import android.util.Log;

import com.example.urbalog.Class.Building;

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

public class JsonBuilding {

    private static String fileNameBuilding = "buildings.json";
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
        if(fichierExiste())
        {
            Log.d("debug", "le fichier existe deja");
        } else {
            Log.d("debug", "Création du fichier");
            addBuildings();
        }
    }

    public static void addBuildings(){
        writeText(" {\"buildings\" : [{\"name\":\"Piste cyclable\",\"description\":\"Voie réservée aux cyclistes et protégée du reste de la circulation\",\"Couts\":{\"politique\":3,\"social\":2,\"economique\":2},\"Effets\":{\"attractivite\":0,\"fluidite\":1,\"environnemental\":1}},{\"name\":\"Borne vélo\",\"description\":\"Borne permettant d'emprunter un vélo en libre service\",\"Couts\":{\"politique\":2,\"social\":2,\"economique\":1},\"Effets\":{\"attractivite\":1,\"fluidite\":-1,\"environnemental\":1}},{\"name\":\"Terasse\",\"description\":\"Terasse de café ou de restaurant\",\"Couts\":{\"politique\":1,\"social\":2,\"economique\":1},\"Effets\":{\"attractivite\":1,\"fluidite\":-2,\"environnemental\":1}},{\"name\":\"Petit magasin\",\"description\":\"Petit commerce (-20 salariés)\",\"Couts\":{\"politique\":1,\"social\":4,\"economique\":2},\"Effets\":{\"attractivite\":2,\"fluidite\":1,\"environnemental\":0}},{\"name\":\"Poste\",\"description\":\"Bureau de poste\",\"Couts\":{\"politique\":1,\"social\":1,\"economique\":2},\"Effets\":{\"attractivite\":0,\"fluidite\":2,\"environnemental\":-1}}]\n" +
                " }");
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

    public static ArrayList<Building> readBuilding(){
        Log.d("debug", "readBuilding...");
        File file = new File(fileNameBuilding);
        String jsonText = null;
        JSONObject jsonRoot = null;
        JSONArray jsonBuildings = null;
        JSONObject jsonBuilding = null;
        JSONObject jsonCouts;
        JSONObject jsonEffets;
        ArrayList<Building> listeBuilding = new ArrayList<>();
        try {
            jsonText = readText();
            jsonRoot = new JSONObject(jsonText);
            jsonBuildings = jsonRoot.getJSONArray("buildings");
            for(int i=0; i<jsonBuildings.length(); i++){
                jsonBuilding = jsonBuildings.getJSONObject(i);
                String name = jsonBuilding.getString("name");
                String description = jsonBuilding.getString("description");
                jsonCouts = jsonBuilding.getJSONObject("Couts");
                Integer coutPolitique= Integer.parseInt(jsonCouts.getString("politique"));
                Integer coutSocial= Integer.parseInt(jsonCouts.getString("social"));
                Integer coutEconomique= Integer.parseInt(jsonCouts.getString("economique"));
                jsonEffets = jsonBuilding.getJSONObject("Effets");
                Integer effetAttractivite = Integer.parseInt(jsonEffets.getString("attractivite"));
                Integer effetFluidite = Integer.parseInt(jsonEffets.getString("fluidite"));
                Integer effetEnvironnemental = Integer.parseInt(jsonEffets.getString("environnemental"));
                Building building = new Building(name, description, coutPolitique, coutSocial, coutEconomique, effetAttractivite, effetFluidite, effetEnvironnemental);
                Log.d("debug", building.toString());
                listeBuilding.add(building);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return listeBuilding;
    }

}
