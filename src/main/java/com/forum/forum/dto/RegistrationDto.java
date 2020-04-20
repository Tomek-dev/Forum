package com.forum.forum.dto;

import com.forum.forum.validation.PasswordMatch;
import com.forum.forum.validation.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatch
public class RegistrationDto implements Password{


    @NotEmpty
    @NotNull
    @UniqueEmail
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
