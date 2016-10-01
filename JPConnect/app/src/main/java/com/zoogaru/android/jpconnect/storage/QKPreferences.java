package com.zoogaru.android.jpconnect.storage;

import android.content.SharedPreferences;

import com.zoogaru.android.jpconnect.GlobalUtil;

import java.util.Date;

/**
 * Created by sandeep on 3/31/16.
 */
public final class QKPreferences {

    SharedPreferences.Editor m_editor;
    public static final String PROFILE_PIC = "profile_pic";
    public static final String USER_ACCID = "accid";
    public static final String USER_EMAIL = "email";
    public static final String USER_NICK_NAME = "nickname";
    public static final String USER_REAL_NAME = "name";
    public static final String USER_PHONE = "phone";
    public static final String USER_OCCUPATION = "occupation";
    public static final String LAST_UPDATED_TIME = "last_time";

    public static final String PUSH_NOTIFICATION = "nearby_notifications";
    public static final String APP_NOTIFICATION = "notifications";
    public static final String SHARE_CONTACT = "share_phone";
    public static final String SHARE_PHNO = "share_phone";
    public static final String SHARE_EMAIL = "share_email";
    public static final String SHARE_LOCATION = "share_location";

    public static final String REGISTARTION_INFO_SENT = "sentTokenToServer";
    public static final int GCM_TIME_TO_LIVE = 0 ; //30 minutes

    public static final String HELP_ACTIVITY = "help" ;

    public QKPreferences()
    {
        m_editor = GlobalUtil.m_sharedReader.edit();
    }

    public void setListeningNearby(boolean success)
    {
        m_editor.putBoolean(QKPreferences.PUSH_NOTIFICATION, success).apply();
    }

    public boolean isListeningNearby()
    {
        return GlobalUtil.m_sharedReader.getBoolean(QKPreferences.PUSH_NOTIFICATION, true);
    }

    public void setNotificationEnabled(boolean success)
    {
        m_editor.putBoolean(QKPreferences.APP_NOTIFICATION, success).apply();
    }

    public boolean isNotificationEnabled()
    {
        return GlobalUtil.m_sharedReader.getBoolean(QKPreferences.APP_NOTIFICATION, true);
    }

    public void setPhoneShareEnabled(boolean success)
    {
        m_editor.putBoolean(QKPreferences.SHARE_PHNO, success).apply();
    }

    public void ignoreHelp()
    {
        m_editor.putBoolean(QKPreferences.HELP_ACTIVITY, false).apply();
    }

    public boolean doHelpShow()
    {
        return GlobalUtil.m_sharedReader.getBoolean(QKPreferences.HELP_ACTIVITY, true);
    }

    public boolean isPhoneShareEnabled()
    {
        return GlobalUtil.m_sharedReader.getBoolean(QKPreferences.SHARE_PHNO, false);
    }

    public void setEmailShareEnabled(boolean success)
    {
        m_editor.putBoolean(QKPreferences.SHARE_EMAIL, success).apply();
    }

    public boolean isContactShareEnabled()
    {
        return GlobalUtil.m_sharedReader.getBoolean(QKPreferences.SHARE_CONTACT, false);
    }

    public void setContactShareEnabled(boolean success)
    {
        m_editor.putBoolean(QKPreferences.SHARE_CONTACT, success).apply();
    }

    public boolean isEmailShareEnabled()
    {
        return GlobalUtil.m_sharedReader.getBoolean(QKPreferences.SHARE_EMAIL, false);
    }

    public void setLocationShareEnabled(boolean success)
    {
        m_editor.putBoolean(QKPreferences.SHARE_LOCATION, success).apply();
    }

    public boolean isLocationShareEnabled()
    {
        return GlobalUtil.m_sharedReader.getBoolean(QKPreferences.SHARE_LOCATION, false);
    }

    public void updateRegistartionInfoSent(boolean success)
    {
        m_editor.putBoolean(QKPreferences.REGISTARTION_INFO_SENT, success).apply();
    }

    public boolean isRegistrationInfoSent()
    {
        return GlobalUtil.m_sharedReader.getBoolean(QKPreferences.REGISTARTION_INFO_SENT, false);
    }

    public String getUserName()
    {
        return GlobalUtil.m_sharedReader.getString(QKPreferences.USER_REAL_NAME, "");
    }

    public String getUserEmail()
    {
        return GlobalUtil.m_sharedReader.getString(QKPreferences.USER_EMAIL, "");
    }

    public String getUserPhone()
    {
        return GlobalUtil.m_sharedReader.getString(QKPreferences.USER_PHONE, "");
    }

    public String getProfilePic()
    {
        return GlobalUtil.m_sharedReader.getString(QKPreferences.PROFILE_PIC, "");
    }

    public String getUserID()
    {
        return GlobalUtil.m_sharedReader.getString(QKPreferences.USER_ACCID, "");
    }

    public String getUserNickName()
    {
        return GlobalUtil.m_sharedReader.getString(QKPreferences.USER_NICK_NAME, "");
    }

    public long getLastSavedTime()
    {
        return GlobalUtil.m_sharedReader.getLong(QKPreferences.LAST_UPDATED_TIME, 0);
    }

    public void saveLastTime()
    {
        Date now = new Date();
        m_editor.putLong(QKPreferences.LAST_UPDATED_TIME, now.getTime()).apply();
    }
}