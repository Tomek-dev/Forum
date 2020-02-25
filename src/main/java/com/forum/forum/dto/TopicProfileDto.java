package com.forum.forum.dto;

public class TopicProfileDto {

    private String username;
    private String createdAt;
    private String title;
    private int commentsCount;
    private Long id;

    public TopicProfileDto(String username, String createdAt, String title, int commentsCount, Long id) {
        this.username = username;
        this.createdAt = createdAt;
        this.title = title;
        this.commentsCount = commentsCount;
        this.id = id;
    }

    public TopicProfileDto(String createdAt, String title, int commentsCount, Long id) {
        this.createdAt = createdAt;
        this.title = title;
        this.commentsCount = commentsCount;
        this.id = id;
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

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
