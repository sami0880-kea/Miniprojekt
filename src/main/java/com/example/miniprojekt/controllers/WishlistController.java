package com.example.miniprojekt.controllers;

import com.example.miniprojekt.dto.WishlistItemDTO;
import com.example.miniprojekt.model.User;
import com.example.miniprojekt.model.Wishlist;
import com.example.miniprojekt.model.WishlistItem;
import com.example.miniprojekt.repository.WishlistRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RequestMapping("/")
@Controller
public class WishlistController {
    WishlistRepository wishlistRepository;

    public WishlistController () { wishlistRepository = new WishlistRepository(); }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email")String email, @RequestParam("pw") String pw, HttpSession session, Model model)
    {
        User user = wishlistRepository.getUser(email);
        if (user != null)
            if (user.getPassword().equals(pw)) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(21600);
                return "redirect:/wishlists";
            }
        model.addAttribute("wrongLogin", true);
        return "index";
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Wishlist>> getWishlists(){
        List<Wishlist> wishlists = wishlistRepository.getWishlists();
        return new ResponseEntity<>(wishlists, HttpStatus.OK);
    }

    @GetMapping(path = "/wishlists")
    public String getWishlists(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Wishlist> wishlists = wishlistRepository.getUserWishlists(user.getId());
        model.addAttribute("wishlist", wishlists);
        model.addAttribute("userName", user.getName());
        model.addAttribute("cUserId", user.getId());
        model.addAttribute("updateWishlist", new Wishlist());
        return "wishlists";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("signUpError", "");
        return "index";
    }

    @PostMapping("/signup")
    public String addSignUp(@ModelAttribute("user") User user, HttpSession session, Model model) {
        if(user.getName().length() < 1 || user.getEmail().length() < 1 || user.getPassword().length() < 1) {
            model.addAttribute("signUpError", "Fill out all fields!");
            return "index";
        }
        try {
            session.setAttribute("user", wishlistRepository.getUser(wishlistRepository.addSignUp(user)));
            session.setMaxInactiveInterval(21600);
            return "redirect:/wishlists";
        } catch (RuntimeException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                model.addAttribute("signUpError", "Email is already registered.");
            }
            return "index";
        }
    }


    @GetMapping("/create/wishlist/{id}")
    public String createWishlist(@PathVariable int id, Model model) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(id);
        model.addAttribute("wishlist", wishlist);
        return "wishlists";
    }

    @PostMapping("/create/wishlist")
    public String createWishlist(@RequestParam("userId") int userId, @RequestParam("title")String title, @RequestParam("description") String description, @ModelAttribute("wishlist") Wishlist wishlist, Model model) {
        if(title.length() < 1 || description.length() < 1)
            model.addAttribute("notFilled", true);
        wishlist.setUserId(userId);
        wishlistRepository.createWishlist(wishlist);
        return "redirect:/wishlists";
    }

    @PostMapping("/update/wishlist")
    public String updateWishlist(@RequestParam("id")String id, @RequestParam("title")String title, @RequestParam("description") String description, @ModelAttribute("wishlist") Wishlist wishlist) {
        wishlistRepository.updateWishlist(wishlist);
        return "redirect:/wishlists";
    }

    @PostMapping("/update/wishlistItem")
    public String updateWishlistItem(@RequestParam("id") int id, @RequestParam("name")String name, @RequestParam("description") String description, @RequestParam("price") double price, @RequestParam("url") String url, @RequestParam("imageUrl") String imageUrl, @RequestParam("wishlistId") String wishlistId, @ModelAttribute("wishlistItem") WishlistItem wishlistItem) {
        wishlistRepository.updateWishlistItem(wishlistItem);
        return "redirect:/items/" + wishlistId;
    }

    @PostMapping("/create/item")
    public String createWish(@RequestParam("wishlistId") int wishlistId, @RequestParam("name")String name, @RequestParam("description") String description, @RequestParam("url") String url, @RequestParam("imageUrl") String imageUrl, @RequestParam("price") double price,  @ModelAttribute("wishlistItem") WishlistItem wishlistItem, Model model) {
        if(name.length() < 1 || description.length() < 1)
            model.addAttribute("notFilled", true);
        if(!(imageUrl.length() > 1))
            wishlistItem.setImageUrl("https://rushcountyfoundation.org/wp-content/uploads/2015/12/gift-06.jpg");
        wishlistItem.setWishlistId(wishlistId);
        wishlistRepository.addWishlistItem(wishlistItem);
        return "redirect:/items/" + wishlistId;
    }

    @GetMapping("/addItem/{id}")
    public String addItem(@PathVariable int id, Model model) {
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setWishlistId(id);
        model.addAttribute("wishlistItem", wishlistItem);
        return "addWishlistItem";
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute("wishlistItem") WishlistItem wishlistItem) {
        wishlistRepository.addWishlistItem(wishlistItem);
        return "redirect:/items/" + wishlistItem.getWishlistId();
    }

    @GetMapping("/deleteItem/{id}")
    public String deleteWish(@PathVariable int id){
        int wishlistId = wishlistRepository.getItemId(id).getWishlistId();
        wishlistRepository.deleteWish(id);
        return "redirect:/items/" + wishlistId;
    }


    @GetMapping(path = "/item2/{id}")
    public ResponseEntity<List<WishlistItem>> getItem(@PathVariable int id){
        List<WishlistItem> wishlistItems = wishlistRepository.getItem(id);
        return new ResponseEntity<>(wishlistItems, HttpStatus.OK);
    }

    @GetMapping(path = "/items/{id}")
    public String getItem(Model model, @PathVariable int id) {
        WishlistItemDTO wishlistItems = wishlistRepository.getWishlistItems(id);
        model.addAttribute("wishlistTitle", wishlistItems.getWishlistTitle());
        model.addAttribute("wishlistId", id);
        model.addAttribute("itemList", wishlistItems.getWishlistItems());
        model.addAttribute("updateItem", new WishlistItem());
        return "wishlistItems";
    }

    @GetMapping("/delete/{id}")
    public String deleteWishlist(@PathVariable int id){
        wishlistRepository.deleteWishlist(id);
        return "redirect:/wishlists";
    }
}
