package com.forum.forum.controller;

import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.EmailDto;
import com.forum.forum.dto.RegistrationDto;
import com.forum.forum.dto.ResetDto;
import com.forum.forum.service.ResetService;
import com.forum.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class AuthController {

    private UserDao userDao;
    private ResetService resetService;
    private UserService userService;

    @Autowired
    public AuthController(UserDao userDao, ResetService resetService, UserService userService) {
        this.userDao = userDao;
        this.resetService = resetService;
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/test")
    public String getTest(){
        return "test";
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

    @GetMapping("/forgot")
    public String getForgot(Model model){
        model.addAttribute("emailDto", new EmailDto());
        return "forgot";
    }

    @PostMapping("/forgot")
    public String forgot(@Valid EmailDto emailDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "forgot";
        }
        resetService.sendToken(emailDto);
        return "redirect:/login";
    }

    @GetMapping("/reset")
    public String getReset(@RequestParam("v") UUID token, Model model){
        if(token==null){
            return "redirect:/forgot";
        }
        model.addAttribute("resetDto", new ResetDto());
        model.addAttribute("token", token);
        return "reset";
    }

    @PostMapping("/reset")
    public String reset(@RequestParam("v")UUID token, @Valid ResetDto resetDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "reset";
        }
        resetService.resetPassword(token, resetDto);
        return "redirect:/login";
    }
}
