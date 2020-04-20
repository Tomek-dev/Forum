package com.forum.forum.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
public class HelpMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    private String subject;

    @NotEmpty
    @NotNull
    @Size(max = 510)
    @Column(columnDefinition = "VARCHAR", length = 510)
    private String description;

    private LocalDateTime createdAt;

    public HelpMessage(@NotEmpty @NotNull String subject, @NotEmpty @NotNull @Size(max = 510) String description) {
        this.subject = subject;
        this.description = description;
        createdAt = LocalDateTime.now();
    }

    public HelpMessage() {
        createdAt = LocalDateTime.now();
    }
}
