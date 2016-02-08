package com.dev.christopher.events;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dev.christopher.events.Config.Configs;
import com.dev.christopher.events.Json.BodyParser;
import com.dev.christopher.events.session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;

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
                cat = editText.getText().toString();
                new Categories().execute();
            }
        });

    }
    class Categories extends AsyncTask <Objects,Void,JSONObject>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            json = "{\"name\":\"" + cat + "\"}";
        }

        @Override
        protected JSONObject doInBackground(Objects... params) {
            JSONObject  jsonObject = null;
            BodyParser bodyParser = new BodyParser();

            try {
               String string= bodyParser.post(new Configs().URL_API_CAT,json);
                jsonObject= new JSONObject(string);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("json",String.valueOf(jsonObject));
            return jsonObject;
        }
    }

}
