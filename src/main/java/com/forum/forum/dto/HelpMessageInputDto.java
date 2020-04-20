package com.forum.forum.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class HelpMessageInputDto {

    @NotBlank
    private String subject;

    @NotBlank
    private String description;
}
