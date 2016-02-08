package com.dev.christopher.events.internet.authentication.utils;

import okhttp3.Credentials;

/**
 * Created by nicolas on 08/02/2016.
 */
public class AuthenticationUtils {

    private static final String ANDROID_CLIENT_ID = "android_manage_event";
    private static final String ANDROID_CLIENT_SECRET = "fraf_uzEch@g5Guh";

    public static String getClientAuthentication() {
        return Credentials.basic(ANDROID_CLIENT_ID, ANDROID_CLIENT_SECRET);
    }

}
