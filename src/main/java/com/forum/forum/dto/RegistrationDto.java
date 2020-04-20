package com.forum.forum.dto;

import com.forum.forum.other.validation.PasswordMatch;
import com.forum.forum.other.validation.UniqueEmail;
import com.forum.forum.other.validation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatch
public class RegistrationDto implements Password{


    @NotBlank
    @UniqueUsername
    private String username;

    @UniqueEmail
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, max = 24)
    private String password;

    @NotBlank
    private String confirmpassword;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getConfirmpassword() {
        return confirmpassword;
    }

}
