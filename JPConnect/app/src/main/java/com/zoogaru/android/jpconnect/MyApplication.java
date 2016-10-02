package com.zoogaru.android.jpconnect;

import android.support.multidex.MultiDex;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sandeep on 9/24/16.
 */
public class MyApplication extends android.support.multidex.MultiDexApplication {

    public static final String TAG = MyApplication.class
            .getSimpleName();

    private static MyApplication mInstance;
    public AtomicInteger m_msgId = new AtomicInteger();
    GlobalUtil t = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        t = new GlobalUtil(this);
        MultiDex.install(this);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
