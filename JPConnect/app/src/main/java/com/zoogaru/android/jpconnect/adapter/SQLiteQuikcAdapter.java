package com.zoogaru.android.jpconnect.adapter;

/**
 * Created by sandeep on 7/20/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zoogaru.android.jpconnect.entity.Channel;
import com.zoogaru.android.jpconnect.entity.QuikcSQLite;

import java.util.ArrayList;
import java.util.List;

public class SQLiteQuikcAdapter {
    // Database fields
    private SQLiteDatabase database;
    private QuikcSQLite dbHelper;
    private String[] quikcMessageColumns = {QuikcSQLite.COLUMN_ID, QuikcSQLite.COLUMN_CHANNEL_KEY, QuikcSQLite.COLUMN_MSG_KEY, QuikcSQLite.COLUMN_TITLE, QuikcSQLite.COLUMN_SUMMERY, QuikcSQLite.COLUMN_IS_REPLIED, QuikcSQLite.COLUMN_MSG_TIME_STAMP};

    private String[] quikcChannelColumns = {QuikcSQLite.COLUMN_ID, QuikcSQLite.COLUMN_CHANNEL_KEY, QuikcSQLite.COLUMN_CHANNEL_TITLE, QuikcSQLite.COLUMN_CHANNEL_LISTENING};

    public SQLiteQuikcAdapter(Context context) {
        dbHelper = new QuikcSQLite(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertToSqlite(Channel aChannel) {
        open();
        ContentValues values = new ContentValues();
        values.put(QuikcSQLite.COLUMN_CHANNEL_KEY, aChannel.m_key);
        values.put(QuikcSQLite.COLUMN_CHANNEL_TITLE, aChannel.m_title);
        values.put(QuikcSQLite.COLUMN_CHANNEL_LISTENING, aChannel.m_isListening);

        long insertId = database.insert(QuikcSQLite.TABLE_QUIKC_CHANNELS, null, values);
        Log.d("DBOperation", "inserted channel id: " + insertId);
    }

    public boolean isChannelListening(String aChannel) {
        boolean registered = true;
        List<Channel> channels = getAllChannels();
        for (Channel c: channels) {
            String s = c.getTitle();
            if(!s.isEmpty()) {
                if(s.equals(aChannel))
                {
                    registered = c.isListening();
                    break;
                }
            }
        }
        return registered;
    }

    public List<Channel> getAllChannels() {
        open();
        List<Channel> channels = new ArrayList<Channel>();

        Cursor cursor = database.query(QuikcSQLite.TABLE_QUIKC_CHANNELS, quikcChannelColumns, null, null, null, null, QuikcSQLite.COLUMN_CHANNEL_TITLE);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            channels.add(cursorToChannel(cursor));
            cursor.moveToNext();
        }
// make sure to close the cursor
        cursor.close();
        return channels;
    }

    public Channel getChannel(String key) {
        open();
        Channel channel = null;
        Log.d("DBOperation", "fetching channel for key: " + key);

        String whereClause = QuikcSQLite.COLUMN_CHANNEL_KEY + "=?";
        String[] whereArgs = new String[]{
                key
        };

        Cursor cursor = database.query(QuikcSQLite.TABLE_QUIKC_CHANNELS, quikcChannelColumns, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0)
        {
            channel = cursorToChannel(cursor);
        }
        cursor.close();
        return channel;
    }

    public Channel updateChannel(Channel aChannel) {
        open();
        Channel channel = null;
        Log.d("DBOperation", "updating channel for id: " + aChannel.getId());

        String whereClause = QuikcSQLite.COLUMN_ID + "=?";
        String[] whereArgs = new String[]{
                aChannel.getId()
        };

        ContentValues values = new ContentValues();
        values.put(QuikcSQLite.COLUMN_ID, aChannel.m_id);
        values.put(QuikcSQLite.COLUMN_CHANNEL_KEY, aChannel.m_key);
        values.put(QuikcSQLite.COLUMN_CHANNEL_TITLE, aChannel.m_title);
        values.put(QuikcSQLite.COLUMN_CHANNEL_LISTENING, aChannel.m_isListening);

        int row = database.update(QuikcSQLite.TABLE_QUIKC_CHANNELS, values, whereClause, whereArgs);
        return channel;
    }

    private Channel cursorToChannel(Cursor cursor) {
        Channel channel = new Channel();

        channel.m_id = cursor.getString(cursor.getColumnIndex(QuikcSQLite.COLUMN_ID));
        channel.m_key = cursor.getString(cursor.getColumnIndex(QuikcSQLite.COLUMN_CHANNEL_KEY));
        channel.m_title = cursor.getString(cursor.getColumnIndex(QuikcSQLite.COLUMN_CHANNEL_TITLE));
        channel.m_isListening = cursor.getInt(cursor.getColumnIndex(QuikcSQLite.COLUMN_CHANNEL_LISTENING));

        return channel;
    }
}