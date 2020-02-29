package com.forum.forum.controller;

import com.forum.forum.dto.SearchDto;
import com.forum.forum.dto.TopicOutputDto;
import com.forum.forum.other.SearchFilter;
import com.forum.forum.other.URISearchMapper;
import com.forum.forum.service.SearchSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private SearchSerivce searchSerivce;

    @Autowired
    public SearchController(SearchSerivce searchSerivce) {
        this.searchSerivce = searchSerivce;
    }

    @GetMapping
    @ResponseBody
    public List<TopicOutputDto> getSearch(SearchFilter searchFilter, Model model, Pageable pageable){
        model.addAttribute("result", searchSerivce.getPageBySearch(searchFilter, pageable));
        return searchSerivce.getPageBySearch(searchFilter, pageable);
    }

    @PostMapping
    public String search(SearchDto searchDto){
        return "redirect:/search"+ URISearchMapper.map(searchDto);
    }
}
