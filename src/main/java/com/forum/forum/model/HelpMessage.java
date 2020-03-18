package com.forum.forum.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
