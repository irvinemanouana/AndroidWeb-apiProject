package com.dev.christopher.events;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;

import com.dev.christopher.events.Config.Configs;
import com.dev.christopher.events.Json.BodyParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InscriptionActivity extends AppCompatActivity {
    @Bind(R.id.input_layout_username)
    TextInputLayout textInputLayoutusernamel;

    @Bind(R.id.input_username)
    EditText edtusername;

    @Bind(R.id.input_layout_email)
    TextInputLayout textInputLayoutemail;

    @Bind(R.id.input_email)
    EditText edtemail;

    @Bind(R.id.input_layout_lastname)
    TextInputLayout textInputLayoutLastname;

    @Bind(R.id.input_lastname)
    EditText edtlastname;

    @Bind(R.id.input_name)
    EditText edtname;
    @Bind(R.id.input_password)
    EditText edtpassword;

    /*@Bind(R.id.create)
    Button signup;*/
    String username,email,name,firstname,password;
    String url = new Configs().URL_API;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @OnClick(R.id.create)
    public void sendForm() {

        username = edtusername.getText().toString();
        email = edtemail.getText().toString();
        name = edtname.getText().toString();
        firstname = edtlastname.getText().toString();
        password = edtpassword.getText().toString();
        new SignUpClass().execute();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }
    class SignUpClass extends AsyncTask<Objects,Void,JSONObject> {

        String json = "{\"username\":\"" + username + "\",\"password\":\""+password+"\",\"email\":\""+email+"\",\"name\":\""+name+"\",\"firstname\":\""+firstname+"\"}";
        @Override
        protected JSONObject doInBackground(Objects... params) {
            Log.d("Json",json);
            JSONObject jsonObject = null;
            BodyParser bodyParser = new BodyParser();
            try {
               String response = bodyParser.post(url, json);

                String data = bodyParser.getResponse(url);
                jsonObject = new JSONObject(data);
                Log.d("data",response);
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


}
