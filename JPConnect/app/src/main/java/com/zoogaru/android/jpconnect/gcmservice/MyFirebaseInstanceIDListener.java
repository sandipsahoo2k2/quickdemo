package com.zoogaru.android.jpconnect.gcmservice;

/**
 * Created by sandeep on 3/31/16.
 */

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.zoogaru.android.jpconnect.QKConstants;

public class MyFirebaseInstanceIDListener extends FirebaseInstanceIdService {

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(QKConstants.TAG, "Firebase Refreshed token: " + refreshedToken);
    }
}