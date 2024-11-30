package ru.kpfu.itis.zakirov.eventme.dto;

import java.time.LocalDateTime;

public class EventDto {
    private String title;
    private String description;
    private LocalDateTime date;

    public EventDto(String title, String description, LocalDateTime date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }
}