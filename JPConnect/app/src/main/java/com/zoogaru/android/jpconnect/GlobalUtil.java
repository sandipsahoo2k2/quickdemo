package com.zoogaru.android.jpconnect;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.preference.PreferenceManager;
import android.util.Log;

import com.zoogaru.android.jpconnect.adapter.SQLiteQuikcAdapter;
import com.zoogaru.android.jpconnect.storage.QKPreferences;

/**
 * Created by sandeep on 9/24/16.
 */
public class GlobalUtil {

    public static SQLiteQuikcAdapter m_quikc_store = null;
    public static SharedPreferences m_sharedReader = null;
    public static QKPreferences m_sharedPref = null;

    public GlobalUtil(Context aContext) //We need an activity
    {
        if (m_quikc_store == null) {
            try {
                m_quikc_store = new SQLiteQuikcAdapter(aContext);
                m_quikc_store.open();
            } catch (SQLException ex) {
                Log.d("QSurvey", "database creation failed");
            }
        }
        m_sharedReader = PreferenceManager.getDefaultSharedPreferences(aContext);
        m_sharedPref = new QKPreferences();
    }
}
