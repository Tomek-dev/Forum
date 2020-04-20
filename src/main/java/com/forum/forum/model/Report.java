package com.forum.forum.model;

import com.forum.forum.enums.ReportType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private ReportType type;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;

    private String describe;

    private LocalDateTime createdAt;

    public Report() {
        createdAt = LocalDateTime.now();
    }

    public Report(@NotNull ReportType type, String describe) {
        this.type = type;
        this.describe = describe;
        createdAt = LocalDateTime.now();
    }
}
