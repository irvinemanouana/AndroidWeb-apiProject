package com.dev.christopher.events.Models;

import java.util.ArrayList;

/**
 * Created by Christopher on 16/01/2016.
 */
public class Event {
    private ArrayList<User>users;
    private User user;
    private Category category;

    public Event(ArrayList<User> users, User user, Category category) {
        this.users = users;
        this.user = user;
        this.category = category;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser() {
        return user;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Event{" +
                "users=" + users +
                ", user=" + user +
                ", category=" + category +
                '}';
    }
}
