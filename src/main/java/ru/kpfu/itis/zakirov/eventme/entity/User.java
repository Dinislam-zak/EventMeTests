package ru.kpfu.itis.zakirov.eventme.entity;

import java.util.List;

public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private List<Event> events;
    private List<Review> reviews;

    public User(Integer id, String username, String email, String password, Role role, List<Event> events, List<Review> reviews) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.events = events;
        this.reviews = reviews;
    }

    public User(String username, String email, String password, Role role, List<Event> events, List<Review> reviews) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.events = events;
        this.reviews = reviews;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
