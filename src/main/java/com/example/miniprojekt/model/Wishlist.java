package com.example.miniprojekt.model;

import java.util.Date;

public class Wishlist {
    private int id;
    private String title;
    private String description;
    private Date createdAt;
    private int userId;

    public Wishlist(int id, String title, String description, Date createdAt, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getUserId() {
        return userId;
    }
}
