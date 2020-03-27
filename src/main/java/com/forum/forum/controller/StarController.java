package com.forum.forum.controller;

import com.forum.forum.model.User;
import com.forum.forum.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StarController {

    private StarService starService;

    @Autowired
    public StarController(StarService starService) {
        this.starService = starService;
    }

    @PostMapping("/star/{username}")
    public String star(@PathVariable String username, @AuthenticationPrincipal User user){
        starService.star(username, user);
        return "redirect:/profile/" + username;
    }
}
