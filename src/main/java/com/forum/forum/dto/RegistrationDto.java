package com.forum.forum.dto;

import com.forum.forum.validation.PasswordMatch;
import com.forum.forum.validation.UniqueEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatch
public class RegistrationDto implements Password{

    @UniqueEmail
    @NotEmpty
    @NotNull
    private String username;

    @UniqueEmail
    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 6, max = 24)
    private String password;

    @NotEmpty
    @NotNull
    private String confirmpassword;

    public RegistrationDto(String username, String email, String password, String confirmpassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public RegistrationDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
