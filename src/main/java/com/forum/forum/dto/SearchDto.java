package com.forum.forum.dto;

import com.forum.forum.enums.Type;

public class SearchDto {

    private String query;

    private boolean user;

    private Type type;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
