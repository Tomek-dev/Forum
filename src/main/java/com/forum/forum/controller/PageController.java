package com.forum.forum.controller;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.dto.CommentInputDto;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.model.HelpMessage;
import com.forum.forum.model.Report;
import com.forum.forum.model.User;
import com.forum.forum.service.CommentService;
import com.forum.forum.service.HelpService;
import com.forum.forum.service.ReportService;
import com.forum.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PageController {

    private TopicService topicService;
    private HelpService helpService;

    @Autowired
    public PageController(TopicService topicService, HelpService helpService) {
        this.topicService = topicService;
        this.helpService = helpService;
    }

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("topics", topicService.getPageOf15Topics(0));
        model.addAttribute("helpMessage", new HelpMessage());
        model.addAttribute("pageListSize", topicService.getPageSize());
        model.addAttribute("pageId", 1);
        return "index";
    }

    @GetMapping("/page")
    public String getHomeWithType(@RequestParam(required = false) String type, @RequestParam(required = false) Integer id, Model model){
        model.addAttribute("helpMessage", new HelpMessage());
        if(id == null && type == null){
            return "redirect:/";
        }
        if(type != null && id != null){
            model.addAttribute("topics", topicService.getPageOf15TopicsByType(type, id-1));
            model.addAttribute("pageListSize", topicService.getPageSizeByType(type));
            model.addAttribute("pageId", id);
            model.addAttribute("typeEnum", type);
            return "index";
        }
        if(id == null){
            model.addAttribute("pageId", 1);
            model.addAttribute("typeEnum", type);
            model.addAttribute("pageListSize", topicService.getPageSizeByType(type));
            model.addAttribute("topics", topicService.getPageOf15TopicsByType(type, 0));
            return "index";
        }
        model.addAttribute("pageListSize", topicService.getPageSize());
        model.addAttribute("topics", topicService.getPageOf15Topics(id-1));
        model.addAttribute("pageId", id);
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
