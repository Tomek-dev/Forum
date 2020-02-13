package com.forum.forum;

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
}
