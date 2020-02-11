package com.forum.forum.controller;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.model.HelpMessage;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PageController {
    private HelpMessageDao helpMessageDao;
    private TopicService topicService;

    @Autowired
    public PageController(HelpMessageDao helpMessageDao, TopicService topicService) {
        this.helpMessageDao = helpMessageDao;
        this.topicService = topicService;
    }

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("helpMessage", new HelpMessage());
        return "index";
    }

    @PostMapping("/help")
    public String help(@Valid @ModelAttribute HelpMessage helpMessage, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "fragments/elements :: footer-help";
        }
        helpMessageDao.save(helpMessage);
        return "redirect:/";
    }

    @GetMapping("/write")
    public String getWrite(Model model){
        return "write";
    }

    @PostMapping("/write/topic")
    public String write(@Valid Topic topic, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "";
        }
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        topic.setUser(user);
        topicService.addTopic(topic);
        return "redirect:/";
    }
}
