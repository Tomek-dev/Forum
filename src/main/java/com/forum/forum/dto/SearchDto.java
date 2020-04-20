package com.forum.forum.dto;

import com.forum.forum.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {

    private String query;

    private Boolean text;

    private Type type;
}
