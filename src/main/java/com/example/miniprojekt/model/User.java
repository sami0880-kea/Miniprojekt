package com.example.miniprojekt.model;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private int age;

    public User(int id, String name, String password, String email, int age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
