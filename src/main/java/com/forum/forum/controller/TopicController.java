package com.forum.forum.controller;

import com.forum.forum.dto.CommentInputDto;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.model.Report;
import com.forum.forum.model.User;
import com.forum.forum.service.CommentService;
import com.forum.forum.service.ReportService;
import com.forum.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TopicController {

    private ReportService reportService;
    private TopicService topicService;
    private CommentService commentService;

    @Autowired
    public TopicController(ReportService reportService, TopicService topicService, CommentService commentService) {
        this.reportService = reportService;
        this.topicService = topicService;
        this.commentService = commentService;
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

    @PostMapping("/topic/{topicId}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Long topicId, @PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/topic/"+topicId;
    }

    @PostMapping("/topic/{id}/delete")
    public String deleteTopic(@PathVariable Long id){
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
