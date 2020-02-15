package com.forum.forum.dto;

public class TopicDto {

    private String posted;
    private String username;
    private String title;
    private String description;

    public TopicDto(String posted, String username, String title, String description) {
        this.posted = posted;
        this.username = username;
        this.title = title;
        this.description = description;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
