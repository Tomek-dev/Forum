package com.forum.forum.controller;

import com.forum.forum.dto.SearchDto;
import com.forum.forum.model.HelpMessage;
import com.forum.forum.other.specification.TypeSpecification;
import com.forum.forum.service.HelpService;
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
import java.security.Principal;

@Controller
public class PageController {

    private TopicService topicService;
    private HelpService helpService;
    private UserService userService;

    @Autowired
    public PageController(TopicService topicService, HelpService helpService, UserService userService) {
        this.topicService = topicService;
        this.helpService = helpService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHome(Model model, Principal principal){
        Pageable pageable = PageRequest.of(1, 15, Sort.by("id").descending());
        model.addAttribute("search", new SearchDto());
        model.addAttribute("topics", topicService.getPageOf15Topics(pageable));
        model.addAttribute("pageListSize", topicService.getPageNumber(pageable));
        model.addAttribute("userInfo", (principal == null? "null": userService.getInfo(principal.getName())));
        model.addAttribute("pageId", 1);
        return "index";
    }

    @GetMapping("/topic")
    public String getHomeWithType(TypeSpecification typeSpecification, @PageableDefault(sort = "id", size = 15, page = 1, direction = Sort.Direction.DESC) Pageable pageable, Model model, Principal principal){
        model.addAttribute("search", new SearchDto());
        model.addAttribute("typeEnum", typeSpecification.getType());
        model.addAttribute("topics", (typeSpecification.getType() == null? topicService.getPageOf15Topics(pageable): topicService.getPageOf15Topics(typeSpecification, pageable)));
        model.addAttribute("pageListSize", topicService.getPageNumber(typeSpecification, pageable));
        model.addAttribute("pageId", pageable.getPageNumber());
        model.addAttribute("userInfo", (principal == null? "null": userService.getInfo(principal.getName())));
        return "index";
    }

    @GetMapping("/help")
    public String getHelp(Model model){
        model.addAttribute("helpMessage", new HelpMessage());
        return "help";
    }

    @PostMapping("/help")
    public String help(@Valid @ModelAttribute HelpMessage helpMessage, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "help";
        }
        helpService.addHelpMessage(helpMessage);
        return "redirect:/";
    }

}
