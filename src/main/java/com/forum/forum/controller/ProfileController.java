package com.forum.forum.controller;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.dto.TopicProfileDto;
import com.forum.forum.enums.ReportType;
import com.forum.forum.model.HelpMessage;
import com.forum.forum.model.Report;
import com.forum.forum.service.ReportService;
import com.forum.forum.service.ResetService;
import com.forum.forum.service.TopicService;
import com.forum.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private TopicService topicService;
    private HelpMessageDao helpMessageDao;
    private UserService userService;
    private ReportService reportService;

    @Autowired
    public ProfileController(TopicService topicService, HelpMessageDao helpMessageDao, UserService userService, ReportService reportService) {
        this.topicService = topicService;
        this.helpMessageDao = helpMessageDao;
        this.userService = userService;
        this.reportService = reportService;
    }

    @GetMapping("/{user}")
    public String getProfile(@PathVariable("user") String user, @RequestParam(required = false) String value, @RequestParam(required = false) Long id, Model model){
        model.addAttribute("userVariable", user);
        model.addAttribute("userOutputDto", userService.getUserByUserName(user));
        model.addAttribute("helpMessage", new HelpMessage());
        model.addAttribute("pageId", (id == null? 1: id));
        model.addAttribute("valueParam", value);
        if(value==null){
            return "redirect:/profile/" + user + "?value=topic";
        }
        if(value.equals("topic")){
            model.addAttribute("topics", (id == null ? topicService.getLast15TopicsByUsername(user): topicService.get15TopicsByPageAndUsername(id, user)));
            model.addAttribute("pageListSize", topicService.getPageListSizeOfTopicsByUsername(user));
            return "profile";
        }
        if(value.equals("comment")){
            model.addAttribute("topics", (id == null ? topicService.getLast15CommentedTopicsByUsername(user) : topicService.get15CommentedTopicsByUsernameAndPage(id, user)));
            model.addAttribute("pageListSize", topicService.getPageListSizeOfCommentedTopicsByUsername(user));
            return "profile";
        }
        throw new RuntimeException("Value not found");
    }

    @GetMapping("/{username}/report")
    public String getReportProfile(@PathVariable String username,Model model){
        model.addAttribute("report", new Report());
        model.addAttribute("userVariable", username);
        return "report";
    }

    @PostMapping("/{username}/report")
    public String reportProfile(@PathVariable String username, @Valid Report report, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "report";
        }
        reportService.addReport(report, username);
        return"redirect:/profile/" + username;
    }
}
