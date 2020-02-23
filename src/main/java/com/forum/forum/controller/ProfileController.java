package com.forum.forum.controller;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.model.HelpMessage;
import com.forum.forum.service.TopicService;
import com.forum.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    //TODO write all tests, add urls to front, findbyusername ignore case

    private TopicService topicService;
    private HelpMessageDao helpMessageDao;
    private UserService userService;

    @Autowired
    public ProfileController(TopicService topicService, HelpMessageDao helpMessageDao, UserService userService) {
        this.topicService = topicService;
        this.helpMessageDao = helpMessageDao;
        this.userService = userService;
    }

    @GetMapping("/profile/{user}")
    public String getProfile(@PathVariable("user") String user, @RequestParam(required = false) String value, @RequestParam(required = false) Long id, Model model){
        model.addAttribute("username", user);
        model.addAttribute("userOutputDto", userService.getUserByUserName(user));
        model.addAttribute("helpMessage", new HelpMessage());
        model.addAttribute("pageId", (id == null? 1: id));
        model.addAttribute("valueParam", value);
        if(value==null){
            return "/profile/" + user + "?value=topic";
        }
        if(value.equals("topic")){
            model.addAttribute("topics", (id == null ? topicService.getLast15TopicsByUsername(user): topicService.get15TopicsByPageAndUsername(id, user)));
            model.addAttribute("pageListSize", topicService.getPageListSizeOfTopicsByUsername(user));
            return "profile";
        }
        if(value.equals("comment")){
            model.addAttribute("comments", (id == null ? topicService.getLast15CommentedTopicsByUsername(user) : topicService.get15CommentedTopicsByUsernameAndPage(id, user)));
            model.addAttribute("pageListSize", topicService.getPageListSizeOfCommentedTopicsByUsername(user));
            return "profile";
        }
        throw new RuntimeException("Value not found");
    }
}
