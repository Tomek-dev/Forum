package com.forum.forum.controller;


import com.forum.forum.dto.SearchDto;
import com.forum.forum.other.enums.Role;
import com.forum.forum.service.HelpService;
import com.forum.forum.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class AdminController {

    private ReportService reportService;
    private HelpService helpService;

    @Autowired
    public AdminController(ReportService reportService, HelpService helpService) {
        this.reportService = reportService;
        this.helpService = helpService;
    }

    @GetMapping("/admin")
    public String getReport(@PageableDefault(page = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model){
        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by("id").descending());
        model.addAttribute("search", new SearchDto());
        model.addAttribute("help", helpService.getPageOfHelpMessage((pageable == null? pageRequest: pageable)));
        model.addAttribute("pageListSize", helpService.getPageNumber(pageable == null? pageRequest: pageable));
        model.addAttribute("pageId", (pageable == null? 1 : pageable.getPageNumber()));
        return "admin";
    }
}
