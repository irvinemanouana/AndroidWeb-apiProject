package com.dev.christopher.events.session;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dev.christopher.events.Models.Category;
import com.dev.christopher.events.R;
import com.dev.christopher.events.ResultActivity;
import com.dev.christopher.events.internet.restapi.BaseRestAPI;
import com.dev.christopher.events.internet.restapi.CategoryRestAPI;
import com.dev.christopher.events.internet.restapi.EventRestAPI;

import java.util.List;

import retrofit.RetrofitError;

public class FilterActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView) findViewById(R.id.lisCat);
        CategoryRestAPI.getInstance().getCategories(new BaseRestAPI.CallbackEventAPI<List<Category>>() {
            @Override
            public void onSuccess(List<Category> o) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,o);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Category category1 = (Category) parent.getItemAtPosition(position);
                        String idcat = category1.get_id();
                        String namecat = category1.getName();
                        Log.d("categories", idcat);
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("id", idcat);
                        intent.putExtra("name", namecat);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(RetrofitError error) {

            }
        });
    }

}
