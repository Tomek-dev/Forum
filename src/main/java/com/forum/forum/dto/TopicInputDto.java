package com.forum.forum.dto;

import com.forum.forum.other.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicInputDto {

    @NotBlank
    private Type type;

    @Size(max = 96)
    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
