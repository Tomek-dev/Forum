package com.forum.forum.service;

import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.CommentInputDto;
import com.forum.forum.dto.CommentOutputDto;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.DateFormater;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private TopicDao topicDao;
    private CommentDao commentDao;
    private UserDao userDao;
    //private ModelMapper mapper = new ModelMapper();

    @Autowired
    public CommentService(TopicDao topicDao, CommentDao commentDao, UserDao userDao) {
        this.topicDao = topicDao;
        this.commentDao = commentDao;
        this.userDao = userDao;
    }

    public void addComment(CommentInputDto commentInputDto, String username, Long id){
        User user = userDao.findByUsername(username);
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic topic = topicOptional.orElseThrow(() -> new RuntimeException("Topic doesn't exist"));
        Comment comment = new Comment(commentInputDto.getComment());
        comment.setTopic(topic);
        comment.setUser(user);
        user.getComments().add(comment);
        topic.getComments().add(comment);
        commentDao.save(comment);
        userDao.save(user);
        topicDao.save(topic);
    }

    public List<CommentOutputDto> getComments(Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic topic = topicOptional.orElseThrow(()-> new RuntimeException("Topic doesn't exist"));
        //TypeMap<Comment, CommentOutputDto> typeMap = mapper.createTypeMap(Comment.class, CommentOutputDto.class);
        //typeMap.addMappings(map -> map.map(src -> DateFormater.posted(src.getCreatedAt()), CommentOutputDto::setPostedAt));
        return topic.getComments().stream()
                .sorted(Comparator.comparing(Comment::getCreatedAt))//to change
                //.map(typeMap::map)
                .map(comment -> new CommentOutputDto(comment.getComment(), comment.getUser().getUsername(), DateFormater.posted(comment.getCreatedAt()), comment.getId()))
                .collect(Collectors.toList());
    }

    public void deleteComment(Long id){
        commentDao.deleteById(id);
    }
}
