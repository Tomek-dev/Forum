package com.forum.forum.controller;

import com.forum.forum.dto.SearchDto;
import com.forum.forum.dto.TopicOutputDto;
import com.forum.forum.other.SearchSpecification;
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
    public List<TopicOutputDto> getSearch(SearchSpecification searchSpecification, Model model, Pageable pageable){
        model.addAttribute("result", searchSerivce.getPageBySearch(searchSpecification, pageable));
        return searchSerivce.getPageBySearch(searchSpecification, pageable);
    }

    @PostMapping
    public String search(SearchDto searchDto){
        return "redirect:/search"+ URISearchMapper.map(searchDto);
    }
}
