package com.forum.forum.dto;

import com.forum.forum.model.Comment;
import com.forum.forum.other.DateFormater;

import java.time.LocalDateTime;
import java.util.Set;

public class TopicOutputDto {

    private String userUsername;
    private String title;
    private String description;
    private String type;
    private String createdAt;
    private Long id;
    private Integer comments;

    public TopicOutputDto(String userUsername, String title, String description, String type, String createdAt, Long id, Integer comments) {
        this.userUsername = userUsername;
        this.title = title;
        this.description = description;
        this.type = type;
        this.createdAt = createdAt;
        this.id = id;
        this.comments = comments;
    }

    public TopicOutputDto() {
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments.size();
    }
}
