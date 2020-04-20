package com.forum.forum.other;

import com.forum.forum.dto.SearchDto;
import com.forum.forum.other.specification.SearchSpecification;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class URISearchMapper {

    public static String map(SearchDto searchDto){
        String uri = "";
        if(searchDto.getQuery() != null){
            uri += "&query=" + URLEncoder.encode(searchDto.getQuery(), StandardCharsets.UTF_8);
        }
        if(searchDto.getType() != null){
            uri += "&type=" + URLEncoder.encode(searchDto.getType().getDisplayName().toLowerCase(), StandardCharsets.UTF_8);
        }
        if(searchDto.getText() !=null){
            uri += "&text="+searchDto.getText();
        }
        return "?" + uri.substring(1);
    }

    public static String map(SearchSpecification searchSpecification){
        String uri = "";
        if(searchSpecification.getQuery() != null){
            uri += URLEncoder.encode(searchSpecification.getQuery(), StandardCharsets.UTF_8);
        }
        if(searchSpecification.getType() != null){
            uri += "&type=" + URLEncoder.encode(searchSpecification.getType().toLowerCase(), StandardCharsets.UTF_8);
        }
        if(searchSpecification.getText() != null){
            uri += "&text=" + searchSpecification.getText();
        }
        return uri;
    }
}
