package com.dev.christopher.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.dev.christopher.events.Models.EventCreate;
import com.dev.christopher.events.internet.restapi.BaseRestAPI;
import com.dev.christopher.events.internet.restapi.CallbackRetrofit;
import com.dev.christopher.events.internet.restapi.CategoryRestAPI;
import com.dev.christopher.events.internet.restapi.EventRestAPI;
import com.dev.christopher.events.Models.Category;
import com.dev.christopher.events.Models.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CreateEventActivity extends AppCompatActivity {
    @Bind(R.id.spinnerCat)
    Spinner spinner;
    @Bind(R.id.edtlibelle)
    EditText title;
    @Bind(R.id.edtdescription)
    EditText description;
    private String titletxt,desctxt,datetxt,idcat;
    public List<Category> categoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        ButterKnife.bind(this);
        Button button = (Button)findViewById(R.id.create);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titletxt = title.getText().toString();
                desctxt = description.getText().toString();
                datetxt = initDate();
                
                Event event = new Event(titletxt, desctxt, datetxt, idcat);
                Log.d("Event", event.toString());
                EventRestAPI.getInstance().createEvent(event, new CallbackRetrofit<Event>() {
                    @Override
                    public void success(Event event, Response response) {
                        Log.d("response App",event.toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("response App",String.valueOf(error));
                        super.failure(error);
                    }
                });
                //Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //startActivity(intent);
                //finish();
            }
        });
        getCategories();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    public void getCategories(){
        CategoryRestAPI.getInstance().getCategories(new BaseRestAPI.CallbackEventAPI<List<Category>>() {
            @Override
            public void onSuccess(List<Category> categories) {
                Log.d("categories", String.valueOf(categories));
                init(categories);
            }

            @Override
            public void onFailure(RetrofitError error) {
                Log.d("categories", String.valueOf(error));
            }
        });
    }

    public String initDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = new Date();
        Log.d("date",dateFormat.format(date));
        return dateFormat.format(date);
    }

    public void init(List<Category> categories){
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = spinner.getSelectedItem().toString();
                Category category1 = (Category) spinner.getSelectedItem();

                idcat = category1.get_id();
                Log.d("categories", idcat);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}


