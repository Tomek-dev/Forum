package com.forum.forum.other.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum Type {
    C("C"),
    JAVA("Java"),
    PYTHON("Python"),
    JAVASCRIPT("JavaScript"),
    RUBY("Ruby"),
    CSHARP("C#"),
    SQL("SQL"),
    PHP("PHP"),
    CPP("C++"),
    SWIFT("Swift"),
    HTMLCSS("HTML & CSS"),
    OTHER("Other");

    private final String displayName;

    public static Optional<Type> fromValue(String value){
        return Arrays.stream(Type.values()).filter(type -> type.toString().equalsIgnoreCase(value)).findFirst();
    }
}
