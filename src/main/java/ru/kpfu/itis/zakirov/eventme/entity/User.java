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
    private String avatarUrl;

    public User(Integer id, String username, String email, String password, Role role, List<Event> events, List<Review> reviews, String avatarUrl) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.events = events;
        this.reviews = reviews;
        this.avatarUrl = avatarUrl;
    }

    public User(String username, String email, String password, Role role, List<Event> events, List<Review> reviews, String avatarUrl) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.events = events;
        this.reviews = reviews;
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void setReviews(List<Review> reviews) {
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
}
