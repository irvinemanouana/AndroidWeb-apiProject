package com.dev.christopher.events.internet.restapi;

import android.util.Log;

import com.dev.christopher.events.Models.User;
import com.dev.christopher.events.internet.webclients.IUserClient;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nicolas on 23/02/2016.
 */
public class UserRestAPI extends BaseRestAPI {

    /***************************************** CLASS VARS *****************************************/
    private IUserClient mUserClient;

    /*************************************** SINGLETON MGMT ***************************************/
    private static UserRestAPI INSTANCE = new UserRestAPI();

    public static synchronized UserRestAPI getInstance(){
        return INSTANCE;
    }

    private UserRestAPI() {
        super();
        mUserClient = mServiceGenerator.getUserClient();
    }

    /*************************************
     * PUBLIC REST METHOD
     *************************************/
    public void getUser(final CallbackEventAPI<User> callback) {
        mUserClient.getUser(new CallbackRetrofit<User>() {
            @Override
            public void success(User user, Response response) {
                Log.d("user",user.toString());
                callback.onSuccess(user);
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
                callback.onFailure(error);
            }
        });
    }

}
