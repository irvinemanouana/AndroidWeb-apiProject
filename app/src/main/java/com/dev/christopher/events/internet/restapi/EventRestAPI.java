package com.dev.christopher.events.internet.restapi;

import android.util.Log;

import com.dev.christopher.events.Models.Event;
import com.dev.christopher.events.Models.EventCreate;
import com.dev.christopher.events.internet.webclients.IEventClient;

import java.util.List;

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
    public void createEvent(EventCreate event, final CallbackEventAPI<Event> callback){
        mEventClient.createEvent(event, new CallbackRetrofit<Event>() {
            @Override
            public void success(Event event, Response response) {
                Log.d("Event create",event.toString());
                callback.onSuccess(event);
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
                Log.d("Event create", String.valueOf(error));
                callback.onFailure(error);
            }
        });

    }
    public void deleteEvent(String idEvent,final CallbackEventAPI callbackEventAPI){
        mEventClient.deleteEvent(idEvent, new CallbackRetrofit() {
            @Override
            public void success(Object o, Response response) {
                callbackEventAPI.onSuccess(o);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("delete failure", error.toString());
                super.failure(error);
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
