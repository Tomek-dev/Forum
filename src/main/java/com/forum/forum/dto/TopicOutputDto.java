package com.forum.forum.dto;

import com.forum.forum.model.Comment;
import com.forum.forum.model.Vote;
import com.forum.forum.other.DateFormater;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class TopicOutputDto {

    private String userUsername;
    private String title;
    private String description;
    private String type;
    private String createdAt;
    private Long id;
    private Integer comments;
    private Integer votes;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = DateFormater.posted(createdAt);
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments.size();
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes.size();
    }
}
