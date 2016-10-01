package com.zoogaru.android.jpconnect;

import android.app.Application;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sandeep on 9/24/16.
 */
public class MyApplication extends Application {

    public static final String TAG = MyApplication.class
            .getSimpleName();

    private static MyApplication mInstance;
    public AtomicInteger m_msgId = new AtomicInteger();
    GlobalUtil t = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //FirebaseApp.initializeApp(this, FirebaseOptions.fromResource(this));
        t = new GlobalUtil(this);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
