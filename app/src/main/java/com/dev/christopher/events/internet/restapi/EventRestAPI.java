package com.dev.christopher.events.internet.restapi;

import com.dev.christopher.events.Models.Event;
import com.dev.christopher.events.internet.webclients.IEventClient;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nicolas on 11/02/2016.
 */
public class EventRestAPI extends BaseRestAPI {

    /***************************************** CLASS VARS *****************************************/
    private IEventClient mEventClient;

    /*************************************** SINGLETON MGMT ***************************************/
    private static EventRestAPI INSTANCE = new EventRestAPI();

    public static synchronized EventRestAPI getInstance(){
        return INSTANCE;
    }

    private EventRestAPI() {
        super();
        mEventClient = mServiceGenerator.getEventClient();
    }

    /************************************* PUBLIC REST METHOD *************************************/
    public void createEvent(Event event, final CallbackEventAPI<Event> callback){
        mEventClient.createEvent(event, new CallbackRetrofit<Event>() {
            @Override
            public void success(Event event, Response response) {
                callback.onSuccess(event);
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
                callback.onFailure(error);
            }
        });
    }

}
