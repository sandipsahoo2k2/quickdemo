package com.zoogaru.android.jpconnect.gcmservice;

/**
 * Created by sandeep on 3/31/16.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.zoogaru.android.jpconnect.GlobalUtil;
import com.zoogaru.android.jpconnect.QKConstants;
import com.zoogaru.android.jpconnect.QKUtils;
import com.zoogaru.android.jpconnect.R;
import com.zoogaru.android.jpconnect.view.MainActivity;

import java.util.Date;
import java.util.Map;

//https://quickdemo-b4cc9.firebaseapp.com/
public class MyFirebaseMessagingListener extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        String from = remoteMessage.getFrom();
        Log.d(QKConstants.TAG, "Received Firebase message From: " + from);

        // Check if message contains a data payload.
        Map<String, String> data = remoteMessage.getData();
        if (data.size() > 0) {
            String message = data.get("body");
            String titles[] = from.split("/");
            String title = titles[2];
            if(GlobalUtil.m_quikc_store.isChannelListening(title)) {
                sendNotification(title, message, null, data);
            }
            else
            {
                Log.d(QKConstants.TAG, "Message ignored from " + title);
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(QKConstants.TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    /**
     * Processing user specific push message
     * It will be displayed with / without image in push notification tray
     * */
    private void processUserMessage(String title, boolean isBackground, String message) {
        if (!isBackground) {
                // verifying whether the app is in background or foreground
                if (!QKUtils.isAppIsInBackground(getApplicationContext())) {

                    // app is in foreground, broadcast the push message
                    Intent pushNotification = new Intent(QKConstants.PUSH_NOTIFICATION);
                    pushNotification.putExtra("type", QKConstants.PUSH_TYPE_USER);
                    pushNotification.putExtra("message", message);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
                    // play notification sound
                    QKUtils notificationUtils = new QKUtils();
                    notificationUtils.playNotificationSound();
                } else {
                    // app is in background. show the message in notification try
                    Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                    sendNotification(message, title, resultIntent, null);
                }

        } else {
            // the push notification is silent, may be other operations needed
            // like inserting it in to SQLite
        }
    }
    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(String title, String message, Intent anIntent, Map<String, String> aData) {

        //Intent intent = new Intent(this, MainActivity.class);
        int notificationId = (int) (new Date().getTime() % Integer.MAX_VALUE);
        String messageURI = null;
        boolean isSurvey = false;
        Bundle extras = new Bundle();

        Intent resultIntent = new Intent();
        if (anIntent != null) {
            resultIntent.setAction(anIntent.getAction());
            resultIntent.putExtras(anIntent.getExtras());
        }
        String messageType = aData.get("survey");
        if (messageType.equals("true")) {
            /* get the handle to message preferences */
            messageURI = aData.get("message_uri");
            isSurvey = true;
        }
        extras.putBoolean("survey", isSurvey);
        extras.putString("message_uri", messageURI);
        extras.putInt("notificationId", notificationId);

        PendingIntent notificationIntent = PendingIntent.getActivity(this, 0 /* Request code */, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent yesIntent = new Intent();
        yesIntent.putExtras(extras);
        yesIntent.setAction(QKConstants.ACTION_OK);
        PendingIntent piYes = PendingIntent.getBroadcast(this, notificationId, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent dismissIntent = new Intent();
        dismissIntent.putExtras(extras);
        dismissIntent.setAction(QKConstants.ACTION_DISMISS);
        PendingIntent piDismiss = PendingIntent.getBroadcast(this, notificationId + 1, dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setColor(Color.parseColor(QKConstants.COLOR_PRIMARY))
                .setContentIntent(notificationIntent).setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message));

        notificationBuilder.addAction(R.drawable.ic_stat_content_clear, QKConstants.ACTION_DISMISS, piDismiss);
        notificationBuilder.addAction(R.drawable.ic_stat_action_done, QKConstants.ACTION_OK, piYes);
        Notification n = notificationBuilder.build();
        n.flags = Notification.FLAG_NO_CLEAR | Notification.FLAG_ONGOING_EVENT;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, n);
        Log.d(QKConstants.TAG, "Notification id: " + notificationId);
    }
}
