package com.forum.forum.other.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum ReportType {
    HATE("Hate Speech"),
    WRONG("Wrong Tag"),
    UNCLEAR("Unclear Question"),
    OTHER("Other"),
    OFFENSIVE("Offensive Image");

    private final String displayName;

    public static Optional<ReportType> fromValue(String type) {
        return Arrays.stream(ReportType.values()).filter(value -> value.toString().equalsIgnoreCase(type)).findFirst();
    }
}
