package com.forum.forum.dto;

public class CommentInputDto {

    private String comment;

    public CommentInputDto(String comment) {
        this.comment = comment;
    }

    public CommentInputDto() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
