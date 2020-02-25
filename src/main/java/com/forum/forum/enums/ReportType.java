package com.forum.forum.enums;

public enum ReportType {
    HATE("Hate Speech"),
    WRONG("Wrong Tag"),
    UNCLEAR("Unclear Question"),
    OTHER("Other"),
    OFFENSIVE("Offensive Image");

    private final String displayName;

    ReportType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
