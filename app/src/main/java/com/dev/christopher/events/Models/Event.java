package com.dev.christopher.events.Models;

import java.util.ArrayList;

/**
 * Created by Christopher on 16/01/2016.
 */
public class Event {
    private String title;
    private String categoryId;
    private String description;
    private String date;

    public Event(String title, String categoryId, String description, String date) {
        this.title = title;
        this.categoryId = categoryId;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
