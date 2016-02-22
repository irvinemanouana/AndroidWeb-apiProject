package com.dev.christopher.events.internet.webclients;

import com.dev.christopher.events.Models.Event;
import com.dev.christopher.events.Models.EventCreate;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

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

    @PUT("/api/event/{id}")
    public void putEvent( @Path("id") String id, Callback<Event> response);

    @DELETE("/api/event/{id}")
    public void deleteEvent( @Path("id") String id, Callback<Event> response);

    @POST("/api/event/{id}/subscribe/{user}")
    public void subscribeToevent(@Path("id")String id_event,@Path("user")String id_user,Callback response);

    @DELETE("/{id}/unsubscribe/{user}")
    public void unsubscribeToevent(@Path("id")String id_event,@Path("user")String id_user,Callback response);
}
