package com.dev.christopher.events.Models;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Christopher on 16/01/2016.
 */
public class Event {
    private String title;
    private String description;
    private String date;
    private String _id;
    private int __v;
    private String createdAt;
    private ArrayList members;
    private String creator;
    private String categoryId;
    private Category category;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    public Event(){}


    public Event(String title, String description, String date,String categoryId) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.categoryId = categoryId;
    }

    public ArrayList getMembers() {
        return members;
    }

    public void setMembers(ArrayList members) {
        this.members = members;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", _id='" + _id + '\'' +
                ", __v=" + __v +
                ", createdAt='" + createdAt + '\'' +
                ", members=" + members +
                ", creator='" + creator + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", category=" + category +
                '}';
    }
}
