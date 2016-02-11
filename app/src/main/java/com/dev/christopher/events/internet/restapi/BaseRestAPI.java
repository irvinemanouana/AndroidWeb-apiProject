package com.dev.christopher.events.internet.restapi;

import retrofit.RetrofitError;

/**
 * Created by nicolas on 11/02/2016.
 */
public class BaseRestAPI {
    /*********************************** INTERFACE ***********************************/
    public interface CallbackEventAPI<T> {
        void onSuccess(T o);
        void onFailure(RetrofitError error);
    }

    /************************************** CLASS VARS *************************************/
    protected ServiceGenerator mServiceGenerator;

    /********************************** CONSTRUCTOR **********************************/
    protected BaseRestAPI(){
        mServiceGenerator = ServiceGenerator.getInstance();
    }
}
