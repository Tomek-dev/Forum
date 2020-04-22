package com.forum.forum.service;

import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.LikeDao;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Like;
import com.forum.forum.model.User;
import com.forum.forum.other.builder.LikeBuilder;
import com.forum.forum.other.exceptions.CommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    private LikeDao likeDao;
    private CommentDao commentDao;

    @Autowired
    public LikeService(LikeDao likeDao, CommentDao commentDao) {
        this.likeDao = likeDao;
        this.commentDao = commentDao;
    }

    public void like(User user, Long id, Boolean type){
        Optional<Like> likeOptional = likeDao.findByUserAndCommentId(user, id);
        if(likeOptional.isEmpty()){
            Optional<Comment> commentOptional = commentDao.findById(id);
            Comment comment = commentOptional.orElseThrow(CommentNotFoundException::new);
            Like like = LikeBuilder.builder()
                    .user(user)
                    .comment(comment)
                    .like(type)
                    .build();
            likeDao.save(like);
            return;
        }
        Like like = likeOptional.get();
        if(like.getType().equals(type)){
            likeDao.delete(like);
            return;
        }
        like.setType(type);
        likeDao.save(like);
    }
}
