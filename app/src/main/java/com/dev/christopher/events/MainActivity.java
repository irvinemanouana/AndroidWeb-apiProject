package com.dev.christopher.events;



import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.dev.christopher.events.Models.Category;
import com.dev.christopher.events.Models.Event;
import com.dev.christopher.events.internet.restapi.BaseRestAPI;
import com.dev.christopher.events.internet.restapi.BaseRestAPI.CallbackEventAPI;

import com.dev.christopher.events.internet.restapi.CategoryRestAPI;
import com.dev.christopher.events.internet.restapi.EventRestAPI;
import com.dev.christopher.events.session.FilterActivity;


import java.util.List;
import java.util.logging.FileHandler;

import retrofit.RetrofitError;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton actionButton;
    Spinner spinner;
    private List<Event> eventList;
    EventAdapter eventAdapter;
    Boolean aBoolean ;
    @Override
    protected void onStart() {
        super.onStart();
        spinner = (Spinner) findViewById(R.id.trie);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        displayEvent();

        actionButton = (FloatingActionButton) findViewById(R.id.floatbutton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateEventActivity.class);
                startActivity(intent);
            }
        });
        getCategories();
    }

    public void displayEvent(){

        EventRestAPI.getInstance().getAllEvent(new CallbackEventAPI<List<Event>>() {
            @Override
            public void onSuccess(List<Event> o) {
                Log.d("o", String.valueOf(o));
                eventList = o;
                listView = (ListView) findViewById(R.id.listViewEvent);
                eventAdapter = new EventAdapter(getApplicationContext(), eventList);
                listView.setAdapter(eventAdapter);

            }

            @Override
            public void onFailure(RetrofitError error) {
                Log.d("o", String.valueOf(error));
            }
        });
    }

    public void init(List<Category> categories){

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category category1 = (Category) spinner.getSelectedItem();
                String idcat = category1.get_id();
                String namecat = category1.getName();
                Log.d("categories", idcat);
                //Intent intent = new Intent(getApplicationContext(), TrieActivity.class);
                //intent.putExtra("id", idcat);
                // intent.putExtra("name", namecat);
                // startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        spinner.setAdapter(adapter);


    }
    public void getCategories(){
        CategoryRestAPI.getInstance().getCategories(new BaseRestAPI.CallbackEventAPI<List<Category>>() {
            @Override
            public void onSuccess(List<Category> categories) {
                Log.d("categories", String.valueOf(categories));
                init(categories);
            }

            @Override
            public void onFailure(RetrofitError error) {
                Log.d("categories", String.valueOf(error));
            }
        });
    }
    List<Event> trieEventByCategorie(List<Event> events,String id_cat){
        List<Event> eventstrie = null;
        for (int i=0;i<events.size();i++){
            Event  event = events.get(i);
            if (event.getCategory().get_id().equals(id_cat))
               eventstrie.add(event);

        }
        return  eventstrie;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
