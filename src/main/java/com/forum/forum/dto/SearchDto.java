package com.forum.forum.dto;

import com.forum.forum.enums.Type;

public class SearchDto {

    private String query;

    private boolean text;

    private Type type;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
