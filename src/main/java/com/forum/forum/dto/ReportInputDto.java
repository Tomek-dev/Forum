package com.forum.forum.dto;

import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.enums.ReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportInputDto {

    @NotBlank
    private String type;

    @NotBlank
    private String describe;
}
