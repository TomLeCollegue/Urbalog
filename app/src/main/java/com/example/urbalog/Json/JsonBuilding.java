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
            deleteJson();
            init(mContext);
        } else {
            Log.d("debug", "Création du fichier");
            addBuildings();
        }
    }

    public static void addBuildings(){
        writeText("{\n" +
                "  \"buildings\": [\n" +
                "    {\n" +
                "      \"name\": \"Piste cyclable\",\n" +
                "      \"description\": \"Voie réservée aux cyclistes et protégée du reste de la circulation\",\n" +
                "      \"Couts\": {\n" +
                "        \"politique\": 3,\n" +
                "        \"social\": 2,\n" +
                "        \"economique\": 2\n" +
                "      },\n" +
                "      \"Effets\": {\n" +
                "        \"attractivite\": 0,\n" +
                "        \"fluidite\": 1,\n" +
                "        \"environnemental\": 1\n" +
                "      },\n" +
                "      \"scoreLogistique\" : 9,\n" +
                "      \"explicationLogistique\": \"test\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Borne vélo\",\n" +
                "      \"description\": \"Borne permettant d'emprunter un vélo en libre service\",\n" +
                "      \"Couts\": {\n" +
                "        \"politique\": 2,\n" +
                "        \"social\": 2,\n" +
                "        \"economique\": 1\n" +
                "      },\n" +
                "      \"Effets\": {\n" +
                "        \"attractivite\": 1,\n" +
                "        \"fluidite\": -1,\n" +
                "        \"environnemental\": 1\n" +
                "      },\n" +
                "      \"scoreLogistique\" : 9,\n" +
                "      \"explicationLogistique\": \"test\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Terasse\",\n" +
                "      \"description\": \"Terasse de café ou de restaurant\",\n" +
                "      \"Couts\": {\n" +
                "        \"politique\": 1,\n" +
                "        \"social\": 2,\n" +
                "        \"economique\": 1\n" +
                "      },\n" +
                "      \"Effets\": {\n" +
                "        \"attractivite\": 1,\n" +
                "        \"fluidite\": -2,\n" +
                "        \"environnemental\": 1\n" +
                "      },\n" +
                "      \"scoreLogistique\" : 9,\n" +
                "      \"explicationLogistique\": \"test\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Petit magasin\",\n" +
                "      \"description\": \"Petit commerce (-20 salariés)\",\n" +
                "      \"Couts\": {\n" +
                "        \"politique\": 1,\n" +
                "        \"social\": 4,\n" +
                "        \"economique\": 2\n" +
                "      },\n" +
                "      \"Effets\": {\n" +
                "        \"attractivite\": 2,\n" +
                "        \"fluidite\": 1,\n" +
                "        \"environnemental\": 0\n" +
                "      },\n" +
                "      \"scoreLogistique\" : 9,\n" +
                "      \"explicationLogistique\": \"test\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Poste\",\n" +
                "      \"description\": \"Bureau de poste\",\n" +
                "      \"Couts\": {\n" +
                "        \"politique\": 1,\n" +
                "        \"social\": 1,\n" +
                "        \"economique\": 2\n" +
                "      },\n" +
                "      \"Effets\": {\n" +
                "        \"attractivite\": 0,\n" +
                "        \"fluidite\": 2,\n" +
                "        \"environnemental\": -1\n" +
                "      },\n" +
                "      \"scoreLogistique\" : 9,\n" +
                "      \"explicationLogistique\": \"test\"\n" +
                "    }\n" +
                "  ]\n" +
                "}");
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
                Integer scoreLogistique = jsonBuilding.getInt("scoreLogistique");
                String explicationLogistique = jsonBuilding.getString("explicationLogistique");
                Building building = new Building(name, description, coutPolitique, coutSocial, coutEconomique, effetAttractivite, effetFluidite, effetEnvironnemental, scoreLogistique, explicationLogistique);
                listeBuilding.add(building);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return listeBuilding;
    }

    public static void modificationBuilding(Building building, String name){
        Log.d("debug", "modificationBuilding...");
        removeBuilding(name);
        writeBuilding(building);
    }

    public static void writeBuilding(Building building){
        Log.d("debug", "writeBuilding...");
        String jsonText = null;
        JSONObject jsonRoot = null;
        JSONArray jsonBuildings = null;
        try {
            JSONObject object = new JSONObject();
            object.put("name", building.getName());
            object.put("description", building.getDescription());
            JSONObject couts = new JSONObject();
            couts.put("politique", building.getCoutPolitique());
            couts.put("social", building.getCoutSocial());
            couts.put("economique", building.getCoutEconomique());
            object.put("Couts", couts);
            JSONObject effets = new JSONObject();
            effets.put("attractivite", building.getEffetAttractivite());
            effets.put("fluidite", building.getEffetFluidite());
            effets.put("environnemental", building.getEffetEnvironnemental());
            object.put("Effets", effets);

            object.put("scoreLogistique", building.getScoreLogistique());
            object.put("explicationLogistique", building.getExplicationLogistique());

            jsonText = readText();
            jsonRoot = new JSONObject(jsonText);
            jsonBuildings = jsonRoot.getJSONArray("buildings");
            jsonBuildings.put(object);
            Log.d("debug", jsonBuildings.toString());
            writeText(jsonRoot.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void removeBuilding(String name){
        Log.d("debug", "removeBuilding...");
        String jsonText = null;
        JSONObject jsonRoot = null;
        JSONArray jsonBuildings = null;
        JSONObject jsonBuilding = null;
        try {
            jsonText = readText();
            jsonRoot = new JSONObject(jsonText);
            jsonBuildings = jsonRoot.getJSONArray("buildings");
            for(int i=0; i<jsonBuildings.length(); i++){
                jsonBuilding = jsonBuildings.getJSONObject(i);
                if((jsonBuilding.getString("name").replaceAll("([A-Z])", "$1").toLowerCase()).equals(name.replaceAll("([A-Z])", "$1").toLowerCase())){
                    Log.d("debug", "==> " + i);
                    jsonBuildings.remove(i);
                }
            }
            writeText(jsonRoot.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean buildingAlreadyInList(String name){
        Log.d("debug", "buildingAlreadyInList...");
        String jsonText = null;
        JSONObject jsonRoot = null;
        JSONArray jsonBuildings = null;
        JSONObject jsonBuilding = null;
        try {
            jsonText = readText();
            jsonRoot = new JSONObject(jsonText);
            jsonBuildings = jsonRoot.getJSONArray("buildings");
            for(int i=0; i<jsonBuildings.length(); i++){
                jsonBuilding = jsonBuildings.getJSONObject(i);

                if((jsonBuilding.getString("name").replaceAll("([A-Z])", "$1").toLowerCase()).equals(name.replaceAll("([A-Z])", "$1").toLowerCase()) || (jsonBuilding.getString("name").replaceAll("([A-Z])", "$1").toLowerCase()).equals(name.replaceAll("([A-Z])", "$1").toLowerCase())){
                    return true;
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
