package com.example.miniprojekt.model;

import java.util.Date;

public class WishlistItem {
    private int id;
    private String name;
    private String description;
    private double price;
    private String url;
    private String imageUrl;
    private Date createdAt;

    private int wishlistId;

    public WishlistItem(int id, String name, String description, double price, String url, String imageUrl, Date createdAt, int wishlistId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.url = url;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.wishlistId = wishlistId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getWishlistId() {
        return wishlistId;
    }
}
