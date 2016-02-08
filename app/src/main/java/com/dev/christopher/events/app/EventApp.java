package com.dev.christopher.events.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.dev.christopher.events.Config.Configs;

/**
 * Created by nicolas on 08/02/2016.
 */
public class EventApp extends Application {
    public static Context baseContext;

    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        baseContext = getApplicationContext();

        sharedPreferences = getSharedPreferences(Configs.PREFERENCES.FILE_NAME, Context.MODE_PRIVATE);
    }
}
