package com.example.urbalog.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.urbalog.Class.Bet;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Player;
import com.example.urbalog.Class.Role;
import com.example.urbalog.NetworkHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            "CREATE TABLE " + GAME_TABLE_NAME + " (" +
                    GAME_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GAME_NB_PLAYER + " INTEGER, " +
                    GAME_NB_BUILDING + " INTEGER, " +
                    GAME_SCORE_FLUID + " INTEGER, " +
                    GAME_SCORE_ATTR + " INTEGER, " +
                    GAME_SCORE_ENV + " INTEGER, " +
                    GAME_SCORE_LOG + " INTEGER, " +
                    GAME_NB_TURN + " INTEGER, " +
                    GAME_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
                    ");";

    // Constants for players table
    public static final String PLAYER_KEY = "id";
    public static final String PLAYER_GAME_ID = "game_id";
    public static final String PLAYER_AGE = "age";
    public static final String PLAYER_JOB = "job";
    public static final String PLAYER_SCORE = "score";
    public static final String PLAYER_ROLE = "role";
    public static final String PLAYER_POL = "mise_politique";
    public static final String PLAYER_SOCIAL = "mise_sociale";
    public static final String PLAYER_ECO = "mise_economique";
    public static final String PLAYER_CREATED_AT = "created_at";
    public static final String PLAYER_TABLE_NAME = "players";

    private final String TABLE_PLAYER_CREATE =
            "CREATE TABLE " + PLAYER_TABLE_NAME + " (" +
                    PLAYER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PLAYER_GAME_ID + " INTEGER , " +
                    PLAYER_AGE + " INTEGER, " +
                    PLAYER_JOB + " TEXT, " +
                    PLAYER_SCORE + " INTEGER, " +
                    PLAYER_ROLE + " TEXT, " +
                    PLAYER_POL + " INTEGER, " +
                    PLAYER_SOCIAL + " INTEGER, " +
                    PLAYER_ECO + " INTEGER, " +
                    PLAYER_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(" + PLAYER_GAME_ID + ") REFERENCES " + GAME_TABLE_NAME + "(" + GAME_KEY + ")" +
                    ");";

    // Constants for bet_history table
    public static final String BET_KEY = "id";
    public static final String BET_GAME_ID = "game_id";
    public static final String BET_PLAYER_ID = "player_id";
    public static final String BET_MISE_POLITIQUE = "mise_politique";
    public static final String BET_MISE_SOCIAL = "mise_social";
    public static final String BET_MISE_ECO = "mise_eco";
    public static final String BET_BUILDING = "building";
    public static final String BET_CREATED_AT = "created_at";
    public static final String BET_TABLE_NAME = "bet_history";


    private final String TABLE_BET_CREATE =
            "CREATE TABLE " + BET_TABLE_NAME + " (" +
                    BET_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BET_GAME_ID + " INTEGER , " +
                    BET_PLAYER_ID + " INTEGER, " +
                    BET_MISE_POLITIQUE + " INTEGER, " +
                    BET_MISE_SOCIAL + " INTEGER, " +
                    BET_MISE_ECO + " INTEGER, " +
                    BET_BUILDING + " TEXT, " +
                    BET_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(" + BET_GAME_ID + ") REFERENCES " + GAME_TABLE_NAME + "(" + GAME_KEY + ")," +
                    "FOREIGN KEY(" + BET_PLAYER_ID + ") REFERENCES " + PLAYER_TABLE_NAME + "(" + PLAYER_KEY + ")" +
                    ");";

    private Context appContext;

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, 1);
        this.appContext = context;
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

        v.put(GAME_NB_PLAYER, nbPlayers);
        v.put(GAME_NB_BUILDING, nbBuildings);
        long res = db.insert(GAME_TABLE_NAME, null, v);

        game.setDbID(res);

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

        v.put(PLAYER_AGE, player.getAge());
        v.put(PLAYER_JOB, player.getJob());
        v.put(PLAYER_SCORE, 0);
        v.put(PLAYER_POL, 0);
        v.put(PLAYER_ECO, 0);
        v.put(PLAYER_SOCIAL, 0);
        v.put(PLAYER_ROLE, player.getRole().getTypeRole());
        v.put(PLAYER_GAME_ID, game.getDbID());
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
        v.put(BET_PLAYER_ID, bet.getPlayerID());
        v.put(BET_MISE_POLITIQUE, bet.getMisePolitique());
        v.put(BET_MISE_SOCIAL, bet.getMiseSocial());
        v.put(BET_MISE_ECO, bet.getMiseEco());
        v.put(BET_BUILDING, bet.getNameBuilding());
        long res = db.insert(BET_TABLE_NAME, null, v);

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
            if(table.equals(GAME_TABLE_NAME) || table.equals(PLAYER_TABLE_NAME) || table.equals(BET_TABLE_NAME)) {
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
            SqliteExporter.export(this.getWritableDatabase(), appContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_GAME_CREATE);
        db.execSQL(TABLE_PLAYER_CREATE);
        db.execSQL(TABLE_BET_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int n) {
        dropAllTables();
        onCreate(db);
    }
}
