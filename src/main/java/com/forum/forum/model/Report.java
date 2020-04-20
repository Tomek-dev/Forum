package com.forum.forum.model;

import com.forum.forum.other.enums.ReportType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ReportType type;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;

    private String describe;

    private LocalDateTime createdAt;

    private Report() {

    }

    public static class Builder{
        private ReportType type;
        private User user;
        private Topic topic;
        private String describe;

        public Builder type(ReportType type){
            this.type = type;
            return this;
        }

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public Builder topic(Topic topic){
            this.topic = topic;
            return this;
        }

        public Builder describe(String describe){
            this.describe = describe;
            return this;
        }

        public Report build(){
            Report report = new Report();
            report.user = this.user;
            report.topic = this.topic;
            report.describe = this.describe;
            report.type = this.type;
            report.createdAt = LocalDateTime.now();
            return report;
        }
    }
}
