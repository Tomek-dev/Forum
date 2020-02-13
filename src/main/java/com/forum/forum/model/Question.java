package com.forum.forum.model;

import com.forum.forum.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 96)
    @NotNull
    @NotEmpty
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotEmpty
    @NotNull
    private String description;

    @ManyToOne
    private User user;

    private Date createdAt;

    private Type type;

    public Question(@Size(max = 96) @NotEmpty @NotNull String title, @NotEmpty @NotNull String description) {
        this.title = title;
        this.description = description;
        createdAt = createdAtDate();
    }

    public Question() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
