package com.example.pr55.domain;

import java.util.Date;

public class Review {
    private final String id;
    private final String userId;
    private final String autoServiceId;
    private final String comment;
    private final int rating;
    private final Date date;

    public Review(String id, String userId, String autoServiceId, String comment, int rating, Date date) {
        this.id = id;
        this.userId = userId;
        this.autoServiceId = autoServiceId;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getAutoServiceId() {
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
}
