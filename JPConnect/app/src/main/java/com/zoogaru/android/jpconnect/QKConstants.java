package com.zoogaru.android.jpconnect;

/**
 * Created by sandeep on 3/31/16.
 */
public final class QKConstants {
    public static final String TAG = "JPCOnnect";

    public static final String PROFILE_PIC = "profile_pic";
    public static final String USER_EMAIL = "email";
    public static final String USER_NICK_NAME = "nickname";
    public static final String USER_REAL_NAME = "name";
    public static final String USER_PHONE = "phone";

    public static String ACTION_DISMISS = "DISMISS";
    public static String ACTION_OK = "OK";

    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    public static int GOTO_SERVER_TIME = 1000 * 60 * 30; //30 minutes

    public static final long GCM_TIME_TO_LIVE = 60L * 60L * 24L ;
    public static final int APP_MIN_RADIUS = 200 ;
    public static final int APP_DEFAULT_RADIUS = 500;
    public static final int APP_MAX_RADIUS = 3000; //in meters
    public static final int MAP_ZOOM_LEVEL = 15;
    public final static String APP_PURPLE_COLOR = "#7844FF" ;
    public final static String LIGHT_GREEN_COLOR = "#98FB98" ;
    public final static String COLOR_PRIMARY = "#3F51B5" ;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int SPLASH_SCREEN_CODE = 1000;
    public static final int SIGNIN_ACTIVITY_CODE = 1001;
    public static final int MORE_ACTIVITY_CODE = 1002;
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final int RC_SIGN_IN = 9001;

    public static final String NEW_MSG_EVENT = "NEW_MSG_EVENT";
    public static final String NEW_CHAT_EVENT = "NEW_CHAT_EVENT";
    public static final String REGISTRATION_FAILED = "registrationFailed";

    // flag to identify whether to show single line
    // or multi line test push notification tray
    public static boolean appendNotificationMessages = true;

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // type of push messages
    public static final int PUSH_TYPE_CHATROOM = 1;
    public static final int PUSH_TYPE_USER = 2;

    // id to handle the notification in the notification try
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
}
