package com.dev.christopher.events.WebServices;

import android.os.AsyncTask;

import com.dev.christopher.events.Json.BodyParser;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Christopher on 17/01/2016.
 */
public class SignUp extends AsyncTask<Objects,Void,String> {

    String url = "http://localhost:8080/api/user";
    String json ="{'username':'nana','password':'azerty','email':'nana@gmail.com','name':'nanou','firstname':'loulou'}" ;
    @Override
    protected String doInBackground(Objects... params) {
        BodyParser bodyParser = new BodyParser();
        try {
            bodyParser.post(url,json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
