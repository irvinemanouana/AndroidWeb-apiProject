package com.dev.christopher.events;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.christopher.events.Models.Category;
import com.dev.christopher.events.Models.Event;
import com.dev.christopher.events.internet.restapi.BaseRestAPI;
import com.dev.christopher.events.internet.restapi.EventRestAPI;
import com.dev.christopher.events.session.SessionManager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by Christopher on 12/02/2016.
 */
public class EventAdapter extends ArrayAdapter<Event> {
    private ArrayList members;
    private String creator;
    private SessionManager sessionManager;
    public EventAdapter(Context context, List<Event> events) {
        super(context,0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Event event = getItem(position);
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_event,parent,false);
        }
        Category category = event.getCategory();
        TextView title =(TextView) convertView.findViewById(R.id.titleEvent);
        TextView categoryName = (TextView) convertView.findViewById(R.id.catname);
        TextView desc =(TextView) convertView.findViewById(R.id.description);
        TextView date =(TextView) convertView.findViewById(R.id.date);
        Button buttondelete =(Button) convertView.findViewById(R.id.delete);
        Button buttonP = (Button) convertView.findViewById(R.id.subscribe);

        title.setText(event.getTitle());
        desc.setText(event.getDescription());
        date.setText(event.getDate());
        categoryName.setText(category.getName());


        sessionManager = new SessionManager(getContext());
        HashMap hashMap = sessionManager.storeUser();
        String id = (String) hashMap.get("_id");
        String id_creator = event.getCreator();
        if (id.equals(id_creator))
            buttonP.setVisibility(View.GONE);
        members = event.getMembers();
        if (!id.equals(id_creator))
            buttondelete.setVisibility(View.GONE);
        if (members.size()==0)
            buttondelete.setVisibility(View.GONE);
        for (int i =0;i<members.size();i++){
            Log.d("test", String.valueOf(members.get(i)));
            if (members.get(i).equals(id))
                Log.d("Message","in");
            Log.d("creator",event.getCreator());
        }

        buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"TEST",Toast.LENGTH_SHORT).show();

            }
        });
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             EventRestAPI.getInstance().deleteEvent(event.get_id(), new BaseRestAPI.CallbackEventAPI<Event>() {
                 @Override
                 public void onSuccess(Event o) {
                     Log.d("delete",event.toString());
                 }

                 @Override
                 public void onFailure(RetrofitError error) {
                    Log.d("delete",String.valueOf(error));
                 }
             });
            }
        });
        return convertView;
    }


}
