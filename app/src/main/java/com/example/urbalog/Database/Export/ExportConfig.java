package com.example.urbalog.Database.Export;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.urbalog.Database.FileUtils;
import com.example.urbalog.NetworkHelper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ExportConfig {

    public enum ExportType {
        JSON, CSV
    }

    // Package variables
    SQLiteDatabase db;
    File directory;
    ExportType exportType;

    private String databaseName;
    private Set<String> excludedTables;
    private Context appContext;

    /**
     * Constructor
     * @param db SQLiteDatabase instance
     * @param databaseName Db name
     * @param exportType ExportType (define if file will be export to CSV or JSON)
     * @param appContext Application context
     * @throws IOException
     */
    public ExportConfig(SQLiteDatabase db, String databaseName, ExportType exportType, Context appContext) throws IOException {
        this.db = db;
        this.exportType = exportType;
        this.databaseName = databaseName;
        this.appContext = appContext;

        if( !FileUtils.isExternalStorageWritable() ){
            throw new IOException("Cannot write to external storage");
        }
        this.directory = appContext.getExternalFilesDir("Urbalog/");
    }

    public void setExcludeTable(String tableName) {
        if (excludedTables == null) {
            excludedTables = new HashSet<>();
        }
        excludedTables.add(tableName);
    }

    /**
     * Check if table id excluded
     * @param tableName
     * @return bool
     */
    public boolean isExcludeTable(String tableName) {
        if (excludedTables == null) {
            return false;
        }
        return excludedTables.contains(tableName);
    }

    /**
     * Return corresponding file extension
     * @return String
     */
    public String getFileExtension(){
        switch(exportType){
            case CSV:
                return ".csv";
            case JSON:
                return ".json";
            default:
                return "";
        }
    }

    public String getDatabaseName() {
        return databaseName;
    }

}
