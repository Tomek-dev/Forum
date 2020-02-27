package com.forum.forum.enums;

import java.util.Arrays;
import java.util.Optional;

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

    Type(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Optional<Type> fromValue(String value){
        return Arrays.stream(Type.values()).filter(type -> type.displayName.equalsIgnoreCase(value)).findFirst();
    }
}
