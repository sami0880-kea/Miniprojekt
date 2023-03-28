package com.example.miniprojekt.repository;

import com.example.miniprojekt.model.Wishlist;
import com.example.miniprojekt.model.WishlistItem;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("wishlist")
public class WishlistRepository {
    private final String db_url = "jdbc:mysql://localhost:3306/wishlist_db";
    private final String uid = "root";
    private final String pwd = "Samim123";

    public List<Wishlist> getWishlists() {
        List<Wishlist> wishlists = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "SELECT * FROM wishlist;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("created_at");
                int userId = rs.getInt("user_id");
                wishlists.add(new Wishlist(id, title, description, createdAt, userId));
            }
            return wishlists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Wishlist> getWishlist(int sWisthlist) {
        List<Wishlist> wishlists = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "SELECT * FROM `wishlist` WHERE `user_id` = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, sWisthlist);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("created_at");
                int userId = rs.getInt("user_id");
                wishlists.add(new Wishlist(id, title, description, createdAt, userId));
            }
            return wishlists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<WishlistItem> getWishlistItems(int sWishlistId) {
        List<WishlistItem> wishlistItems = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "SELECT * FROM `wishlist_item` WHERE `wishlist_id` = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, sWishlistId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                String url = rs.getString("url");
                Date createdAt = rs.getDate("created_at");
                int wishlistId = rs.getInt("wishlist_id");
                wishlistItems.add(new WishlistItem(id, name, description, price, url, createdAt, wishlistId));
            }
            return wishlistItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
