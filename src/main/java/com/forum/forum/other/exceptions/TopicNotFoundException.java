package com.forum.forum.other.exceptions;

public class TopicNotFoundException extends RuntimeException{
    public TopicNotFoundException() {
        super("Topic not found.");
    }

    public TopicNotFoundException(String message) {
        super(message);
    }

    public TopicNotFoundException(Throwable cause) {
        super(cause);
    }
}
