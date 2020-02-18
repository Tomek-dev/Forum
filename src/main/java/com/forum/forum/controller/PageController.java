package com.forum.forum.controller;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.dto.TopicOutputDto;
import com.forum.forum.model.HelpMessage;
import com.forum.forum.model.User;
import com.forum.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PageController {

    private TopicService topicService;
    private HelpMessageDao helpMessageDao;

    @Autowired
    public PageController(TopicService topicService, HelpMessageDao helpMessageDao) {
        this.topicService = topicService;
        this.helpMessageDao = helpMessageDao;
    }

    @GetMapping("/")
    public String getHome(Model model){
        List<TopicOutputDto> topics = topicService.getLast15Topics();
        model.addAttribute("topics", topics);
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
    public String getWriteTopic(Model model){
        model.addAttribute("topic", new TopicInputDto());
        model.addAttribute("helpMessage", new HelpMessage());
        return "topic";
    }

    @PostMapping("/write")
    public String writeTopic(@Valid TopicInputDto topicInputDto, @ModelAttribute("helpMessage") HelpMessage helpMessage, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "topic";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        topicService.addTopic(topicInputDto, ((User) principal).getUsername());
        return "redirect:/";
    }
}
