package com.dev.christopher.events.Json;

import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


import java.io.IOException;


/**
 * Created by Christopher on 17/01/2016.
 */
public class BodyParser {


    public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    public String post(String url,String json) throws IOException {
        RequestBody body = RequestBody.create(JSON_TYPE, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response =  client.newCall(request).execute();
        String data =response.body().string();
        Log.d("data",data);
        return data;

    }

    public void get(String url,String json){

    }

    public String getResponse(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
