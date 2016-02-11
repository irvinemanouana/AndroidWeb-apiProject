package com.dev.christopher.events.internet.authentication.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import com.dev.christopher.events.Config.Configs;
import com.dev.christopher.events.app.EventApp;

import retrofit.RequestInterceptor;

/**
 * Created by nicolas on 11/02/2016.
 */
public class APIResRequestInterceptor implements RequestInterceptor  {
    private SharedPreferences mSharedPreferences;

    public APIResRequestInterceptor(){
        mSharedPreferences = EventApp.baseContext.getSharedPreferences(Configs.PREFERENCES.FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void intercept(RequestFacade request) {
        String accessToken = mSharedPreferences.getString(Configs.PREFERENCES.KEYS.CURRENT_USER_TOKEN, null);
        request.addHeader(Configs.WEB.HEADERS.AUTHORIZATION, String.format("Bearer %s", accessToken));
        request.addHeader("Accept", "application/javascript, application/json");
    }
}
