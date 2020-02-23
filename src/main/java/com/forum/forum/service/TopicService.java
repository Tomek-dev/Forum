package com.forum.forum.service;

import com.forum.forum.Type;
import com.forum.forum.dao.CommentDao;
import com.forum.forum.dao.TopicDao;
import com.forum.forum.dao.UserDao;
import com.forum.forum.dto.*;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<TopicOutputDto> getLast15TopicsByType(String type){
        Optional<Type> typeOptional = Arrays.stream(Type.values())
                .filter(typeEnum -> typeEnum.toString().toLowerCase().equals(type))
                .findFirst();
        Type foundType = typeOptional.orElseThrow(() -> new RuntimeException("Type doesn't exist"));
        return topicDao.findTop15ByTypeOrderByIdDesc(foundType).stream()
                .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), posted(topic.getCreatedAt()), topic.getId()))
                .collect(Collectors.toList());
    }

    public List<TopicOutputDto> getLast15Topics(){
        return topicDao.findTop15ByOrderByIdDesc().stream()
                .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), posted(topic.getCreatedAt()), topic.getId()))
                .collect(Collectors.toList());
    }

    public List<TopicOutputDto> get15TopicByPage(long page){
        final long count = topicDao.count();
        if(page < 1 || page> Math.ceil((double) count/15)){
            throw new IndexOutOfBoundsException("Page index out of bounds");
        }
        if(count <= 15){
            return getLast15Topics();
        }
        return topicDao.findByIdBetweenOrderByIdDesc((count-(page*15)+1 > 0 ? count-(page*15)+1: 1), count-((page-1)*15)).stream()
                .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), posted(topic.getCreatedAt()), topic.getId()))
                .collect(Collectors.toList());
    }

    //TODO not working
    public List<TopicOutputDto> get15TopicByPageAndType(String type, long page){
        Optional<Type> typeOptional = Arrays.stream(Type.values())
                .filter(typeElement -> typeElement.toString().toLowerCase().equals(type))
                .findFirst();
        Type foundType = typeOptional.orElseThrow(() -> new RuntimeException("Type doesn't exist"));
        final long count = topicDao.countByType(foundType);
        if (page < 1 || page > Math.ceil((double) count/15)){
            throw new IndexOutOfBoundsException("Page index out of bounds");
        }
        if(count < 15){
            return topicDao.findByTypeAndIdBetweenOrderByIdDesc(foundType, 1, count).stream()
                    .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), posted(topic.getCreatedAt()), topic.getId()))
                    .collect(Collectors.toList());
        }
        return topicDao.findByTypeAndIdBetweenOrderByIdDesc(foundType, (count-(page*15) > 0? count-(page*15) : 1), count-((page-1)*15)).stream()
                .map(topic -> new TopicOutputDto(topic.getUser().getUsername(), topic.getTitle(), topic.getDescription(), topic.getType().getDisplayName(), posted(topic.getCreatedAt()), topic.getId()))
                .collect(Collectors.toList());
    }

    public List<TopicProfileDto> getLast15TopicsByUsername(String username){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not authorized."));
        return user.getTopics().stream()
                .sorted(Comparator.comparing(Topic::getCreatedAt))
                .map(topic -> new TopicProfileDto(topic.getCreatedAt().toString(), topic.getTitle(), topic.getComments().size()))
                .collect(Collectors.toList());
    }

    //TODO not working
    public List<TopicProfileDto> get15TopicsByPageAndUsername(long page, String username) {
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not authorized."));
        final long count = topicDao.count();
        if (page < 1 || page > Math.ceil((double) count/15)){
            throw new IndexOutOfBoundsException("Page index out of bounds");
        }
        if(count < 15){
            return user.getTopics().stream()
                    .sorted(Comparator.comparing(Topic::getCreatedAt))
                    .map(topic -> new TopicProfileDto(topic.getUser().getUsername(), simpleDateFormat.format(topic.getCreatedAt().getTime()), topic.getTitle(), topic.getComments().size()))
                    .collect(Collectors.toList());
        }
        return topicDao.findByUserAndIdBetweenOrderByIdDesc(user, (count-(page*15) > 0? count-(page*15) : 1), count-((page-1)*15)).stream()
                .map(topic -> new TopicProfileDto(topic.getUser().getUsername(), topic.getCreatedAt().toString(), topic.getTitle(), topic.getComments().size()))
                .collect(Collectors.toList());
    }

    public List<TopicProfileDto> getLast15CommentedTopicsByUsername(String username){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not authorized."));
        return user.getComments().stream()
                .map(Comment::getTopic)
                .sorted(Comparator.comparing(Topic::getCreatedAt))
                .map(topic -> new TopicProfileDto(topic.getUser().getUsername(), simpleDateFormat.format(topic.getCreatedAt().getTime()), topic.getTitle(), topic.getComments().size()))
                .collect(Collectors.toList());
    }

    //TODO not working
    public List<TopicProfileDto> get15CommentedTopicsByUsernameAndPage(long page, String username){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not authorized."));
        final long count = topicDao.count();
        if (page < 1 || page > Math.ceil((double) count/15)){
            throw new IndexOutOfBoundsException("Page index out of bounds");
        }
        if(count < 15){
            return user.getComments().stream()
                    .map(Comment::getTopic)
                    .sorted(Comparator.comparing(Topic::getCreatedAt))
                    .map(topic -> new TopicProfileDto(topic.getUser().getUsername(), topic.getCreatedAt().toString(), topic.getTitle(), topic.getComments().size()))
                    .collect(Collectors.toList());
        }
        return commentDao.findByUserAndIdBetweenOrderByIdDesc(user, (count-(page*15) > 0? count-(page*15) : 1), count-((page-1)*15)).stream()
                .map(Comment::getTopic)
                .map(topic -> new TopicProfileDto(topic.getUser().getUsername(), simpleDateFormat.format(topic.getCreatedAt().getTime()), topic.getTitle(), topic.getComments().size()))
                .collect(Collectors.toList());
    }

    public TopicOutputDto getTopic(Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic foundTopic = topicOptional.orElseThrow(()-> new RuntimeException("Topic doesn't exist"));
        return new TopicOutputDto(foundTopic.getUser().getUsername(), foundTopic.getTitle(), foundTopic.getDescription(), foundTopic.getType().getDisplayName(), posted(foundTopic.getCreatedAt()), foundTopic.getId());
    }

    public List<CommentOutputDto> getComments(Long id){
        Optional<Topic> topicOptional = topicDao.findById(id);
        Topic topic = topicOptional.orElseThrow(()-> new RuntimeException("Topic doesn't exist"));
        return topic.getComments().stream()
                .sorted(Comparator.comparing(Comment::getCreatedAt))
                .map(comment -> new CommentOutputDto(comment.getComment(), comment.getUser().getUsername(), posted(comment.getCreatedAt())))
                .collect(Collectors.toList());
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

    public long getPageListSize(){
        return (long) Math.ceil((double) topicDao.count()/15);
    }

    //TODO not working
    public long getPageListSizeByType(String type){
        Optional<Type> typeOptional = Arrays.stream(Type.values())
                .filter(typeElement -> typeElement.toString().toLowerCase().equals(type))
                .findFirst();
        Type foundType = typeOptional.orElseThrow(() -> new RuntimeException("Type doesn't exist"));
        return (long) Math.ceil((double) topicDao.countByType(foundType)/15);
    }

    public long getPageListSizeOfTopicsByUsername(String username){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not authorized."));
        return (long) Math.ceil((double) user.getTopics().size()/15);
    }

    public long getPageListSizeOfCommentedTopicsByUsername(String username){
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsernameIgnoreCase(username));
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not authorized."));
        return (long) Math.ceil((double) user.getComments().size()/15);
    }

    private String posted(Date postedDate){
        long actualDate = new Date().getTime();
        long remain = (actualDate - postedDate.getTime())/1000;
        if(remain < 60){
            return remain + " secs";
        }
        remain /= 60;
        if(remain < 60){
            return remain + " mins";
        }
        remain /= 60;
        if(remain < 60){
            return remain + " hours";
        }
        remain /= 24;
        if(remain < 365){
            return remain + " days";
        }
        remain /= 365;
        return remain + " years";
    }
}
