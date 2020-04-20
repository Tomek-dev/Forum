package com.forum.forum.controller;

import com.forum.forum.dto.SearchDto;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class WriteController {

    private TopicService topicService;

    @Autowired
    public WriteController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/write")
    public String getWriteTopic(Model model){
        model.addAttribute("search", new SearchDto());
        model.addAttribute("topic", new TopicInputDto());
        return "write";
    }

    @PostMapping("/write")
    public String writeTopic(@Valid TopicInputDto topicInputDto, Authentication authentication, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "write";
        }
        topicService.addTopic(topicInputDto, authentication.getName());
        return "redirect:/";
    }
}
