package com.dev.christopher.events.internet.webclients;

import com.dev.christopher.events.Models.Event;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Christopher on 08/02/2016.
 */
public interface IEventClient {
    //get All event
    @GET("/api/event/all")
    public void getAllEvents(Callback<List<Event>> response );
    //Create event
    @POST("/api/event")
    public void createEvent(@Body Event event, Callback<Event> response);
}
