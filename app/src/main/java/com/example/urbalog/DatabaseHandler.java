package com.example.urbalog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import com.example.urbalog.Class.Bet;
import com.example.urbalog.Class.Game;
import com.example.urbalog.Class.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Database helper class used for interact with db and table for operation like:
 * - insert
 * - delete
 * - select
 * - create database and tables
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "Urbalog";

    // Constants for games table
    private final String GAME_KEY = "id";
    private final String GAME_NB_PLAYER = "nb_player";
    private final String GAME_NB_BUILDING = "nb_building";
    private final String GAME_CREATED_AT = "created_at";
    private static final String GAME_TABLE_NAME = "games";

    private final String TABLE_GAME_CREATE =
            "CREATE TABLE " + GAME_TABLE_NAME + " (" +
                    GAME_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GAME_NB_PLAYER + " INTEGER, " +
                    GAME_NB_BUILDING + " INTEGER, " +
                    GAME_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
                    ");";

    // Constants for players table
    private final String PLAYER_KEY = "id";
    private final String PLAYER_GAME_ID = "game_id";
    private final String PLAYER_AGE = "age";
    private final String PLAYER_JOB = "job";
    private final String PLAYER_SCORE = "score";
    private final String PLAYER_ROLE = "role";
    private final String PLAYER_CREATED_AT = "created_at";
    private static final String PLAYER_TABLE_NAME = "players";

    private final String TABLE_PLAYER_CREATE =
            "CREATE TABLE " + PLAYER_TABLE_NAME + " (" +
                    PLAYER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PLAYER_GAME_ID + " INTEGER , " +
                    PLAYER_AGE + " INTEGER, " +
                    PLAYER_JOB + " TEXT, " +
                    PLAYER_SCORE + " INTEGER, " +
                    PLAYER_ROLE + " TEXT, " +
                    PLAYER_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(" + PLAYER_GAME_ID + ") REFERENCES " + GAME_TABLE_NAME + "(" + GAME_KEY + ")" +
                    ");";

    // Constants for bet_history table
    private final String BET_KEY = "id";
    private final String BET_GAME_ID = "game_id";
    private final String BET_PLAYER_ID = "player_id";
    private final String BET_MISE_POLITIQUE = "mise_politique";
    private final String BET_MISE_SOCIAL = "mise_social";
    private final String BET_MISE_ECO = "mise_eco";
    private final String BET_BUILDING = "building";
    private final String BET_CREATED_AT = "created_at";
    private static final String BET_TABLE_NAME = "bet_history";


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

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, 1);
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
        v.put(PLAYER_GAME_ID, game.getDbID());
        long res = db.insert(PLAYER_TABLE_NAME, null, v);

        player.setDbID(res);

        return res != -1;
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
    public Cursor getAllData(String table)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + table, null);
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
            String dropQuery = "DROP TABLE IF EXISTS " + table;
            db.execSQL(dropQuery);
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
