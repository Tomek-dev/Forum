package com.forum.forum.other.builder;

import com.forum.forum.enums.Type;
import com.forum.forum.model.Comment;
import com.forum.forum.model.Report;
import com.forum.forum.model.Topic;
import com.forum.forum.model.User;

import java.time.LocalDateTime;
import java.util.Set;

public class TopicBuilder {

    private Topic topic = new Topic();

    public static TopicBuilder builder(){
        return new TopicBuilder();
    }

    public TopicBuilder title(String title){
        topic.setTitle(title);
        return this;
    }

    public TopicBuilder user(User user){
        topic.setUser(user);
        return this;
    }

    public TopicBuilder description(String description){
        topic.setDescription(description);
        return this;
    }

    public TopicBuilder comments(Set<Comment> comments){
        topic.setComments(comments);
        return this;
    }

    public TopicBuilder createdAt(LocalDateTime createdAt){
        topic.setCreatedAt(createdAt);
        return this;
    }

    public TopicBuilder report(Set<Report> report){
        topic.setReport(report);
        return this;
    }

    public TopicBuilder type(Type type){
        topic.setType(type);
        return this;
    }

    public TopicBuilder video(String videoUrl){
        topic.setVideoUrl(videoUrl);
        return this;
    }

    public Topic build(){
        return topic;
    }
}
