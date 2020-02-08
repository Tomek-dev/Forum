package com.forum.forum.validation;

import com.forum.forum.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private UserDao userDao;

    @Autowired
    public UniqueUsernameValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userDao.findByUsername(s)==null;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }
}
