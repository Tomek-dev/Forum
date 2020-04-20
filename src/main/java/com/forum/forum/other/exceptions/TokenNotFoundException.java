package com.forum.forum.other.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Token")
public class TokenNotFoundException extends RuntimeException{
    public TokenNotFoundException() {
        super("Token not found.");
    }

    public TokenNotFoundException(String message) {
        super(message);
    }

    public TokenNotFoundException(Throwable cause) {
        super(cause);
    }
}
