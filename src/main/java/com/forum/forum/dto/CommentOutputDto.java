package com.forum.forum.dto;

import com.forum.forum.model.Like;
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
    private int userStars;
    private Long[] likes;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = DateFormater.posted(createdAt);
    }

    public void setUserStars(Set<Star> stars) {
        this.userStars = stars.size();
    }

    public void setLikes(Set<Like> likes) {
        Long[] count = new Long[2];
        count[0] = likes.stream().filter(like -> like.getType().equals(true)).count();
        count[1] = likes.stream().filter(like -> like.getType().equals(false)).count();
        this.likes = count;
    }
}
