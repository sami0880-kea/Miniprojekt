package com.example.miniprojekt.controllers;

import com.example.miniprojekt.model.Wishlist;
import com.example.miniprojekt.repository.WishlistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WishlistController {
    WishlistRepository wishlistRepository;

    public WishlistController () { wishlistRepository = new WishlistRepository(); }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Wishlist>> getWishlists(){
        List<Wishlist> superheroes = wishlistRepository.getWishlists();
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }
}
