package com.forum.forum.other;

import com.forum.forum.dto.SearchDto;

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
        return "?" + (uri.startsWith("&")? uri.substring(1): uri);
    }
}
