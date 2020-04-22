package com.forum.forum.other.builder;

import com.forum.forum.model.Comment;
import com.forum.forum.model.Like;
import com.forum.forum.model.User;

public class LikeBuilder {

    private Like like = new Like();

    public static LikeBuilder builder(){
        return new LikeBuilder();
    }

    public LikeBuilder comment(Comment comment){
        like.setComment(comment);
        return this;
    }

    public LikeBuilder user(User user){
        like.setUser(user);
        return this;
    }

    public LikeBuilder like(Boolean like) {
        this.like.setType(like);
        return this;
    }

    public Like build(){
        return like;
    }
}
