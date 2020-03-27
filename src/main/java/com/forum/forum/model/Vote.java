package com.forum.forum.model;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;

    private Vote() {
    }

    public static class Builder{
        private User user;
        private Topic topic;

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public Builder topic(Topic topic){
            this.topic = topic;
            return this;
        }

        public Vote build() {
            Vote vote = new Vote();
            vote.user = this.user;
            vote.topic = this.topic;
            return vote;
        }

    }
}
