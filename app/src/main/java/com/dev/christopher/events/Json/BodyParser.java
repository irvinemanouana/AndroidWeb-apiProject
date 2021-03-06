package com.dev.christopher.events.Json;

import android.util.Log;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
