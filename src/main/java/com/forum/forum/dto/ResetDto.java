package com.forum.forum.dto;

import com.forum.forum.validation.PasswordMatch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatch
public class ResetDto implements Password{

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 24)
    private String password;

    public ResetDto(@NotNull @NotEmpty @Size(min = 6, max = 24) String password, @NotNull @NotEmpty String confirmpassword) {
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public ResetDto() {
    }

    @NotNull
    @NotEmpty
    private String confirmpassword;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getConfirmpassword() {
        return confirmpassword;
    }

    @Override
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}
