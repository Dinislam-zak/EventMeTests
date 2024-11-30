package ru.kpfu.itis.zakirov.eventme.entity;

public class UserEvent {

    private User user;
    private Event event;

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public UserEvent(User user, Event event) {
        this.user = user;
        this.event = event;
    }
}
