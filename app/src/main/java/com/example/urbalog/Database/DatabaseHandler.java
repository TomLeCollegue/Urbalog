package com.example.urbalog.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.example.urbalog.Class.Bet;
import com.example.urbalog.Class.Building;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Market;
import com.example.urbalog.Class.Player;
import com.example.urbalog.Class.Role;
import com.example.urbalog.Database.Export.DBExporterCsv;
import com.example.urbalog.Database.Export.DBExporterJson;
import com.example.urbalog.Database.Export.ExportConfig;
import com.example.urbalog.Database.Export.SqliteExporter;
import com.example.urbalog.Database.Http.FileUploadService;
import com.example.urbalog.Database.Http.ServiceGenerator;
import com.example.urbalog.NetworkHelper;
import com.example.urbalog.UUIDHelper;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Database helper class used for interact with db and table for operation like:
 * - insert
 * - delete
 * - select
 * - update
 * - create database and tables
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "Urbalog";

    // Constants for games table
    public static final String GAME_KEY = "id";
    public static final String GAME_GENERATED_KEY = "game_key";
    public static final String GAME_NB_PLAYER = "nb_player";
    public static final String GAME_NB_BUILDING = "nb_building";
    public static final String GAME_SCORE_FLUID = "score_fluidité";
    public static final String GAME_SCORE_ATTR = "score_attractivité";
    public static final String GAME_SCORE_ENV = "score_environmental";
    public static final String GAME_SCORE_LOG = "score_logistique";
    public static final String GAME_NB_TURN = "nb_turn";
    public static final String GAME_CREATED_AT = "created_at";
    public static final String GAME_TABLE_NAME = "games";

    private final String TABLE_GAME_CREATE =
            "CREATE TABLE IF NOT EXISTS " + GAME_TABLE_NAME + " (" +
                    GAME_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GAME_GENERATED_KEY + " TEXT, " +
                    GAME_NB_PLAYER + " INTEGER, " +
                    GAME_NB_BUILDING + " INTEGER, " +
                    GAME_SCORE_FLUID + " INTEGER, " +
                    GAME_SCORE_ATTR + " INTEGER, " +
                    GAME_SCORE_ENV + " INTEGER, " +
                    GAME_SCORE_LOG + " INTEGER, " +
                    GAME_NB_TURN + " INTEGER, " +
                    GAME_CREATED_AT + " DATETIME DEFAULT (datetime('now', 'localtime'))" +
                    ");";

    // Constants for players table
    public static final String PLAYER_KEY = "id";
    public static final String PLAYER_GAME_ID = "game_id";
    public static final String PLAYER_GAME_KEY = "game_key";
    public static final String PLAYER_NAME = "nom";
    public static final String PLAYER_FIRSTNAME = "prénom";
    public static final String PLAYER_SEXE = "sexe";
    public static final String PLAYER_AGE = "age";
    public static final String PLAYER_HOME = "residence";
    public static final String PLAYER_ACTIVITY_STATUS = "statut_activité";
    public static final String PLAYER_JOB = "job";
    public static final String PLAYER_JOB_DOMAIN = "secteur_activité";
    public static final String PLAYER_CORP = "entreprise";
    public static final String PLAYER_POL = "mise_politique";
    public static final String PLAYER_SOCIAL = "mise_sociale";
    public static final String PLAYER_ECO = "mise_economique";
    public static final String PLAYER_SCORE = "score";
    public static final String PLAYER_ROLE = "role";
    public static final String PLAYER_CREATED_AT = "created_at";
    public static final String PLAYER_TABLE_NAME = "players";

    private final String TABLE_PLAYER_CREATE =
            "CREATE TABLE IF NOT EXISTS " + PLAYER_TABLE_NAME + " (" +
                    PLAYER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PLAYER_GAME_ID + " INTEGER , " +
                    PLAYER_GAME_KEY + " TEXT , " +
                    PLAYER_NAME + " TEXT , " +
                    PLAYER_FIRSTNAME + " TEXT , " +
                    PLAYER_SEXE + " TEXT , " +
                    PLAYER_AGE + " TEXT, " +
                    PLAYER_HOME + " TEXT, " +
                    PLAYER_ACTIVITY_STATUS + " TEXT, " +
                    PLAYER_JOB + " TEXT, " +
                    PLAYER_JOB_DOMAIN + " TEXT, " +
                    PLAYER_CORP + " TEXT, " +
                    PLAYER_POL + " INTEGER, " +
                    PLAYER_SOCIAL + " INTEGER, " +
                    PLAYER_ECO + " INTEGER, " +
                    PLAYER_SCORE + " INTEGER, " +
                    PLAYER_ROLE + " TEXT, " +
                    PLAYER_CREATED_AT + " DATETIME DEFAULT (datetime('now', 'localtime')), " +
                    "FOREIGN KEY(" + PLAYER_GAME_ID + ") REFERENCES " + GAME_TABLE_NAME + "(" + GAME_KEY + ")" +
                    ");";

    // Constants for bet_history table
    public static final String BET_KEY = "id";
    public static final String BET_GAME_ID = "game_id";
    public static final String BET_GAME_KEY = "game_key";
    public static final String BET_PLAYER_ID = "player_id";
    public static final String BET_MISE_POLITIQUE = "mise_politique";
    public static final String BET_MISE_SOCIAL = "mise_social";
    public static final String BET_MISE_ECO = "mise_eco";
    public static final String BET_BUILDING = "building";
    public static final String BET_TURN = "turn";
    public static final String BET_CREATED_AT = "created_at";
    public static final String BET_TABLE_NAME = "bet_history";


    private final String TABLE_BET_CREATE =
            "CREATE TABLE IF NOT EXISTS " + BET_TABLE_NAME + " (" +
                    BET_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BET_GAME_ID + " INTEGER , " +
                    BET_GAME_KEY + " TEXT , " +
                    BET_PLAYER_ID + " INTEGER, " +
                    BET_MISE_POLITIQUE + " INTEGER, " +
                    BET_MISE_SOCIAL + " INTEGER, " +
                    BET_MISE_ECO + " INTEGER, " +
                    BET_TURN + " INTEGER, " +
                    BET_BUILDING + " TEXT, " +
                    BET_CREATED_AT + " DATETIME DEFAULT (datetime('now', 'localtime')), " +
                    "FOREIGN KEY(" + BET_GAME_ID + ") REFERENCES " + GAME_TABLE_NAME + "(" + GAME_KEY + ")," +
                    "FOREIGN KEY(" + BET_PLAYER_ID + ") REFERENCES " + PLAYER_TABLE_NAME + "(" + PLAYER_KEY + ")" +
                    ");";

    // Constants for turn_history table
    public static final String TURN_KEY = "id";
    public static final String TURN_GAME_ID = "game_id";
    public static final String TURN_GAME_KEY = "game_key";
    public static final String TURN_NUMBER = "turn_number";
    public static final String TURN_MARKET1 = "building_market_1";
    public static final String TURN_MARKET2 = "building_market_2";
    public static final String TURN_MARKET3 = "building_market_3";
    public static final String TURN_MARKET4 = "building_market_4";
    public static final String TURN_MARKET5 = "building_market_5";
    public static final String TURN_BUILDED1 = "building_completed_1";
    public static final String TURN_BUILDED2 = "building_completed_2";
    public static final String TURN_BUILDED3 = "building_completed_3";
    public static final String TURN_BUILDED4 = "building_completed_4";
    public static final String TURN_BUILDED5 = "building_completed_5";
    public static final String TURN_CREATED_AT = "created_at";
    public static final String TURN_TABLE_NAME = "turn_history";


    private final String TABLE_TURN_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TURN_TABLE_NAME + " (" +
                    TURN_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TURN_GAME_ID + " INTEGER , " +
                    TURN_GAME_KEY + " TEXT , " +
                    TURN_NUMBER + " INTEGER, " +
                    TURN_MARKET1 + " TEXT, " +
                    TURN_MARKET2 + " TEXT, " +
                    TURN_MARKET3 + " TEXT, " +
                    TURN_MARKET4 + " TEXT, " +
                    TURN_MARKET5 + " TEXT, " +
                    TURN_BUILDED1 + " TEXT, " +
                    TURN_BUILDED2 + " TEXT, " +
                    TURN_BUILDED3 + " TEXT, " +
                    TURN_BUILDED4 + " TEXT, " +
                    TURN_BUILDED5 + " TEXT, " +
                    TURN_CREATED_AT + " DATETIME DEFAULT (datetime('now', 'localtime')), " +
                    "FOREIGN KEY(" + TURN_GAME_ID + ") REFERENCES " + GAME_TABLE_NAME + "(" + GAME_KEY + ")" +
                    ");";

    // Constants for buildings table
    public static final String BUILDING_KEY = "id";
    public static final String BUILDING_GAME_ID = "game_id";
    public static final String BUILDING_GAME_KEY = "game_key";
    public static final String BUILDING_NAME = "name";
    public static final String BUILDING_DESCRIPTION = "description";
    public static final String BUILDING_POLITIC_COST = "political_cost";
    public static final String BUILDING_SOCIAL_COST = "social_cost";
    public static final String BUILDING_ECO_COST = "economic_cost";
    public static final String BUILDING_ATTR_BUFF = "attractiveness_score";
    public static final String BUILDING_FLUID_BUFF = "fluency_score";
    public static final String BUILDING_ENV_BUFF = "environmental_score";
    public static final String BUILDING_LOG_BUFF = "logistic_score";
    public static final String BUILDING_LOG_CONTEXT = "logistic_description";
    public static final String BUILDING_CREATED_AT = "created_at";
    public static final String BUILDING_TABLE_NAME = "buildings";


    private final String TABLE_BUILDINGS_CREATE =
            "CREATE TABLE IF NOT EXISTS " + BUILDING_TABLE_NAME + " (" +
                    BUILDING_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BUILDING_GAME_ID + " INTEGER , " +
                    BUILDING_GAME_KEY + " TEXT , " +
                    BUILDING_NAME + " TEXT, " +
                    BUILDING_DESCRIPTION + " TEXT, " +
                    BUILDING_POLITIC_COST + " INTEGER, " +
                    BUILDING_SOCIAL_COST + " INTEGER, " +
                    BUILDING_ECO_COST + " INTEGER, " +
                    BUILDING_ATTR_BUFF + " INTEGER, " +
                    BUILDING_FLUID_BUFF + " INTEGER, " +
                    BUILDING_ENV_BUFF + " INTEGER, " +
                    BUILDING_LOG_BUFF + " INTEGER, " +
                    BUILDING_LOG_CONTEXT + " TEXT, " +
                    BUILDING_CREATED_AT + " DATETIME DEFAULT (datetime('now', 'localtime')), " +
                    "FOREIGN KEY(" + BUILDING_GAME_ID + ") REFERENCES " + GAME_TABLE_NAME + "(" + GAME_KEY + ")" +
                    ");";

    private Context appContext;

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, 1);
        this.appContext = context;
    }

    /**
     * Insert initial data in db (buildings/roles pools & game settings)
     *
     * @param game Game Instance
     * @param nbPlayers Number of players of current game
     * @param nbBuildings Number of buildings required for current game
     * @return bool
     */
    public boolean insertInitialGameData(Game game, ArrayList<Role> roles, int nbPlayers, int nbBuildings)
    {
        return insertGame(game, nbPlayers, nbBuildings) && insertBuildings(game);
    }

    /**
     * Insert buildings pool data for a game in db
     *
     * @param game Game Instance
     * @return bool
     */
    public boolean insertBuildings(Game game)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues v = new ContentValues();
            for (Building building : game.getMarket().getDeck()) {
                v.put(BUILDING_GAME_ID, game.getDbID());
                v.put(BUILDING_GAME_KEY, game.getDbKEY());
                v.put(BUILDING_NAME, building.getName());
                v.put(BUILDING_DESCRIPTION, building.getDescription());
                v.put(BUILDING_POLITIC_COST, building.getCoutPolitique());
                v.put(BUILDING_SOCIAL_COST, building.getCoutSocial());
                v.put(BUILDING_ECO_COST, building.getCoutEconomique());
                v.put(BUILDING_ATTR_BUFF, building.getEffetAttractivite());
                v.put(BUILDING_FLUID_BUFF, building.getEffetFluidite());
                v.put(BUILDING_ENV_BUFF, building.getEffetEnvironnemental());
                v.put(BUILDING_LOG_BUFF, building.getScoreLogistique());
                v.put(BUILDING_LOG_CONTEXT, building.getExplicationLogistique());
                db.insert(BUILDING_TABLE_NAME, null, v);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return true;
    }

    /**
     * Insert Game instance data in db
     *
     * @param game Game Instance
     * @param nbPlayers Number of players of current game
     * @param nbBuildings Number of buildings required for current game
     * @return bool
     */
    public boolean insertGame(Game game, int nbPlayers, int nbBuildings)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();

        String key = UUIDHelper.randomUUID(15, 0, ' ');
        Log.d(NetworkHelper.TAG, "Game key :"+key);

        v.put(GAME_GENERATED_KEY, key);
        v.put(GAME_NB_PLAYER, nbPlayers);
        v.put(GAME_NB_BUILDING, nbBuildings);
        long res = db.insert(GAME_TABLE_NAME, null, v);

        game.setDbID(res);
        game.setDbKEY(key);

        return res != -1;
    }

    /**
     * Update Game data in db with final game values (score, nb turn, ect...)
     *
     * @param game Game Instance
     * @return bool
     */
    public boolean updateGame(Game game)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();

        v.put(GAME_SCORE_FLUID, game.getScoreFluidite());
        v.put(GAME_SCORE_ENV, game.getScoreEnvironnemental());
        v.put(GAME_SCORE_ATTR, game.getScoreAttractivite());
        v.put(GAME_SCORE_LOG, game.getScoreLogistique());
        v.put(GAME_NB_TURN, game.getnTurn());
        String[] args = {String.valueOf(game.getDbID())};
        int res = db.update(GAME_TABLE_NAME, v, GAME_KEY + " = ?", args);

        return res > 0;
    }

    /**
     * Insert Player instance data in db
     *
     * @param game Game instance
     * @param player Player instance
     * @return bool
     */
    public boolean insertPlayer(Game game, Player player)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();

        v.put(PLAYER_NAME, player.getName());
        v.put(PLAYER_FIRSTNAME, player.getFirstName());
        v.put(PLAYER_SEXE, player.getSexe());
        v.put(PLAYER_AGE, player.getAge());
        v.put(PLAYER_HOME, player.getResidence());
        v.put(PLAYER_ACTIVITY_STATUS, player.getStatutActivite());
        v.put(PLAYER_JOB, player.getProfession());
        v.put(PLAYER_JOB_DOMAIN, player.getSecteurActivite());
        v.put(PLAYER_CORP, player.getEntreprise());
        v.put(PLAYER_SCORE, 0);
        v.put(PLAYER_POL, 0);
        v.put(PLAYER_ECO, 0);
        v.put(PLAYER_SOCIAL, 0);
        v.put(PLAYER_ROLE, player.getRole().getTypeRole());
        v.put(PLAYER_GAME_ID, game.getDbID());
        v.put(PLAYER_GAME_KEY, game.getDbKEY());
        long res = db.insert(PLAYER_TABLE_NAME, null, v);

        player.setDbID(res);

        return res != -1;
    }

    /**
     * Update Player data in db with final game values (score)
     *
     * @param player Player Instance
     * @return bool
     */
    public boolean updatePlayer(Player player)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        Cursor mCursor;
        int eco = 0, social = 0, pol = 0;

        mCursor = getAllDataFromTable(PLAYER_TABLE_NAME, "id=" + player.getDbID());
        mCursor.moveToFirst();
        eco = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(PLAYER_ECO)));
        social = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(PLAYER_SOCIAL)));
        pol = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(PLAYER_POL)));
        mCursor.close();

        Role roleInfo = player.getRole();
        if (!roleInfo.getBooleanRessource()[0]){
            for (int i = 0; i < 5; i++) {
                eco += player.getFinancementRessource()[i][0];
                pol += player.getFinancementRessource()[i][1];
            }
            social = -1;
        }
        else if (!roleInfo.getBooleanRessource()[1]){
            for (int i = 0; i < 5; i++) {
                social += player.getFinancementRessource()[i][0];
                pol += player.getFinancementRessource()[i][1];
            }
            eco = -1;
        }
        else if (!roleInfo.getBooleanRessource()[2]){
            for (int i = 0; i < 5; i++) {
                social += player.getFinancementRessource()[i][0];
                eco += player.getFinancementRessource()[i][1];
            }
            pol = -1;
        }

        v.put(PLAYER_SCORE, player.getScore());
        v.put(PLAYER_POL, pol);
        v.put(PLAYER_ECO, eco);
        v.put(PLAYER_SOCIAL, social);
        String[] args = {String.valueOf(player.getDbID())};
        int res = db.update(PLAYER_TABLE_NAME, v, PLAYER_KEY + " = ?", args);

        return res > 0;
    }

    /**
     * Insert Bet instance data in db
     *
     * @param bet Bet instance
     * @param game Game instance
     * @return bool
     */
    public boolean insertBet(Bet bet, Game game)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();

        v.put(BET_GAME_ID, game.getDbID());
        v.put(BET_GAME_KEY, game.getDbKEY());
        v.put(BET_PLAYER_ID, bet.getPlayerID());
        v.put(BET_MISE_POLITIQUE, bet.getMisePolitique());
        v.put(BET_MISE_SOCIAL, bet.getMiseSocial());
        v.put(BET_MISE_ECO, bet.getMiseEco());
        v.put(BET_TURN, game.getnTurn());
        v.put(BET_BUILDING, bet.getNameBuilding());
        long res = db.insert(BET_TABLE_NAME, null, v);

        return res != -1;
    }

    /**
     * Insert turn data in db (Market state and builded buildings per turn)
     *
     * @param game Game Instance
     * @param newBuildings Builded building list
     * @return bool
     */
    public boolean insertTurn(Game game, ArrayList<Building> newBuildings)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();

        v.put(TURN_GAME_ID, game.getDbID());
        v.put(TURN_GAME_KEY, game.getDbKEY());
        v.put(TURN_NUMBER, game.getnTurn());
        v.put(TURN_MARKET1, game.getMarket().getBuildings().get(0).getName());
        v.put(TURN_MARKET2, game.getMarket().getBuildings().get(1).getName());
        v.put(TURN_MARKET3, game.getMarket().getBuildings().get(2).getName());
        v.put(TURN_MARKET4, game.getMarket().getBuildings().get(3).getName());
        v.put(TURN_MARKET5, game.getMarket().getBuildings().get(4).getName());

        for (int i = 0; i < 5; i++) {
            if(i < newBuildings.size()){
                switch (i){
                    case 0:
                        v.put(TURN_BUILDED1, newBuildings.get(i).getName());
                        break;
                    case 1:
                        v.put(TURN_BUILDED2, newBuildings.get(i).getName());
                        break;
                    case 2:
                        v.put(TURN_BUILDED3, newBuildings.get(i).getName());
                        break;
                    case 3:
                        v.put(TURN_BUILDED4, newBuildings.get(i).getName());
                        break;
                    case 4:
                        v.put(TURN_BUILDED5, newBuildings.get(i).getName());
                        break;
                    default:
                        break;
                }
            }
        }

        long res = db.insert(TURN_TABLE_NAME, null, v);
        return res != -1;
    }

    /**
     * Delete entry from db
     * Required table name and entry id
     *
     * @param id Entry id
     * @param table Table name
     * @return bool
     */
    public boolean deleteData(String id, String table){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(table,"id=?",new String[]{id});
        return res != -1;
    }

    /**
     * Return Cursor pointing on first element of desired table
     *
     * @param table Table name
     * @return Cursor
     */
    public Cursor getAllDataFromTable(String table)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + table, null);
    }

    /**
     * Return Cursor pointing on first element of desired table with where
     *
     * @param table Table name
     * @return Cursor
     */
    public Cursor getAllDataFromTable(String table, String where)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + table + " WHERE " + where, null);
    }

    /**
     * Return Cursor pointing on first element of desired data of table
     *
     * @param table Table name
     * @param data Data name
     * @return Cursor
     */
    public Cursor getOneDataFromTable(String table, String data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT " + data + " FROM " + table, null);
    }

    /**
     * Return Cursor pointing on first element of desired data of table with where parameter
     *
     * @param table Table name
     * @param data Data name
     * @param where Where constraint
     * @return Cursor
     */
    public Cursor getOneDataFromTable(String table, String data, String where)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT " + data + " FROM " + table + " WHERE " + where, null);
    }

    /**
     * Return Cursor pointing on first element of desired data of table
     *
     * @param table Table name
     * @param data Data name
     * @return Cursor
     */
    public Cursor getMultipleDataFromTable(String table, String[] data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String catData = data[0];
        for (int i = 1; i < data.length; i++) {
            catData = catData.concat(", "+data[i]);
        }
        Log.d(NetworkHelper.TAG, "SELECT " + catData + " FROM " + table);
        return db.rawQuery("SELECT " + catData + " FROM " + table, null);
    }

    /**
     * Drop all table of the db
     */
    private void dropAllTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        List<String> tables = new ArrayList<>();

        while (c.moveToNext()) {
            tables.add(c.getString(0));
        }
        c.close();

        for (String table : tables) {
            if(table.equals(GAME_TABLE_NAME) || table.equals(PLAYER_TABLE_NAME)
                    || table.equals(BET_TABLE_NAME) || table.equals(TURN_TABLE_NAME)
                    || table.equals(BUILDING_TABLE_NAME)) {
                String dropQuery = "DROP TABLE IF EXISTS " + table;
                db.execSQL(dropQuery);
            }
        }
    }

    /**
     * Debug function for reset database
     * Drop all tables and re-create them
     */
    public void resetDB()
    {
        dropAllTables();
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
    }

    public String getDBName()
    {
        return DB_NAME;
    }

    /**
     * Return the number of elements in the table
     *
     * @param table Table name
     * @return Number of entry of table
     */
    public int getNumEntries(String table)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return (int)DatabaseUtils.queryNumEntries(db, table);
    }

    /**
     * Check if db is empty
     *
     * @param table Table name
     * @return boolean
     */
    public boolean isEmpty(String table)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + table, null);
        boolean empty;

        empty = !mCursor.moveToFirst();
        mCursor.close();

        return empty;
    }

    public void exportDbToCSV(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ExportConfig config = new ExportConfig(db, DB_NAME, ExportConfig.ExportType.CSV, appContext);
            DBExporterCsv exporter = new DBExporterCsv(config);
            exporter.export();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportDbToJSON(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ExportConfig config = new ExportConfig(db, DB_NAME, ExportConfig.ExportType.JSON, appContext);
            DBExporterJson exporter = new DBExporterJson(config);
            exporter.export();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void syncDb(){
        FileUploadService service =
                ServiceGenerator.createService(FileUploadService.class);

        File dbJsonPath = new File(FileUtils.getAppDir(appContext) + "/databases/Urbalog.json");
        Log.d(NetworkHelper.TAG, dbJsonPath.getAbsolutePath());

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(Uri.fromFile(dbJsonPath).toString()),
                        dbJsonPath
                );

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("value", dbJsonPath.getName(), requestFile);

        String titleString = "Urbalog app sync";
        RequestBody title =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, titleString);

        Call<ResponseBody> call = service.upload(title, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_GAME_CREATE);
        db.execSQL(TABLE_PLAYER_CREATE);
        db.execSQL(TABLE_BET_CREATE);
        db.execSQL(TABLE_TURN_CREATE);
        db.execSQL(TABLE_BUILDINGS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int n) {
        dropAllTables();
        onCreate(db);
    }
}
