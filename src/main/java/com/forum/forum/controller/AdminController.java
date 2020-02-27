package com.forum.forum.controller;


import com.forum.forum.service.HelpService;
import com.forum.forum.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ReportService reportService;
    private HelpService helpService;

    @Autowired
    public AdminController(ReportService reportService, HelpService helpService) {
        this.reportService = reportService;
        this.helpService = helpService;
    }

    @GetMapping("/reports")
    public String getReport(@RequestParam(required = false) Integer page, Model model){
        model.addAttribute("report", reportService.getPageOf15Report((page == null? 0: page-1)));
        return "";
    }

    @GetMapping("/help-messages")
    public String getHelpMessages(@RequestParam(required = false) Integer page, Model model){
        model.addAttribute("report", helpService.getPageOfHelpMessage((page == null? 0: page-1)));
        return "";
    }
}
