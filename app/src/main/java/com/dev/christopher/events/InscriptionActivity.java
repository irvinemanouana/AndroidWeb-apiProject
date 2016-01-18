package com.dev.christopher.events;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dev.christopher.events.WebServices.SignUp;

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
    String username,email,name,lastname,password;

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
        name = edtpassword.getText().toString();
        lastname = edtlastname.getText().toString();
        password = edtpassword.getText().toString();
        new SignUp().execute();
    }

}
