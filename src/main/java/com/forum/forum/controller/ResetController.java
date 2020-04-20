package com.forum.forum.controller;

import com.forum.forum.dto.EmailDto;
import com.forum.forum.dto.ResetDto;
import com.forum.forum.service.ResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class ResetController {

    private ResetService resetService;

    @Autowired
    public ResetController(ResetService resetService) {
        this.resetService = resetService;
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
        resetService.createToken(emailDto);
        return "redirect:/login";
    }

    @GetMapping("/reset")
    public String getReset(@RequestParam("value") UUID token, Model model){
        if(token==null){
            return "redirect:/forgot";
        }
        model.addAttribute("resetDto", new ResetDto());
        model.addAttribute("token", token);
        return "reset";
    }

    @PostMapping("/reset")
    public String reset(@RequestParam("value")UUID token, @Valid ResetDto resetDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("token", token);
            return "reset";
        }
        resetService.resetPassword(token, resetDto);
        return "redirect:/login";
    }
}
