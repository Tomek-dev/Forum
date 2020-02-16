package com.forum.forum.dto;

public class QuestionDto {
    private String posted;
    private String username;
    private String title;

    public QuestionDto(String posted, String username, String title) {
        this.posted = posted;
        this.username = username;
        this.title = title;
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
}
