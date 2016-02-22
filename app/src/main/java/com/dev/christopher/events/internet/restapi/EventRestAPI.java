package com.dev.christopher.events.internet.restapi;

import android.util.Log;

import com.dev.christopher.events.Models.Event;
import com.dev.christopher.events.Models.EventCreate;
import com.dev.christopher.events.internet.webclients.IEventClient;

import java.util.List;

import retrofit.Callback;
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
    public void deleteEvent(String idEvent,final CallbackEventAPI<Event> callbackEventAPI){
        mEventClient.deleteEvent(idEvent, new CallbackRetrofit<Event>() {
            @Override
            public void success(Event event, Response response) {
                Log.d("delete event",event.toString());
                callbackEventAPI.onSuccess(event);
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
                Log.d("delete error",String.valueOf(error));
                callbackEventAPI.onFailure(error);
            }
        });
    }

    public void createEvent(Event event,final Callback<Event> callback){
        mEventClient.createEvent(event, new Callback<Event>() {
            @Override
            public void success(Event event, Response response) {
                Log.d("response ",event.toString());
                callback.success(event, response);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("response ",String.valueOf(error));
                callback.failure(error);
            }
        });
    }


    public void getAllEvent(final CallbackEventAPI<List<Event>> callback){
        mEventClient.getAllEvents(new CallbackRetrofit<List<Event>>() {
            @Override
            public void success(List<Event> events, Response response) {
                Log.d("Response",String.valueOf(events));
                callback.onSuccess(events);
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
                Log.d("Response",String.valueOf(error));
                callback.onFailure(error);
            }
        });

    }


}
