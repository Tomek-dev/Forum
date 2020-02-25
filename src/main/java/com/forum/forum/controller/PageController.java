package com.forum.forum.controller;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.dto.CommentInputDto;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.model.HelpMessage;
import com.forum.forum.model.Report;
import com.forum.forum.model.User;
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
    private HelpMessageDao helpMessageDao;
    private ReportService reportService;

    @Autowired
    public PageController(TopicService topicService, HelpMessageDao helpMessageDao, ReportService reportService) {
        this.topicService = topicService;
        this.helpMessageDao = helpMessageDao;
        this.reportService = reportService;
    }

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("topics", topicService.getLast15Topics());
        model.addAttribute("helpMessage", new HelpMessage());
        model.addAttribute("pageListSize", topicService.getPageListSize());
        model.addAttribute("pageId", 1);
        return "index";
    }

    @GetMapping("/page")
    public String getHomeWithType(@RequestParam(required = false) String type, @RequestParam(required = false) Long id, Model model){
        model.addAttribute("helpMessage", new HelpMessage());
        if(id == null && type == null){
            return "redirect:/";
        }
        if(type != null && id != null){
            model.addAttribute("topics", topicService.get15TopicByPageAndType(type, id));
            model.addAttribute("pageListSize", topicService.getPageListSizeByType(type));
            model.addAttribute("pageId", id);
            model.addAttribute("typeEnum", type);
            return "index";
        }
        if(id == null){
            model.addAttribute("pageId", 1);
            model.addAttribute("typeEnum", type);
            model.addAttribute("pageListSize", topicService.getPageListSizeByType(type));
            model.addAttribute("topics", topicService.getLast15TopicsByType(type));
            return "index";
        }
        model.addAttribute("pageListSize", topicService.getPageListSize());
        model.addAttribute("topics", topicService.get15TopicByPage(id));
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
        model.addAttribute("idVariable", id);
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

    @GetMapping("/topic/{topicId}/report")
    public String getReportTopic(@PathVariable Long topicId, Model model){
        model.addAttribute("idVariable", topicId);
        model.addAttribute("report", new Report());
        return "report";
    }

    @PostMapping("/topic/{topicId}/report")
    public String reportTopic(@PathVariable Long topicId, @Valid Report report, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "report";
        }
        reportService.addReport(report, topicId);
        return "redirect:/topic/"+topicId;
    }
}
