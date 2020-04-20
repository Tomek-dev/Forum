package com.forum.forum.dto;

import com.forum.forum.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicInputDto {

    @NotNull
    @NotEmpty
    private Type type;

    @Size(max = 96)
    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String description;
}
