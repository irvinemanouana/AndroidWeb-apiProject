package com.dev.christopher.events.internet.restapi;

import com.dev.christopher.events.Config.Configs;
import com.dev.christopher.events.internet.authentication.authenticator.TokenAuthenticator;
import com.dev.christopher.events.internet.authentication.interceptor.APIResRequestInterceptor;
import com.dev.christopher.events.internet.webclients.ICategoryClient;
import com.dev.christopher.events.internet.webclients.IEventClient;
import com.dev.christopher.events.internet.webclients.IUserClient;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Christopher on 09/02/2016.
 */

/*
Service Generator
The ServiceGenerator is our API/HTTP client heart.
In its current state, it only defines one method to create a basic REST adapter for a given class/interface
 */
public class ServiceGenerator {

    /*********************************** CLASS VARS ***********************************/
    private RestAdapter.Builder mRestAdapterBuilder;

    /********************************* SINGLETON MGMT **********************************/
    private static ServiceGenerator INSTANCE = new ServiceGenerator();

    public static synchronized ServiceGenerator getInstance(){
        return INSTANCE;
    }

    private ServiceGenerator(){
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setAuthenticator(new TokenAuthenticator());

        mRestAdapterBuilder = new RestAdapter.Builder()
                .setEndpoint(Configs.WEB.URL.BASE_HOST)
                .setRequestInterceptor(new APIResRequestInterceptor())
                .setClient(new OkClient(okHttpClient))
                .setConverter(new GsonConverter(new Gson()));
    }

    /****************************** PRIVATE METHOD SERVICE GENERATOR ******************************/
    private <S> S createService(Class<S> serviceClass) {
        return mRestAdapterBuilder.build().create(serviceClass);
    }

    /*************************** PUBLIC REST INSTANCE SERVICE GENERATOR ***************************/
    public ICategoryClient getCategoryAPI(){
        return createService(ICategoryClient.class);
    }

    public IEventClient getEventClient(){
        return createService(IEventClient.class);
    }

    public IUserClient getUserClient(){
        return createService(IUserClient.class);
    }
}
