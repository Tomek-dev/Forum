package com.forum.forum.dto;

import com.forum.forum.Type;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicInputDto {

    @NotNull
    @NotEmpty
    private Type type;

    @Size(max = 96)
    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String description;

    public TopicInputDto(Type type, String title, String description) {
        this.type = type;
        this.title = title;
        this.description = description;
    }

    public TopicInputDto() {
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
