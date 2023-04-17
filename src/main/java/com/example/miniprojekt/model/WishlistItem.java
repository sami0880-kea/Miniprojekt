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

    public WishlistItem() {
    }

    public WishlistItem(int id) {
        this.id = id;
    }

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

    public void updateInfo(int id, String name, String description, double price, String url, String imageUrl, int wishlistId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.url = url;
        this.imageUrl = imageUrl;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    @Override
    public String toString() {
        return "WishlistItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt=" + createdAt +
                ", wishlistId=" + wishlistId +
                '}';
    }
}
