package com.example.miniprojekt.repository;

import com.example.miniprojekt.dto.WishlistItemDTO;
import com.example.miniprojekt.model.User;
import com.example.miniprojekt.model.Wishlist;
import com.example.miniprojekt.model.WishlistItem;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("wishlist")
public class WishlistRepository {
    private final String db_url = "jdbc:mysql://localhost:3306/wishlistdatabase";
    private final String uid = "root";
    private final String pwd = "root";

    public List<Wishlist> getWishlists() {
        List<Wishlist> wishlists = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "SELECT * FROM wishlist;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int id = rs.getInt("wishlistId");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("createdAt");
                int userId = rs.getInt("userId");
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
            String SQL = "SELECT * FROM `wishlist` WHERE `userId` = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, sWisthlist);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("wishlistId");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("createdAt");
                int userId = rs.getInt("userId");
                wishlists.add(new Wishlist(id, title, description, createdAt, userId));
            }
            return wishlists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<WishlistItem> getItem(int sItem) {
        List<WishlistItem> wishlistItems = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "SELECT * FROM `wishlistItem` WHERE `wishlistItemId` = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, sItem);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("wishlistItemId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                String url = rs.getString("url");
                String imageUrl = rs.getString("imageUrl");
                Date createdAt = rs.getDate("createdAt");
                int wishlistId = rs.getInt("wishlistId");
                wishlistItems.add(new WishlistItem(id, name, description, price, url, imageUrl, createdAt, wishlistId));
            }
            return wishlistItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<WishlistItem> getWishlistItems2(int sWishlistId) {
        List<WishlistItem> wishlistItems = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "SELECT * FROM `wishlistItem` WHERE `wishlistId` = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, sWishlistId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("wishlistItemId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                String url = rs.getString("url");
                String imageUrl = rs.getString("imageUrl");
                Date createdAt = rs.getDate("createdAt");
                int wishlistId = rs.getInt("wishlistId");
                wishlistItems.add(new WishlistItem(id, name, description, price, url, imageUrl, createdAt, wishlistId));
            }
            return wishlistItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public WishlistItemDTO getWishlistItems(int sWishlistId) {
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "SELECT title, description FROM wishlist WHERE wishlistId = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, sWishlistId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String wTitle = rs.getString("title");
                String wDescription = rs.getString("description");

                List<WishlistItem> wishlistItems = new ArrayList<>();
                SQL = "SELECT * FROM wishlistItem WHERE wishlistId = ?";
                pstmt = con.prepareStatement(SQL);
                pstmt.setInt(1, sWishlistId);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("wishlistItemId");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    Double price = rs.getDouble("price");
                    String url = rs.getString("url");
                    String imageUrl = rs.getString("imageUrl");
                    Date createdAt = rs.getDate("createdAt");
                    int wishlistId = rs.getInt("wishlistId");

                    wishlistItems.add(new WishlistItem(id, name, description, price, url, imageUrl, createdAt, wishlistId));
                }
                return new WishlistItemDTO(wTitle, wDescription, wishlistItems);
            }
        return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createWishlist(Wishlist wishlist) {
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "INSERT INTO `wishlist` (`userId`, `title`, `description`, `createdAt`) VALUES (1, ?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, wishlist.getTitle());
            pstmt.setString(2, wishlist.getDescription());
            pstmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteWishlist (int wishlistID){
        try(Connection con = DriverManager.getConnection(db_url,uid,pwd)) {
            String SQL = "DELETE FROM wishlistItem WHERE wishlistId = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1,wishlistID);
            pstmt.executeUpdate();

            String SQL2 = "DELETE FROM wishlist WHERE wishlistId = ?;";
            PreparedStatement pstmt2 = con.prepareStatement(SQL2);
            pstmt2.setInt(1,wishlistID);
            pstmt2.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void addWishlistItem(WishlistItem wishlistItem) {
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "INSERT INTO wishlistItem (`name`, `description`, `price`, `url`, `imageUrl`, `createdAt`, `wishlistId`) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, wishlistItem.getName());
            pstmt.setString(2, wishlistItem.getDescription());
            pstmt.setDouble(3, wishlistItem.getPrice());
            pstmt.setString(4, wishlistItem.getUrl());
            pstmt.setString(5, wishlistItem.getImageUrl());
            pstmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setInt(7, wishlistItem.getWishlistId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSignUp (User signUp) {
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
            String SQL = "INSERT INTO user (name, email, password) VALUES (?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, signUp.getName());
            pstmt.setString(2, signUp.getEmail());
            pstmt.setString(3, signUp.getPassword());

            pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException("Email already exists", e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String isEmail) {
        User user = null;
        try (Connection con = DriverManager.getConnection(db_url, uid, pwd))
        {
            String SQL = "SELECT * FROM User WHERE email = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, isEmail);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("userId");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                user = new User(id,name,email,password);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
