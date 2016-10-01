package com.zoogaru.android.jpconnect.entity;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

/**
 * Created by sandeep on 5/15/16.
 */
@IgnoreExtraProperties
public class Channel {
    public String m_id;
    public String m_timeStamp ;
    public int m_isListening ;
    public String m_key ;
    @PropertyName("channel")
    public String m_title ;
    public int m_counter ;

    public String toString()
    {
        return m_title;
    }

    public Channel()
    {
        m_timeStamp = "";
        this.m_counter = 0;
        m_isListening = 1;
    }

    public Channel(String key, String title) {
        super();
        this.m_title = title;
        this.m_counter = 0;
        m_isListening = 1;
        m_key = key;
    }
    public Channel(String title, int isTopic) {
        super();
        this.m_title = title;
        this.m_counter = 0;
        m_isListening = 1;
    }

    public Channel(String m_title, boolean m_notification_switch_present, boolean m_can_delete) {
        this.m_title = m_title;
        m_isListening = 1;
    }

    public String getId()
    {
        return m_id;
    }

    public String getTitle()
    {
        return m_title;
    }

    public String getkey()
    {
        return m_key;
    }

    public void setkey(String aKey)
    {
        m_key = aKey;
    }

    public int getCounter()
    {
        return m_counter;
    }

    public void setCounter(int count)
    {
        m_counter = count;
    }

    public String getTimeStamp() {
        return m_timeStamp;
    }

    public void setTimeStamp(String m_timeStamp) {
        this.m_timeStamp = m_timeStamp;
    }

    public boolean isListening() {
        return ( m_isListening == 0) ? false : true;
    }

    public void setListening(int m_isListening) {
        this.m_isListening = m_isListening;
    }
}
