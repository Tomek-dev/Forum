package com.forum.forum.other.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Topic")
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
