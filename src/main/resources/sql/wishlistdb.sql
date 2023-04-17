CREATE DATABASE IF NOT EXISTS wishlistDatabase DEFAULT CHARACTER SET utf8;

USE wishlistDatabase;

CREATE TABLE users (
  userId INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE wishlist (
  wishlistId INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  userId INT NOT NULL,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  createdAt DATETIME NOT NULL,
  FOREIGN KEY (userId) REFERENCES user (userId)
);

CREATE TABLE wishlistItem (
  wishlistItemId INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  price FLOAT NOT NULL,
  url VARCHAR(255),
  imageUrl VARCHAR(255),
  createdAt DATETIME NOT NULL,
  wishlistId INT NOT NULL,
  FOREIGN KEY (wishlistId) REFERENCES wishlist (wishlistId)
);

INSERT INTO users (name, email, password) VALUES
('John Doe', 'johndoe@example.com', '1234'),
('Jane Smith', 'janesmith@example.com', '1234'),
('Mike Johnson', 'mikejohnson@example.com', '1234'),
('Emily Jones', 'emilyjones@example.com', '1234'),
('David Lee', 'davidlee@example.com','1234');

INSERT INTO wishlist (userId, title, description, createdAt) VALUES
(1, 'Birthday Wishlist', 'My birthday is coming up and these are the items I would love to receive', '2022-05-10 12:00:00'),
(2, 'Christmas Wishlist', 'These are the gifts I would like to get for Christmas this year', '2022-12-01 09:30:00'),
(3, 'Summer Vacation Wishlist', 'These are the things I want to bring for my summer vacation', '2023-06-20 15:45:00'),
(4, 'Baby Registry Wishlist', 'These are the items I need for my new baby', '2023-08-15 08:00:00'),
(5, 'Home Improvement Wishlist', 'These are the things I want to upgrade in my home', '2023-04-30 18:20:00');

INSERT INTO wishlistItem (name, description, price, url, imageUrl, createdAt, wishlistId) VALUES
('Nintendo Switch', 'Portable gaming console', 299.99, 'https://www.nintendo.com/switch/', 'https://www.example.com/images/nintendo-switch.jpg', '2022-05-10 12:00:00', 1),
('AirPods Pro', 'Wireless noise-cancelling earbuds', 249.99, 'https://www.apple.com/airpods-pro/', 'https://www.example.com/images/airpods-pro.jpg', '2022-12-01 09:30:00', 2),
('Beach Towel', 'Large towel for the beach', 19.99, 'https://www.example.com/beach-towel', 'https://www.example.com/images/beach-towel.jpg', '2023-06-20 15:45:00', 3),
('Baby Stroller', 'Lightweight and easy to maneuver', 399.99, 'https://www.example.com/baby-stroller', 'https://www.example.com/images/baby-stroller.jpg', '2023-08-15 08:00:00', 4),
('Smart Thermostat', 'Energy-efficient thermostat that learns your preferences', 249.99, 'https://www.example.com/smart-thermostat', 'https://www.example.com/images/smart-thermostat.jpg', '2023-04-30 18:20:00', 5);
