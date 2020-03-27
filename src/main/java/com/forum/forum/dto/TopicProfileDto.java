package com.forum.forum.dto;

import com.forum.forum.model.Comment;
import com.forum.forum.model.Vote;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

public class TopicProfileDto {

    private String userUsername;
    private String createdAt;
    private String title;
    private Integer comments;
    private Integer votes;
    private Long id;

    public TopicProfileDto() {
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
        this.createdAt = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH).format(createdAt);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes.size();
    }
}
