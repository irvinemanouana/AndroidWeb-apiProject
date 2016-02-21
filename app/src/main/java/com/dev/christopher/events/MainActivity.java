package com.dev.christopher.events;


import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.View;


import android.widget.ListView;

import com.dev.christopher.events.Models.Event;
import com.dev.christopher.events.internet.restapi.BaseRestAPI.CallbackEventAPI;

import com.dev.christopher.events.internet.restapi.EventRestAPI;
import com.dev.christopher.events.session.SessionManager;



import java.util.List;

import retrofit.RetrofitError;


public class MainActivity extends AppCompatActivity {
    //@Bind(R.id.listView)
    ListView listView;
    FloatingActionButton actionButton;
    SessionManager sessionManager;
    private List<Event> eventList;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        HashMap hashMap = sessionManager.storeUser();
        Log.d("hashMap",String.valueOf(hashMap));*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        displayEvent();
        actionButton = (FloatingActionButton) findViewById(R.id.floatbutton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateEventActivity.class);
                startActivity(intent);
            }
        });
    }

    public void displayEvent(){

        EventRestAPI.getInstance().getAllEvent(new CallbackEventAPI<List<Event>>() {
            @Override
            public void onSuccess(List<Event> o) {
                Log.d("o",String.valueOf(o));
                eventList =o;
                listView = (ListView) findViewById(R.id.listViewEvent);
                EventAdapter eventAdapter = new EventAdapter(getApplicationContext(),eventList);
                listView.setAdapter(eventAdapter);
            }

            @Override
            public void onFailure(RetrofitError error) {
                Log.d("o",String.valueOf(error));
            }
        });
    }



}
