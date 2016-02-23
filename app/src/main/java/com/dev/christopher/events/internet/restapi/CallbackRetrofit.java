package com.dev.christopher.events.internet.restapi;

import android.util.Log;

import com.dev.christopher.events.BuildConfig;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by nicolas on 11/02/2016.
 */
public abstract class CallbackRetrofit<T> implements Callback<T> {
    @Override
    public abstract void success(T t, Response response);

    @Override
    public void failure(RetrofitError error) {
        logAuthentication(error);
    }

    private void logAuthentication(RetrofitError error){
        if (BuildConfig.DEBUG && error.getResponse() != null){
            String json =  new String(((TypedByteArray)error.getResponse().getBody()).getBytes());
            Log.v("CallbackRetrofit - failure", json);
        }
    }
}