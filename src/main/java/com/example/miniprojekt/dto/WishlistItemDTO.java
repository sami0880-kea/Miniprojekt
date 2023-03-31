package com.example.miniprojekt.dto;

import com.example.miniprojekt.model.WishlistItem;

import java.util.List;

public class WishlistItemDTO {
    private String wishlistTitle;
    private String wishlistDescription;

    private List<WishlistItem> wishlistItems;

    public WishlistItemDTO(String wishlistTitle, String wishlistDescription, List<WishlistItem> wishlistItems) {
        this.wishlistTitle = wishlistTitle;
        this.wishlistDescription = wishlistDescription;
        this.wishlistItems = wishlistItems;
    }

    public String getWishlistTitle() {
        return wishlistTitle;
    }

    public String getWishlistDescription() {
        return wishlistDescription;
    }

    public List<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistTitle(String wishlistTitle) {
        this.wishlistTitle = wishlistTitle;
    }

    public void setWishlistDescription(String wishlistDescription) {
        this.wishlistDescription = wishlistDescription;
    }

    public void setWishlistItems(List<WishlistItem> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }
}
