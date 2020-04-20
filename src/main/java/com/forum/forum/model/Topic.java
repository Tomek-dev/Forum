package com.forum.forum.model;

import com.forum.forum.other.enums.Type;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
public class Topic{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    private Set<Vote> votes = new HashSet<>();

    private String videoUrl;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    private Set<Report> report = new HashSet<>();

    private Type type;

    public Topic() {
        createdAt = LocalDateTime.now();
    }
}
