package com.example.urbalog.Database.Export;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.urbalog.Database.FileUtils;

public class ExportConfig {

    public enum ExportType {
        JSON, CSV;
    }

    // Package variables
    SQLiteDatabase db;
    File directory;
    ExportType exportType;

    private String databaseName;
    private Set<String> excludedTables;

    public ExportConfig(SQLiteDatabase db, String databaseName, ExportType exportType, Context appContext) throws IOException {
        this.db = db;
        this.exportType = exportType;
        this.databaseName = databaseName;

        if( !FileUtils.isExternalStorageWritable() ){
            throw new IOException("Cannot write to external storage");
        }
        this.directory = FileUtils.createDirIfNotExist(FileUtils.getAppDir(appContext) + "/databases");
    }

    public void setExcludeTable(String tableName) {
        if (excludedTables == null) {
            excludedTables = new HashSet<>();
        }
        excludedTables.add(tableName);
    }

    public boolean isExcludeTable(String tableName) {
        if (excludedTables == null) {
            return false;
        }
        return excludedTables.contains(tableName);
    }

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
