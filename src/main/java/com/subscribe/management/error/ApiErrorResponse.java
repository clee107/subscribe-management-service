package com.subscribe.management.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {
    private String message;

    public static ApiErrorResponse create(String message) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.message = message;

        return response;
    }
}
