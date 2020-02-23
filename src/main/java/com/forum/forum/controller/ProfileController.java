package com.forum.forum.controller;

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
    private HelpMessage helpMessage;
    private UserService userService;

    @Autowired
    public ProfileController(TopicService topicService, HelpMessage helpMessage, UserService userService) {
        this.topicService = topicService;
        this.helpMessage = helpMessage;
        this.userService = userService;
    }

    @GetMapping("/profile/{user}")
    public String getProfile(@PathVariable("user") String user, @RequestParam(required = false) String value, @RequestParam(required = false) Long id, Model model){
        model.addAttribute("user", user);
        model.addAttribute("userOutputDto", userService.getUserByUserName(user));
        model.addAttribute("helpMessage", new HelpMessage());
        model.addAttribute("pageId", (id == null? 1: id));
        model.addAttribute("valueParam", value);
        if(value==null){
            return "/profile/" + user + "?value=topic";
        }
        if(value.equals("topic")){
            model.addAttribute("topics", (id == null ? topicService.getLast15TopicsByUsername(user): topicService.getTopicByPageAndUsername(id, user)));
            model.addAttribute("pageListSize", topicService.getPageListSizeByUsername(user));
            return "profile";
        }
        if(value.equals("comment")){
            model.addAttribute("comments", (id == null ? topicService.getLast15CommentedTopicsByUsername(user) : topicService.getLast15CommentedTopicsByUsernameAndPage(id, user)));
            model.addAttribute("pageListSize", topicService.getPageListSizeofCommentedTopicsByUsername(user));
            return "profile";
        }
        /*if(value.equals("vote")) {
            model.addAttribute("value", (id == null ? voteService.getLast15ByUsername() : voteService.getLast15ByPageAndUsername()));
            return "profile";
        }*/
        throw new RuntimeException("Value not found");
    }
}
