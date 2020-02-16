package com.forum.forum.controller;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.dto.QuestionDto;
import com.forum.forum.dto.TopicDto;
import com.forum.forum.model.HelpMessage;
import com.forum.forum.service.QuestionService;
import com.forum.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private QuestionService questionService;
    private HelpMessageDao helpMessageDao;

    @Autowired
    public PageController(TopicService topicService, QuestionService questionService, HelpMessageDao helpMessageDao) {
        this.topicService = topicService;
        this.questionService = questionService;
        this.helpMessageDao = helpMessageDao;
    }

    @GetMapping("/")
    public String getHome(Model model){
        List<TopicDto> topics = topicService.getLast10Topics();
        List<QuestionDto> questions = questionService.getLast4Topics();
        model.addAttribute("topics", topics);
        model.addAttribute("questions", questions);
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
}
