package com.forum.forum.dto;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class EmailDto {

    @NotNull
    @NotEmpty
    private String email;

    public EmailDto(@NotEmpty String email) {
        this.email = email;
    }

    public EmailDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
