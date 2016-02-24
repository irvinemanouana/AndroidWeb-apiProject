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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dev.christopher.events.Models.EventCreate;
import com.dev.christopher.events.internet.restapi.BaseRestAPI;
import com.dev.christopher.events.internet.restapi.CallbackRetrofit;
import com.dev.christopher.events.internet.restapi.CategoryRestAPI;
import com.dev.christopher.events.internet.restapi.EventRestAPI;
import com.dev.christopher.events.Models.Category;
import com.dev.christopher.events.Models.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;

import retrofit.RetrofitError;


public class CreateEventActivity extends AppCompatActivity {
    @Bind(R.id.spinnerCat)
    Spinner spinner;
    @Bind(R.id.edtlibelle)
    EditText title;
    @Bind(R.id.edtdescription)
    EditText description;
    @Bind(R.id.datep)
    DatePicker datePicker;
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
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                String strDate = format.format(calendar.getTime());
                Log.d("date new", strDate);



                final Date currentTime = new Date();
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                Log.d("UTC time: ",sdf.format(currentTime));
                //System.out.println("UTC time: " + sdf.format(currentTime));

                JSONObject eventobject = new JSONObject();
                try {
                    eventobject.put("title",titletxt);
                    eventobject.put("categoryId",idcat);
                    eventobject.put("description",desctxt);
                    eventobject.put("date","2016-02-25T17:00:54.527Z");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Json",eventobject.toString());
                EventRestAPI.getInstance().createEvent(eventobject, new BaseRestAPI.CallbackEventAPI<Event>() {
                    @Override
                    public void onSuccess(Event o) {
                        Log.d("response create event ",o.toString());
                    }
                    @Override
                    public void onFailure(RetrofitError error) {
                        Log.d("response fail event ", String.valueOf(error));
                        Toast.makeText(getApplicationContext(),"erreur est survenue",Toast.LENGTH_SHORT).show();
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


