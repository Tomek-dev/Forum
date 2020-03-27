package com.forum.forum.controller;

import com.forum.forum.model.User;
import com.forum.forum.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VoteController {

    private VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/vote/{id}")
    public String vote(@PathVariable Long id, @AuthenticationPrincipal User user){
        voteService.vote(user, id);
        return "redirect:/topic/"+id;
    }
}
