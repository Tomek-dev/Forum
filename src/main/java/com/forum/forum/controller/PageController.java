package com.forum.forum.controller;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.dto.ResetDto;
import com.forum.forum.model.HelpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class PageController {
    private HelpMessageDao helpMessageDao;

    @Autowired
    public PageController(HelpMessageDao helpMessageDao) {
        this.helpMessageDao = helpMessageDao;
    }

    @GetMapping("/home")
    public String getHome(){
        return "index";
    }

    @PostMapping("/help")
    public String help(@Valid @ModelAttribute HelpMessage helpMessage, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "fragments/elements :: footer-help";
        }
        helpMessageDao.save(helpMessage);
        return "/";
    }
}
