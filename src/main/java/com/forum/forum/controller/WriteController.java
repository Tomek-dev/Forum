package com.forum.forum.controller;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.dto.QuestionDto;
import com.forum.forum.dto.TopicDto;
import com.forum.forum.model.HelpMessage;
import com.forum.forum.model.Question;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.service.QuestionService;
import com.forum.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/write")
public class WriteController {
    private HelpMessageDao helpMessageDao;
    private TopicService topicService;
    private QuestionService questionService;

    @Autowired
    public WriteController(HelpMessageDao helpMessageDao, TopicService topicService, QuestionService questionService) {
        this.helpMessageDao = helpMessageDao;
        this.topicService = topicService;
        this.questionService = questionService;
    }

    @GetMapping("/topic")
    public String getWriteTopic(Model model){
        model.addAttribute("topic", new Topic());
        model.addAttribute("helpMessage", new HelpMessage());
        return "topic";
    }

    @GetMapping("/question")
    public String getWriteQuestion(Model model){
        model.addAttribute("question", new Question());
        model.addAttribute("helpMessage", new HelpMessage());
        return "question";
    }

    @PostMapping("/topic")
    public String writeTopic(@Valid Topic topic, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "topic";
            //TODO
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        topicService.addPost(topic, ((User) principal).getUsername());
        return "redirect:/";
    }

    @PostMapping("/question")
    public String writeQuestion(@Valid Question question, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "question";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        questionService.addPost(question, ((User) principal).getUsername());
        return "redirect:/";
    }

}
