package com.forum.forum.controller;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.dto.ResetDto;
import com.forum.forum.model.HelpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PageController {
    private HelpMessageDao helpMessageDao;

    @Autowired
    public PageController(HelpMessageDao helpMessageDao) {
        this.helpMessageDao = helpMessageDao;
    }

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("helpMessage", new HelpMessage());
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
}
