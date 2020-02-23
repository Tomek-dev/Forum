package com.forum.forum.dto;

public class UserOutputDto {

    private String username;
    private String createdAt;
    private int topicCount;
    private int commentCount;

    public UserOutputDto(String username, String createdAt, int topicCount, int commentCount) {
        this.username = username;
        this.createdAt = createdAt;
        this.topicCount = topicCount;
        this.commentCount = commentCount;
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

    public int getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(int topicCount) {
        this.topicCount = topicCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
