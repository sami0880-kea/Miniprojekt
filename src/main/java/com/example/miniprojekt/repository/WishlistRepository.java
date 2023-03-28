package com.example.miniprojekt.repository;

import com.example.miniprojekt.model.Wishlist;
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
            Statement stnt = con.createStatement();
            ResultSet rs = stnt.executeQuery(SQL);
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
}
