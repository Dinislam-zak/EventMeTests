package ru.kpfu.itis.zakirov.eventme.dto;

public class ReviewDto {
    private int rating;
    private String comment;

    public ReviewDto(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}