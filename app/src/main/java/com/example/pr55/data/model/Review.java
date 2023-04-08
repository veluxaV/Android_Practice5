package com.example.pr55.data.model;

import java.util.Date;

public class Review {
    private int id;
    private final int userId;
    private final int autoServiceId;
    private final String comment;
    private final int rating;
    private final Date date;

    public Review(int id, int userId, int autoServiceId, String comment, int rating, Date date) {
        this.id = id;
        this.userId = userId;
        this.autoServiceId = autoServiceId;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getAutoServiceId() {
        return autoServiceId;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    public Date getDate() {
        return date;
    }
    public void setId(int id) { this.id = id;}
}
