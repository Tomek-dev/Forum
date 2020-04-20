package com.forum.forum.dto;

import com.forum.forum.model.Comment;
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
public class TopicProfileDto {

    private String userUsername;
    private String createdAt;
    private String title;
    private Integer comments;
    private Integer votes;
    private Long id;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH).format(createdAt);
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments.size();
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes.size();
    }
}
