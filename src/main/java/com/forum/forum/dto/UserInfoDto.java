package com.forum.forum.dto;

import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import com.forum.forum.model.Vote;

import java.util.Set;

public class UserInfoDto {

    private int userComments;
    private int userVotes;
    private int userTopics;

    public UserInfoDto() {
    }

    public int getUserComments() {
        return userComments;
    }

    public void setUserComments(Set<Comment> userComments) {
        this.userComments = userComments.size();
    }

    public int getUserVotes() {
        return userVotes;
    }

    public void setUserVotes(Set<Vote> userVotes) {
        this.userVotes = userVotes.size();
    }

    public int getUserTopics() {
        return userTopics;
    }

    public void setUserTopics(Set<Topic> userTopics) {
        this.userTopics = userTopics.size();
    }
}
