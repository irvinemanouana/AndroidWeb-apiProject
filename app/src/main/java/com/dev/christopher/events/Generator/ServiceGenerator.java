package com.dev.christopher.events.Generator;

import com.dev.christopher.events.Config.Configs;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Christopher on 09/02/2016.
 */

/*
Service Generator
The ServiceGenerator is our API/HTTP client heart.
In its current state, it only defines one method to create a basic REST adapter for a given class/interface
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = Configs.WEB.URL.BASE_HOST;
    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(API_BASE_URL)
            .setClient(new OkClient(new OkHttpClient()));

    public static <S> S createService(Class<S> serviceClass){
        RestAdapter restAdapter = builder.build();
        return restAdapter.create(serviceClass);
    }
}
