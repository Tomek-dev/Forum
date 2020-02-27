package com.forum.forum.dto;

public class ReportDto {

    private String name;
    private Long id;
    private String type;
    private String createdAt;
    private String describe;

    public ReportDto(String name, Long id, String type, String createdAt, String describe) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
