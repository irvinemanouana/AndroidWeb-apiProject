package com.dev.christopher.events;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dev.christopher.events.Models.User;
import com.dev.christopher.events.internet.restapi.BaseRestAPI;
import com.dev.christopher.events.internet.restapi.UserRestAPI;

import retrofit.RetrofitError;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();

        UserRestAPI.getInstance().getUser(new BaseRestAPI.CallbackEventAPI<User>() {
            @Override
            public void onSuccess(User user) {
                changeActivity(MainActivity.class);
            }

            @Override
            public void onFailure(RetrofitError error) {
                changeActivity(LoginActivity.class);
            }
        });
    }

    private void changeActivity(Class classs) {
        startActivity(new Intent(this, classs));
        finish();
    }
}
