package com.forum.forum.dto;

public class TopicOutputDto {

    private String username;
    private String title;
    private String description;
    private String type;
    private String postedAt;

    public TopicOutputDto(String username, String title, String description, String type, String postedAt) {
        this.username = username;
        this.title = title;
        this.description = description;
        this.type = type;
        this.postedAt = postedAt;
    }

    public TopicOutputDto() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }
}
