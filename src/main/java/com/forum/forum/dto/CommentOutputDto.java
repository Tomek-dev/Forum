package com.forum.forum.dto;

public class CommentOutputDto {

    private String comment;
    private String username;
    private String postedAt;

    public CommentOutputDto(String comment, String username, String postedAt) {
        this.comment = comment;
        this.username = username;
        this.postedAt = postedAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }
}
