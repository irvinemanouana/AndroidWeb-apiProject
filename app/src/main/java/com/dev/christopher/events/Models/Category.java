package com.dev.christopher.events.Models;

/**
 * Created by Christopher on 16/01/2016.
 */
public class Category {
    private String name;
    private String createdAt;
    private String updatedAt;
    private String _id;

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String get_id() {
        return _id;
    }

    @Override
    public String toString() {
        return name;
    }
}
