package com.dev.christopher.events.internet.webclients;

import com.dev.christopher.events.Models.Category;

import java.util.List;

import retrofit.Callback;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Christopher on 08/02/2016.
 */
public interface ICategoryClient {
    @GET("/api/category/all")
    public void getAllCategory(Callback<List<Category>> response);


}
