package com.dev.christopher.events;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dev.christopher.events.Models.Event;
import com.dev.christopher.events.internet.restapi.BaseRestAPI;
import com.dev.christopher.events.internet.restapi.EventRestAPI;


import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by Christopher on 12/02/2016.
 */
public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(Context context, List<Event> events) {
        super(context,0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Event event = getItem(position);
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_event,parent,false);
        }
        TextView title =(TextView) convertView.findViewById(R.id.titleEvent);
        TextView desc =(TextView) convertView.findViewById(R.id.description);
        TextView date =(TextView) convertView.findViewById(R.id.date);
        Button buttondelete =(Button) convertView.findViewById(R.id.delete);

        title.setText(event.getTitle());
        desc.setText(event.getDescription());
        date.setText(event.getDate());

        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventRestAPI.getInstance().deleteEvent(event.get_id(), new BaseRestAPI.CallbackEventAPI() {
                    @Override
                    public void onSuccess(Object o) {
                        Log.d("delete", String.valueOf(o));
                    }

                    @Override
                    public void onFailure(RetrofitError error) {
                        Log.d("delete error", String.valueOf(error));
                    }
                });
            }
        });
        return convertView;
    }
}
