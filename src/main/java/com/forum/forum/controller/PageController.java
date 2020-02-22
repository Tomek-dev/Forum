package com.forum.forum.controller;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.dto.CommentInputDto;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.dto.TopicOutputDto;
import com.forum.forum.model.Comment;
import com.forum.forum.model.HelpMessage;
import com.forum.forum.model.User;
import com.forum.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        model.addAttribute("pageListSize", topicService.getPageListSize());
        model.addAttribute("pageId", 1);
        return "index";
    }

    @GetMapping("/page")
    public String getHome(@RequestParam Long id, Model model){
        List<TopicOutputDto> topics = topicService.getTopicByPage(id);
        model.addAttribute("topics", topics);
        model.addAttribute("helpMessage", new HelpMessage());
        model.addAttribute("pageListSize", topicService.getPageListSize());
        model.addAttribute("pageId", id);
        return "index";
    }

    @GetMapping("/topic")
    public String getHomeWithType(@RequestParam("type") String type, @RequestParam(required = false) Long id, Model model){
        if(id == null){
            id = 1L;
        }
        List<TopicOutputDto> topics = topicService.getLast15TopicsByType(type);
        model.addAttribute("topics", topics);
        model.addAttribute("helpMessage", new HelpMessage());
        model.addAttribute("pageListSize", topicService.getPageListSize());
        model.addAttribute("pageId", id);
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
        return "write";
    }

    @PostMapping("/write")
    public String writeTopic(@Valid TopicInputDto topicInputDto, @ModelAttribute("helpMessage") HelpMessage helpMessage, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "write";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        topicService.addTopic(topicInputDto, ((User) principal).getUsername());
        return "redirect:/";
    }

    @GetMapping("/topic/{id}")
    public String getTopic(@PathVariable Long id, Model model){
        model.addAttribute("topic", topicService.getTopic(id));
        model.addAttribute("comments", topicService.getComments(id));
        model.addAttribute("commentInput", new CommentInputDto());
        model.addAttribute("helpMessage", new HelpMessage());
        return "topic";
    }

    @PostMapping("/topic/{id}/comment")
    public String addComment(@PathVariable Long id, @Valid CommentInputDto commentInputDto, @ModelAttribute("helpMessage") HelpMessage helpMessage, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "topic";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        topicService.addComment(commentInputDto, ((User) principal).getUsername(), id);
        return "redirect:/topic/" + id;
    }
}
