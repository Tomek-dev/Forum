package com.forum.forum.dto;

public class CommentOutputDto {

    private String comment;
    private String username;
    private String postedAt;
    private Long id;

    public CommentOutputDto(String comment, String username, String postedAt, Long id) {
        this.comment = comment;
        this.username = username;
        this.postedAt = postedAt;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
