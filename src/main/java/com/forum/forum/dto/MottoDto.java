package com.forum.forum.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MottoDto {

    @NotNull
    @NotEmpty
    private String motto;

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }
}
