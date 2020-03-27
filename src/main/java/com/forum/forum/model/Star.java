package com.forum.forum.model;

import javax.persistence.*;

@Entity
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User starUser;

    @ManyToOne
    private User user;

    private Star() {
    }

    public static class Builder{
        private User starUser;
        private User user;

        public Builder starUser(User starUser){
            this.starUser = starUser;
            return this;
        }

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public Star build(){
            Star star = new Star();
            star.starUser = this.starUser;
            star.user = this.user;
            return star;
        }
    }
}
