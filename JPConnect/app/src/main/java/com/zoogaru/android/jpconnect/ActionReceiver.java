package com.zoogaru.android.jpconnect;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by sandeep on 9/24/16.
 */
public class ActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(QKConstants.TAG,"Action Received!");
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();
        boolean isSurvey = bundle.getBoolean("survey");
        int notificationId = bundle.getInt("notificationId");
        if(action.equals("DISMISS")) {
            Log.v(QKConstants.TAG,"Pressed DISMISS");
            if(isSurvey)
            {
                String msgUri = bundle.getString("message_uri");
                QKUtils.onSurveyMessageClicked(msgUri, false, context, notificationId);
            }
        } else if(action.equals("OK")) {
            Log.v(QKConstants.TAG,"Pressed OK");
            if(isSurvey)
            {
                String msgUri = bundle.getString("message_uri");
                QKUtils.onSurveyMessageClicked(msgUri, true, context, notificationId);
            }
        }
        NotificationManager nMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancel(notificationId);
    }
}
