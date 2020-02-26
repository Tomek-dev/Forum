package com.forum.forum.controller;

import com.forum.forum.model.Report;
import com.forum.forum.service.ReportService;
import com.forum.forum.service.TopicService;
import com.forum.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private TopicService topicService;
    private UserService userService;
    private ReportService reportService;

    @Autowired
    public ProfileController(TopicService topicService, UserService userService, ReportService reportService) {
        this.topicService = topicService;
        this.userService = userService;
        this.reportService = reportService;
    }

    @GetMapping("/{user}")
    public String getProfile(@PathVariable("user") String user, @RequestParam(required = false) String value, @RequestParam(required = false) Integer id, Model model){
        model.addAttribute("userVariable", user);
        model.addAttribute("userOutputDto", userService.getUserByUsername(user));
        model.addAttribute("pageId", (id == null? 1: id));
        model.addAttribute("valueParam", value);
        if(value==null){
            return "redirect:/profile/" + user + "?value=topic";
        }
        if(value.equals("topic")){
            model.addAttribute("topics", (id == null ? topicService.getPageOf15TopicsByUser(user, 0): topicService.getPageOf15TopicsByUser(user, id-1)));
            model.addAttribute("pageListSize", topicService.getPageSizeByUsername(user));
            return "profile";
        }
        if(value.equals("comment")){
            model.addAttribute("topics", (id == null ? topicService.getPageOf15TopicsByComment(user, 0) : topicService.getPageOf15TopicsByComment(user, id-1)));
            model.addAttribute("pageListSize", topicService.getPageSizeByComment(user));
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
    public String reportProfile(@PathVariable String username, @Valid Report report, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("userVariable", username);
            return "report";
        }
        reportService.addReport(report, username);
        return"redirect:/profile/" + username;
    }

    @PostMapping("/{username}/delete")
    public String deleteProfile(@PathVariable String username){
        userService.deleteUser(username);
        return "redirect:/";
    }
}
