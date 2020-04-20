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
public class HelpMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    @Size(max = 510)
    @Column(columnDefinition = "VARCHAR", length = 510)
    private String description;

    private LocalDateTime createdAt;

    public static class Builder{
        private String subject;
        private String description;

        public Builder subject(String subject){
            this.subject = subject;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public HelpMessage build(){
            HelpMessage message = new HelpMessage();
            message.description = this.description;
            message.subject = this.subject;
            message.createdAt = LocalDateTime.now();
            return message;
        }
    }
}
