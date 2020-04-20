package com.forum.forum.dto;

import com.forum.forum.model.Comment;
import com.forum.forum.model.Topic;
import com.forum.forum.model.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

    private int userComments;
    private int userVotes;
    private int userTopics;

    public void setUserComments(Set<Comment> userComments) {
        this.userComments = userComments.size();
    }

    public void setUserVotes(Set<Vote> userVotes) {
        this.userVotes = userVotes.size();
    }

    public void setUserTopics(Set<Topic> userTopics) {
        this.userTopics = userTopics.size();
    }
}
