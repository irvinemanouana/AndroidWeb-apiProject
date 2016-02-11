package com.dev.christopher.events;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dev.christopher.events.internet.restapi.BaseRestAPI.CallbackEventAPI;
import com.dev.christopher.events.internet.restapi.CategoryRestAPI;
import com.dev.christopher.events.Models.Category;
import com.dev.christopher.events.session.SessionManager;


import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.cat)
    EditText editText;
    @Bind(R.id.newcat)
    Button button;
    String cat,json;
    SessionManager sessionManager;
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        HashMap hashMap = sessionManager.storeUser();
        Log.d("hashMap",String.valueOf(hashMap));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryRestAPI.getInstance().getCategories(new CallbackEventAPI<List<Category>>() {
                    @Override
                    public void onSuccess(List<Category> categories) {
                        Log.d("categories", String.valueOf(categories));
                    }

                    @Override
                    public void onFailure(RetrofitError error) {
                        Log.d("categories", String.valueOf(error));
                    }
                });
            }
        });

    }



}
