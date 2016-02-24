package com.dev.christopher.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.dev.christopher.events.Models.Event;
import com.dev.christopher.events.internet.restapi.BaseRestAPI;
import com.dev.christopher.events.internet.restapi.EventRestAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;

public class ResultActivity extends AppCompatActivity {
    ListView listView;
    String  id_cat;
    List<Event> events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.resultList);
        Intent intent = getIntent();
        id_cat = intent.getStringExtra("id");
        Log.d("la categorie",id_cat);
        EventRestAPI.getInstance().getAllEvent(new BaseRestAPI.CallbackEventAPI<List<Event>>() {
            @Override
            public void onSuccess(List<Event> o) {
                events = new ArrayList<Event>();
                for (int i = 0;i<o.size();i++){
                    Event event = o.get(i);
                    if (event.getCategory().get_id().equals(id_cat))
                        events.add(event);
                }
                EventAdapter eventAdapter = new EventAdapter(getApplicationContext(),events);
                listView.setAdapter(eventAdapter);
            }

            @Override
            public void onFailure(RetrofitError error) {

            }
        });
    }

}
