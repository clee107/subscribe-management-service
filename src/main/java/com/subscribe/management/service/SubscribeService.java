package com.subscribe.management.service;

import com.subscribe.management.domain.TestResponse;
import com.subscribe.management.error.ApiResponseException;
import com.subscribe.management.mapper.GeneralMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubscribeService {
    private final GeneralMapper generalMapper;

    public TestResponse getTestResponse() {
        Optional<Integer> found = generalMapper.findByDual();

        if (found.isEmpty()) {
            throw new ApiResponseException(HttpStatus.NOT_FOUND);
        }

        TestResponse response = new TestResponse();
        response.setValue(found.get());

        return response;
    }
}
