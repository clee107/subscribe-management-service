package com.subscribe.management.error;

import jakarta.servlet.RequestDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
@RequiredArgsConstructor
public class GlobalErrorController implements ErrorController {
    private final ErrorAttributes errorAttributes;

    @RequestMapping
    public ResponseEntity<ApiErrorResponse> handleExceptions(WebRequest request) {
        Throwable throwable = errorAttributes.getError(request);
        ApiResponseException responseException = createApiResponseException(request, throwable);

        return ResponseEntity.status(responseException.getStatus())
                .body(ApiErrorResponse.create(responseException.getMessage()));
    }

    @ExceptionHandler(ApiResponseException.class)
    public ResponseEntity<ApiErrorResponse> apiResponseExceptionHandler(ApiResponseException exception) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.create(exception.getMessage());

        return ResponseEntity.status(exception.getStatus())
                .body(apiErrorResponse);
    }

    private HttpStatus getStatusCode(WebRequest request) {
        Integer foundStatusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE, RequestAttributes.SCOPE_REQUEST);
        return HttpStatus.valueOf(Optional.ofNullable(foundStatusCode).orElse(500));
    }

    private ApiResponseException createApiResponseException(WebRequest request, Throwable throwable) {
        if (throwable instanceof ApiResponseException) {
            return (ApiResponseException) throwable;
        }

        if (throwable instanceof HttpRequestMethodNotSupportedException) {
            return new ApiResponseException(HttpStatus.NOT_FOUND);
        }

        if (throwable instanceof IllegalArgumentException) {
            return new ApiResponseException(HttpStatus.BAD_REQUEST);
        }

        return new ApiResponseException(getStatusCode(request));
    }
}
