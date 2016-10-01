package com.zoogaru.android.jpconnect.entity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sandeep on 7/16/16.
 */
public class QuikcSQLite extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Quikc.DB";

    public static final String TABLE_QUIKC_MESSAGES = "messages";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CHANNEL_KEY = "channel_key";
    public static final String COLUMN_MSG_KEY = "msg_key";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_SUMMERY = "summery";

    public static final String COLUMN_MSG_TIME_STAMP = "created_at";
    public static final String COLUMN_IS_REPLIED = "is_replied";

    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_QUIKC_CHANNELS = "channels";
    public static final String COLUMN_CHANNEL_TITLE = "title";
    public static final String COLUMN_CHANNEL_LISTENING = "listening";

    private final String QUIKC_CHANNLE_TABLE_CREATE = "CREATE TABLE "
            + TABLE_QUIKC_CHANNELS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CHANNEL_TITLE + " TEXT, " +
            COLUMN_CHANNEL_KEY + " TEXT, " + COLUMN_CHANNEL_LISTENING + " INTEGER);";

    // Database creation sql statement
    private final String QUIKC_TABLE_CREATE = "CREATE TABLE "
            + TABLE_QUIKC_MESSAGES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MSG_KEY + " TEXT, " +
            COLUMN_CHANNEL_KEY + " TEXT, " + COLUMN_TITLE + " TEXT, " + COLUMN_SUMMERY + " TEXT, " +
            COLUMN_IS_REPLIED + " INTEGER, " + COLUMN_MSG_TIME_STAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    public QuikcSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(QUIKC_TABLE_CREATE);
        database.execSQL(QUIKC_CHANNLE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(QuikcSQLite.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIKC_MESSAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIKC_CHANNELS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(QuikcSQLite.class.getName(), "Downgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIKC_MESSAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIKC_CHANNELS);
        onCreate(db);
    }
}