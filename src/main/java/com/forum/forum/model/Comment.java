package com.forum.forum.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @OneToMany(mappedBy = "comment", orphanRemoval = true)
    Set<Like> likes = new HashSet<>();

    private LocalDateTime createdAt;

    public static class Builder{
        private User user;
        private Topic topic;
        private String comment;

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public Builder topic(Topic topic){
            this.topic = topic;
            return this;
        }

        public Builder comment(String comment){
            this.comment = comment;
            return this;
        }

        public Comment build(){
            Comment comment = new Comment();
            comment.comment = this.comment;
            comment.topic = this.topic;
            comment.user = this.user;
            comment.createdAt = LocalDateTime.now();
            return comment;
        }
    }
}
