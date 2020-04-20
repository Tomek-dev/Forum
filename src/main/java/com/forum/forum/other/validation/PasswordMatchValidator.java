package com.forum.forum.other.validation;

import com.forum.forum.dto.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Password> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(Password password, ConstraintValidatorContext constraintValidatorContext) {
        return password.getPassword().equals(password.getConfirmpassword());
    }
}
