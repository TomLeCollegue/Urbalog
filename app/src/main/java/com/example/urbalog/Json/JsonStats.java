package com.example.urbalog.Json;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class JsonStats {
    private static String fileNameStatistics = "statistics.json";
    private static Context context;

    public static void init(Context mContext){
        context = mContext;
        deleteJson();
        if(fichierExiste())
        {
            Log.d("debug", "le fichier existe deja");
        } else {
            Log.d("debug", "Création du fichier");
            createFile();
        }
    }

    public static void deleteJson()
    {
        Log.d("debug", "delete...");
        File file = context.getFileStreamPath(fileNameStatistics);
        file.delete();
    }

    public static boolean fichierExiste()
    {
        File file = context.getFileStreamPath(fileNameStatistics);
        if(file.exists()){
            return true;
        }
        else{
            return false;
        }
    }

    public static void createFile()
    {
        writeText("{\n" +
                "  \"games\" : [\n" +
                "      {\n" +
                "        \"date\" : \"03/03/2020\",\n" +
                "        \"players\" : [\n" +
                "            {\n" +
                "            \"name\" : \"Laurent\",\n" +
                "            \"age\" : \"23\",\n" +
                "            \"pcs\" : \"étudiant\"\n" +
                "            },\n" +
                "            {\n" +
                "            \"name\" : \"Samuel\",\n" +
                "            \"age\" : \"22\",\n" +
                "            \"pcs\" : \"étudiant\"\n" +
                "            }\n" +
                "          ]\n" +
                "      }\n" +
                "    ]\n" +
                "}");
    }

    public static void writeText(String data){
        FileOutputStream fOut = null;
        OutputStreamWriter osw = null;
        try{
            fOut = context.openFileOutput(fileNameStatistics, Context.MODE_PRIVATE);
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
        File file = context.getFileStreamPath(fileNameStatistics);
        if (!file.exists()){
            return "fichier inexistant";
        }
        else{
            InputStream is = context.openFileInput(fileNameStatistics);
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
}
