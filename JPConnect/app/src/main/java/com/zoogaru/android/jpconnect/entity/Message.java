package com.zoogaru.android.jpconnect.entity;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import java.io.Serializable;

/**
 * Created by Lincoln on 07/01/16.
 */
@IgnoreExtraProperties
public class Message implements Serializable {
    String id ;
    String createdAt;

    @PropertyName("title")
    public String title;

    @PropertyName("body")
    public String body;

    @PropertyName("survey")
    public boolean survey;

    @PropertyName("yesCount")
    public int yesCount;

    @PropertyName("noCount")
    public int noCount;

    public Message() {
    }

    public Message(String id, String message, String createdAt) {
        this.id = id;
        this.body = message;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
