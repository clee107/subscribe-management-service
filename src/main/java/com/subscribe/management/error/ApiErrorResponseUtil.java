package com.subscribe.management.error;

import org.springframework.http.HttpStatus;

public class ApiErrorResponseUtil {

    public static String createMessage(HttpStatus httpStatus) {
        return switch (httpStatus) {
            case BAD_REQUEST -> "Bad request";
            case NOT_FOUND -> "Not found";
            default -> "Internal server error";
        };
    }
}
