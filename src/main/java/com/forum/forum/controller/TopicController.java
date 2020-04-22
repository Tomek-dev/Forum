package com.forum.forum.controller;

import com.forum.forum.dto.CommentInputDto;
import com.forum.forum.dto.ReportInputDto;
import com.forum.forum.dto.SearchDto;
import com.forum.forum.dto.TopicInputDto;
import com.forum.forum.model.Report;
import com.forum.forum.model.User;
import com.forum.forum.service.CommentService;
import com.forum.forum.service.LikeService;
import com.forum.forum.service.ReportService;
import com.forum.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private LikeService likeService;

    @Autowired
    public TopicController(ReportService reportService, TopicService topicService,
                           CommentService commentService, LikeService likeService) {
        this.reportService = reportService;
        this.topicService = topicService;
        this.commentService = commentService;
        this.likeService = likeService;
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
    public String addComment(@PathVariable Long id, @Valid CommentInputDto commentInputDto, @AuthenticationPrincipal User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:/topic/"+id;
        }

        commentService.addComment(commentInputDto, user, id);
        return "redirect:/topic/" + id;
    }

    @PostMapping("/topic/{topicId}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Long topicId, @PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/topic/"+topicId;
    }

    @PostMapping("/topic/{topicId}/like/{commentId}")
    public String likeComment(@PathVariable Long topicId, @PathVariable Long commentId,
                              @RequestParam Boolean type, @AuthenticationPrincipal User user){
        likeService.like(user, commentId, type);
        return "redirect:/topic/"+topicId;
    }

    @PostMapping("/topic/{id}/delete")
    public String deleteTopic(@PathVariable Long id){
        topicService.deleteTopic(id);
        return "redirect:/";
    }

    @GetMapping("topic/{id}/report")
    public String getReportProfile(@PathVariable Long id, Model model){
        model.addAttribute("report", new ReportInputDto());
        model.addAttribute("idVariable", id);
        return "report";
    }

    @PostMapping("/topic/{topicId}/report")
    public String reportTopic(@PathVariable Long topicId, @Valid ReportInputDto reportDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("idVariable", topicId);
            return "report";
        }
        reportService.addReport(reportDto, topicId);
        return "redirect:/topic/"+topicId;
    }
}
