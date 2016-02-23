package com.dev.christopher.events.internet.webclients;

import com.dev.christopher.events.Models.User;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Christopher on 08/02/2016.
 */
public interface IUserClient {

    @GET("/api/user")
    void getUser(Callback<User> response);
}
