package com.forum.forum.controller;

import com.forum.forum.dto.SearchDto;
import com.forum.forum.model.Report;
import com.forum.forum.other.ProfileSpecification;
import com.forum.forum.service.ReportService;
import com.forum.forum.service.TopicService;
import com.forum.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String getProfile(@PathVariable("user") String user, ProfileSpecification profileSpecification, @PageableDefault(sort = "id", page = 1, direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("userOutputDto", userService.getUserByUsername(user));
        model.addAttribute("search", new SearchDto());
        model.addAttribute("userVariable", user);
        model.addAttribute("pageId", (pageable == null? 1: pageable.getPageNumber()));
        model.addAttribute("valueParam", profileSpecification.getValue());
        profileSpecification.setUser(user);
        model.addAttribute("topics", topicService.getPageOfTopic(profileSpecification, (pageable == null? PageRequest.of(1, 10, Sort.by("id").descending()): pageable)));
        model.addAttribute("pageListSize", topicService.getProfilePageNumber(profileSpecification, (pageable == null? PageRequest.of(1, 10, Sort.by("id").descending()): pageable)));
        return "profile";
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
