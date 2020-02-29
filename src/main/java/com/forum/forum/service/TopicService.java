package com.forum.forum.service;

import com.forum.forum.enums.Type;
import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.*;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import com.forum.forum.other.DateFormater;
import com.forum.forum.other.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopicService{

    private TopicDao topicDao;
    private UserDao userDao;
    private CommentDao commentDao;
    private SimpleDateFormat simpleDateFormat;

    @Autowired
    public TopicService(TopicDao topicDao, UserDao userDao, CommentDao commentDao) {
        this.topicDao = topicDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
        simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
    }

    public void addTopic(TopicInputDto topicInputDto, String username){
        User user =  userDao.findByUsername(username);
        Topic topic = new Topic(topicInputDto.getTitle(), topicInputDto.getDescription());
        topic.setUser(user);
        topic.setType(topicInputDto.getType());
        user.getTopics().add(topic);
        userDao.save(user);
        topicDao.save(topic);
    }

    public void deleteTopic(Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic topic = topicOptional.orElseThrow(() -> new RuntimeException("Type doesn't exist"));
        User user = topic.getUser();
        user.getTopics().remove(topic);
        Set<User> commentUserList = new HashSet<>();
        Set<Comment> commentList = topic.getComments();
        topic.getComments()
                .forEach(comment -> {
                    User commentUser = comment.getUser();
                    commentUser.getComments().remove(comment);
                    commentUserList.add(commentUser);
                });
        topicDao.delete(topic);
        commentDao.deleteAll(commentList);
        userDao.saveAll(commentUserList);
        userDao.save(user);
    }

    public List<TopicOutputDto> getPageOf15TopicsByType(String type, int page){
        Optional<Type> typeOptional = Type.fromValue(type);
        Type foundType = typeOptional.orElseThrow(() -> new RuntimeException("Type doesn't exist"));
        long count = topicDao.countByType(foundType);
        if(page < 0 || page> Math.ceil((double) count/15)){
            throw new IndexOutOfBoundsException("Page index out of bounds");
        }
        return topicDao.findByType(PageRequest.of(page, 15, Sort.by("id").descending()), foundType).stream()
                .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), DateFormater.posted(topic.getCreatedAt()), topic.getId()))
                .collect(Collectors.toList());
    }

    public List<TopicOutputDto> getPageOf15Topics(int page){
        long count = topicDao.count();
        if(page < 0 || page> Math.ceil((double) count/15)){
            throw new IndexOutOfBoundsException("Page index out of bounds");
        }
        return topicDao.findAll(PageRequest.of(page, 15, Sort.by("id").descending())).stream()
                .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), DateFormater.posted(topic.getCreatedAt()), topic.getId()))
                .collect(Collectors.toList());
    }

    public List<TopicProfileDto> getPageOf15TopicsByUser(String username, int page){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));
        long count = topicDao.countByUser(user);
        if(page < 0 || page> Math.ceil((double) count/15)){
            throw new IndexOutOfBoundsException("Page index out of bounds");
        }
        return topicDao.findByUser(PageRequest.of(page, 15, Sort.by("id").descending()), user).stream()
                .map(topic -> new TopicProfileDto(simpleDateFormat.format(topic.getCreatedAt()),topic.getTitle(), topic.getComments().size(),topic.getId()))
                .collect(Collectors.toList());
    }

    public List<TopicProfileDto> getPageOf15TopicsByComment(String username, int page){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));
        long count = user.getComments().size();
        if(page < 0 || page> Math.ceil((double) count/15)){
            throw new IndexOutOfBoundsException("Page index out of bounds");
        }
        return topicDao.findByCommentsIn(PageRequest.of(page, 15, Sort.by("id").descending()), user.getComments()).stream()
                .map(topic -> new TopicProfileDto(topic.getUser().getUsername(), simpleDateFormat.format(topic.getCreatedAt()),topic.getTitle(), topic.getComments().size(),topic.getId()))
                .collect(Collectors.toList());
    }

    public TopicOutputDto getTopic(Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic foundTopic = topicOptional.orElseThrow(()-> new RuntimeException("Topic doesn't exist"));
        return new TopicOutputDto(foundTopic.getUser().getUsername(), foundTopic.getTitle(), foundTopic.getDescription(), foundTopic.getType().getDisplayName(), DateFormater.posted(foundTopic.getCreatedAt()), foundTopic.getId());
    }

    public List<CommentOutputDto> getComments(Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic topic = topicOptional.orElseThrow(()-> new RuntimeException("Topic doesn't exist"));
        return topic.getComments().stream()
                .sorted(Comparator.comparing(Comment::getCreatedAt))
                .map(comment -> new CommentOutputDto(comment.getComment(), comment.getUser().getUsername(), DateFormater.posted(comment.getCreatedAt()), comment.getId()))
                .collect(Collectors.toList());
    }

    public long getPageSize(){
        return (long) Math.ceil((double) topicDao.count()/15);
    }

    public long getPageSizeByType(String type){
        Optional<Type> typeOptional = Type.fromValue(type);
        Type foundType = typeOptional.orElseThrow(() -> new RuntimeException("Type doesn't exist"));
        return (long) Math.ceil((double) topicDao.countByType(foundType)/15);
    }

    public long getPageSizeByUsername(String username){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return (long) Math.ceil((double) user.getTopics().size()/15);
    }

    public long getPageSizeByComment(String username){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return (long) Math.ceil((double) user.getComments().size()/15);
    }

}
