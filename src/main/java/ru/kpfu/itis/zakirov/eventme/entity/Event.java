package ru.kpfu.itis.zakirov.eventme.entity;

import java.sql.Timestamp;
import java.util.List;

public class Event {
    private Integer id;
    private String title;
    private String description;
    private Timestamp date;
    private User organizer;
    private List<User> participants;
    private List<Review> reviews;

    public Event(Integer id, String title, String description, Timestamp date, User organizer, List<User> participants, List<Review> reviews) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.organizer = organizer;
        this.participants = participants;
        this.reviews = reviews;
    }

    public Event(String title, String description, Timestamp date, User organizer, List<User> participants, List<Review> reviews) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.organizer = organizer;
        this.participants = participants;
        this.reviews = reviews;
    }

    public Event() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDate() {
        return date;
    }

    public User getOrganizer() {
        return organizer;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Integer getOrganizerId() {
        return organizer.getId();
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizer.setId(organizerId);
    }
}
