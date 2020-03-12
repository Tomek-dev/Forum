package com.forum.forum.controller;

import com.forum.forum.dto.SearchDto;
import com.forum.forum.other.specification.SearchSpecification;
import com.forum.forum.other.URISearchMapper;
import com.forum.forum.service.SearchSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/search")
public class SearchController {

    private SearchSerivce searchSerivce;

    @Autowired
    public SearchController(SearchSerivce searchSerivce) {
        this.searchSerivce = searchSerivce;
    }

    @GetMapping
    public String getSearch(SearchSpecification searchSpecification, Model model,@PageableDefault(sort = "id", size = 10, page = 1, direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("search", new SearchDto());
        model.addAttribute("topics", searchSerivce.getPageBySearch(searchSpecification, (pageable == null? PageRequest.of(1, 10, Sort.by("id").descending()): pageable)));
        model.addAttribute("pageListSize", searchSerivce.getPageNumber(searchSpecification, (pageable == null? PageRequest.of(1, 10, Sort.by("id").descending()): pageable)));
        model.addAttribute("pageId", (pageable == null? 1 : pageable.getPageNumber()));
        model.addAttribute("currentURI", URISearchMapper.map(searchSpecification));
        model.addAttribute("test", null);
        return "search";
    }

    @PostMapping
    public String search(SearchDto searchDto){
        return "redirect:/search"+ URISearchMapper.map(searchDto);
    }
}
