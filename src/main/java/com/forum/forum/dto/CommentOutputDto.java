package com.forum.forum.dto;

import com.forum.forum.model.Star;
import com.forum.forum.other.DateFormater;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class CommentOutputDto {

    private String comment;
    private String userUsername;
    private String createdAt;
    private Long id;
    private int stars;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = DateFormater.posted(createdAt);
    }

    public void setStars(Set<Star> stars) {
        this.stars = stars.size();
    }
}
