package com.forum.forum.dto;

public class TopicProfileDto {

    private String username;
    private String createdAt;
    private String title;
    private int commentsCount;

    public TopicProfileDto(String username, String createdAt, String title, int commentsCount) {
        this.username = username;
        this.createdAt = createdAt;
        this.title = title;
        this.commentsCount = commentsCount;
    }
}
