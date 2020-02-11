package com.forum.forum.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 96)
    @NonNull
    @NotEmpty
    private String title;

    @NonNull
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String description;

    private String videoUrl;

    @ManyToOne
    private User user;

    private Date createAt;

    public Topic(@Size(max = 96) @NotEmpty String title, @NotEmpty String description) {
        this.title = title;
        this.description = description;
        createAt = createdAtDate();
    }

    public Topic() {
        createAt = createdAtDate();
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
