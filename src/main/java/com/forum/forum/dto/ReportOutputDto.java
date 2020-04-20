package com.forum.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportOutputDto {

    private String name;
    private Long id;
    private String type;
    private String createdAt;
    private String describe;
}
