package com.forum.forum.controller;

import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.EmailDto;
import com.forum.forum.dto.RegistrationDto;
import com.forum.forum.dto.ResetDto;
import com.forum.forum.service.ResetService;
import com.forum.forum.service.TopicService;
import com.forum.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class SignUpController {

    private UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-started")
    public String getRegistration(Model model){
        model.addAttribute("registrationDto", new RegistrationDto());
        return "registration";
    }

    @PostMapping("/get-started")
    public String registration(@Valid RegistrationDto registrationDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registration";
        }
        userService.addUser(registrationDto);
        return "redirect:/login";
    }
}
