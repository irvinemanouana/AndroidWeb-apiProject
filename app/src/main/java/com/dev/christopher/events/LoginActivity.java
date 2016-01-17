package com.dev.christopher.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.fab)
    Button newaccount;


    @Bind(R.id.input_email)
    EditText edtMail;
    @Bind(R.id.input_layout_email)
    TextInputLayout textInputLayoutemail;

    @Bind(R.id.input_password)
    EditText edtPassword;
    @Bind(R.id.input_layout_password)
    TextInputLayout textInputLayoutpassword;

    @Bind(R.id.buttonlog)
    Button buttonlog;

    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        buttonlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             SignIn();

            }
        });


        newaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InscriptionActivity.class);
                startActivity(intent);
            }
        });
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
    }
    public void SignIn(){

        if (edtMail.getText().toString().isEmpty()){
            textInputLayoutemail.setError(getString(R.string.error_email));

        }if (edtPassword.getText().toString().isEmpty()){
            textInputLayoutpassword.setError(getString(R.string.error_password));
        }else{
            textInputLayoutemail.setErrorEnabled(false);
            textInputLayoutpassword.setErrorEnabled(false);
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
