package com.forum.forum.controller;

import com.forum.forum.dto.CommentInputDto;
import com.forum.forum.dto.SearchDto;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.model.Report;
import com.forum.forum.model.User;
import com.forum.forum.service.CommentService;
import com.forum.forum.service.ReportService;
import com.forum.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("search", new SearchDto());
        model.addAttribute("topic", new TopicInputDto());
        return "write";
    }

    @PostMapping("/write")
    public String writeTopic(@Valid TopicInputDto topicInputDto, Authentication authentication, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "write";
        }
        topicService.addTopic(topicInputDto, authentication.getName());
        return "redirect:/";
    }

    @GetMapping("/topic/{id}")
    public String getTopic(@PathVariable Long id, Model model){
        model.addAttribute("search", new SearchDto());
        model.addAttribute("topic", topicService.getTopic(id));
        model.addAttribute("comments", commentService.getComments(id));
        model.addAttribute("commentInput", new CommentInputDto());
        model.addAttribute("idVariable", id);
        return "topic";
    }

    @PostMapping("/topic/{id}/comment")
    public String addComment(@PathVariable Long id, @Valid CommentInputDto commentInputDto, Authentication authentication, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:/topic/"+id;
        }

        commentService.addComment(commentInputDto, authentication.getName(), id);
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
