package com.forum.forum.model;

import com.forum.forum.enums.Type;

import javax.persistence.*;
import java.util.*;

@Entity
public class Topic{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String videoUrl;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    private Date createdAt;

    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    private Set<Report> report = new HashSet<>();

    private Type type;

    public Topic(String title, String description) {
        this.title = title;
        this.description = description;
        createdAt = createdAtDate();
    }

    public Topic() {
        createdAt = createdAtDate();
    }

    private Date createdAtDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        return new Date(calendar.getTime().getTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createAt) {
        this.createdAt = createAt;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Report> getReport() {
        return report;
    }

    public void setReport(Set<Report> report) {
        this.report = report;
    }
}
