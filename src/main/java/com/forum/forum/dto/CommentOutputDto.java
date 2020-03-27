package com.forum.forum.dto;

import com.forum.forum.model.Star;
import com.forum.forum.other.DateFormater;

import java.time.LocalDateTime;
import java.util.Set;

public class CommentOutputDto {

    private String comment;
    private String userUsername;
    private String createdAt;
    private Long id;
    private int stars;

    public CommentOutputDto() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = DateFormater.posted(createdAt);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(Set<Star> stars) {
        this.stars = stars.size();
    }
}
