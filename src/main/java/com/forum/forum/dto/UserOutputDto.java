package com.forum.forum.dto;

import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

public class UserOutputDto {

    private String username;
    private String createdAt;
    private int topics;
    private int comments;

    public UserOutputDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH).format(createdAt);
    }

    public int getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics.size();
    }

    public int getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments.size();
    }
}
