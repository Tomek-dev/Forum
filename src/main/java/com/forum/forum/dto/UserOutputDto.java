package com.forum.forum.dto;

import com.forum.forum.model.Comment;
import com.forum.forum.model.Star;
import com.forum.forum.model.Topic;
import com.forum.forum.model.Vote;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

public class UserOutputDto {

    private String username;
    private String createdAt;
    private int topics;
    private int comments;
    private int votes;
    private int stars;
    private String motto;

    public UserOutputDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH).format(createdAt);
    }

    public int getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics.size();
    }

    public int getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments.size();
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes.size();
    }

    public int getStars() {
        return stars;
    }

    public void setStars(Set<Star> stars) {
        this.stars = stars.size();
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }
}
