package com.dev.christopher.events;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.dev.christopher.events.Config.Configs;
import com.dev.christopher.events.Hash.Sha512Convert;
import com.dev.christopher.events.Json.BodyParser;
import com.dev.christopher.events.internet.authentication.OAuthToken;
import com.dev.christopher.events.managers.UserManager;
import com.dev.christopher.events.session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.fab)
    Button newaccount;

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Bind(R.id.input_username)
    EditText edtusername;
    @Bind(R.id.input_layout_email)
    TextInputLayout textInputLayoutemail;

    @Bind(R.id.input_password)
    EditText edtPassword;
    @Bind(R.id.input_layout_password)
    TextInputLayout textInputLayoutpassword;

    @Bind(R.id.buttonlog)
    Button buttonlog;

    String username, password, json;
    SessionManager sessionManager;
    String jsusername,jsname,jsfirstname,jsemail;


    @Override
    protected void onStart() {
        super.onStart();
        sessionManager = new SessionManager(getApplicationContext());

       /* if (sessionManager.checkLogin()==true){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        sessionManager= new SessionManager(getApplicationContext());
        buttonlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAndGetToken();
            }
        });

        newaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InscriptionActivity.class);
                startActivity(intent);
            }
        });


    }

    public void loginAndGetToken()  {

        if (edtusername.getText().toString().isEmpty()) {
            textInputLayoutemail.setError(getString(R.string.error_email));

        }
        if (edtPassword.getText().toString().isEmpty()) {
            textInputLayoutpassword.setError(getString(R.string.error_password));
        } else {

            textInputLayoutemail.setErrorEnabled(false);
            textInputLayoutpassword.setErrorEnabled(false);
            username = edtusername.getText().toString();
            password = edtPassword.getText().toString();
            UserManager.getInstance().asyncLogin(username, password, new UserManager.CallbackOAuthAPI() {
                @Override
                public void onSuccess(OAuthToken token) {
                    Log.d("Token",String.valueOf(token));
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(RetrofitError error) {
                    Log.d("Error",String.valueOf(error));
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
