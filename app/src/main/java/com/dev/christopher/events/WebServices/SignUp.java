package com.dev.christopher.events.WebServices;

import android.os.AsyncTask;
import android.util.Log;

import com.dev.christopher.events.Config.Config;
import com.dev.christopher.events.Json.BodyParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Christopher on 17/01/2016.
 */
public class SignUp extends AsyncTask<Objects,Void,JSONObject> {

    String username ="superman";
    String email="sup@gmail.com";
    String password ="azerty";
    String name = "clark";
    String firstname ="kent";
    String url = new Config().URL_API;
    String json = "{\"username\":\"" + username + "\",\"password\":\""+password+"\",\"email\":\""+email+"\",\"name\":\""+name+"\",\"firstname\":\""+firstname+"\"}";
    @Override
    protected JSONObject doInBackground(Objects... params) {
        Log.d("Json",json);
        JSONObject jsonObject = null;
        BodyParser bodyParser = new BodyParser();
        try {
            bodyParser.post(url,json);
            String data = bodyParser.getResponse(url);
            jsonObject = new JSONObject(data);
            Log.d("data",data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        if (jsonObject.has("error")){
            Log.d("error","error");
        }else {
            Log.d("cool","cool");
        }

    }
}
