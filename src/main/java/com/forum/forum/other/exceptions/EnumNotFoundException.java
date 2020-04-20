package com.forum.forum.other.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Enum")
public class EnumNotFoundException extends RuntimeException {
    public EnumNotFoundException() {
        super("Enum not found.");
    }

    public EnumNotFoundException(String message) {
        super(message);
    }

    public EnumNotFoundException(Throwable cause) {
        super(cause);
    }
}
