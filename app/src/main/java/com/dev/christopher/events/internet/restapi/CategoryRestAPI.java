package com.dev.christopher.events.internet.restapi;

import com.dev.christopher.events.Models.Category;
import com.dev.christopher.events.internet.webclients.ICategoryClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nicolas on 11/02/2016.
 */
public class CategoryRestAPI extends BaseRestAPI {

    /***************************************** CLASS VARS *****************************************/
    private ICategoryClient mCategoryClient;

    /*************************************** SINGLETON MGMT ***************************************/
    private static CategoryRestAPI INSTANCE = new CategoryRestAPI();

    public static synchronized CategoryRestAPI getInstance(){
        return INSTANCE;
    }

    private CategoryRestAPI() {
        super();
        mCategoryClient = mServiceGenerator.getCategoryAPI();
    }

    /************************************* PUBLIC REST METHOD *************************************/
    public void getCategories(final CallbackEventAPI<List<Category>> callback){
        mCategoryClient.getAllCategory(new Callback<List<Category>>(){
            @Override
            public void success(List<Category> categories, Response response) {
                callback.onSuccess(categories);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onFailure(error);
            }
        });
    }

}
