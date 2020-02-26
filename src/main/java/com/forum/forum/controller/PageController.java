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
    private ReportService reportService;
    private CommentService commentService;

    @Autowired
    public PageController(TopicService topicService, HelpService helpService, ReportService reportService, CommentService commentService) {
        this.topicService = topicService;
        this.helpService = helpService;
        this.reportService = reportService;
        this.commentService = commentService;
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

    @GetMapping("/write")
    public String getWriteTopic(Model model){
        model.addAttribute("topic", new TopicInputDto());
        return "write";
    }

    @PostMapping("/write")
    public String writeTopic(@Valid TopicInputDto topicInputDto, BindingResult bindingResult){
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
        model.addAttribute("idVariable", id);
        return "topic";
    }

    @PostMapping("/topic/{id}/comment")
    public String addComment(@PathVariable Long id, @Valid CommentInputDto commentInputDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:/topic/"+id;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentService.addComment(commentInputDto, ((User) principal).getUsername(), id);
        return "redirect:/topic/" + id;
    }

    @PostMapping("/topic/{id}/delete")
    public String deleteComment(@PathVariable Long id){
        topicService.deleteTopic(id);
        return "redirect:/";
    }

    @GetMapping("/topic/{topicId}/report")
    public String getReportTopic(@PathVariable Long topicId, Model model){
        model.addAttribute("idVariable", topicId);
        model.addAttribute("report", new Report());
        return "report";
    }

    @PostMapping("/topic/{topicId}/report")
    public String reportTopic(@PathVariable Long topicId, @Valid Report report, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("idVariable", topicId);
            return "report";
        }
        reportService.addReport(report, topicId);
        return "redirect:/topic/"+topicId;
    }
}
