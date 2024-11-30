package ru.kpfu.itis.zakirov.eventme.dto;

public class UserEventDto {
    private Integer userId;
    private Integer eventId;

    public UserEventDto(Integer userId, Integer eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getEventId() {
        return eventId;
    }
}
