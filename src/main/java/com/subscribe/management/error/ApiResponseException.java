package com.subscribe.management.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponseException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public ApiResponseException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponseException(HttpStatus status) {
        this.status = status;
        this.message = ApiErrorResponseUtil.createMessage(status);
    }
}
