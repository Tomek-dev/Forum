package com.forum.forum.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommentInputDto {

    @NotEmpty
    @NotNull
    private String comment;

    public CommentInputDto(@NotEmpty @NotNull String comment) {
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
