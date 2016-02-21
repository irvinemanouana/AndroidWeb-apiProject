package com.dev.christopher.events.Models;

/**
 * Created by Christopher on 21/02/2016.
 */
public class EventCreate {
    private String title;
    private String categoryId;
    private String date;
    private String description;

    public EventCreate(String title, String categoryId, String date, String description) {
        this.title = title;
        this.categoryId = categoryId;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "EventCreate{" +
                "title='" + title + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
