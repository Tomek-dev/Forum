package com.forum.forum.dto;

import com.forum.forum.model.Comment;
import com.forum.forum.model.Star;
import com.forum.forum.model.Topic;
import com.forum.forum.model.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOutputDto {

    private String username;
    private String createdAt;
    private int topics;
    private int comments;
    private int votes;
    private int stars;
    private String motto;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH).format(createdAt);
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics.size();
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments.size();
    }


    public void setVotes(Set<Vote> votes) {
        this.votes = votes.size();
    }

    public void setStars(Set<Star> stars) {
        this.stars = stars.size();
    }
}
