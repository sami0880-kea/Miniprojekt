package com.example.miniprojekt.controllers;

import com.example.miniprojekt.model.Wishlist;
import com.example.miniprojekt.model.WishlistItem;
import com.example.miniprojekt.repository.WishlistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class WishlistController {
    WishlistRepository wishlistRepository;

    public WishlistController () { wishlistRepository = new WishlistRepository(); }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Wishlist>> getWishlists(){
        List<Wishlist> wishlists = wishlistRepository.getWishlists();
        return new ResponseEntity<>(wishlists, HttpStatus.OK);
    }

    @GetMapping(path = "/wishlists")
    public String getWishlists(Model model){
        List<Wishlist> wishlists = wishlistRepository.getWishlists();
        model.addAttribute("wishlist", wishlists);
        return "wishlists";
    }

    @GetMapping(path = "/items/{id}")
    public ResponseEntity<List<WishlistItem>> getWishlistItems(@PathVariable int id){
        List<WishlistItem> wishlistItems = wishlistRepository.getWishlistItems(id);
        return new ResponseEntity<>(wishlistItems, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<Wishlist>> getWishlist(@PathVariable int id){
        List<Wishlist> wishlist = wishlistRepository.getWishlist(id);
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

}
