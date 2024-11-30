package ru.kpfu.itis.zakirov.eventme.entity;

public class Review {
    private int id;
    private User user;
    private Event event;
    private int rating;
    private String comment;

    public Review(int id, User user, Event event, int rating, String comment) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.rating = rating;
        this.comment = comment;
    }

    public Review(Event event, int rating, String comment, User user) {
        this.event = event;
        this.rating = rating;
        this.comment = comment;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
