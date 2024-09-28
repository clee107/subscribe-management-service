package com.subscribe.management.controller;

import com.subscribe.management.domain.TestResponse;
import com.subscribe.management.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscribeService subscribeService;

    @PostMapping("/subscribe")
    public ResponseEntity<TestResponse> subscribe() {
        TestResponse response = subscribeService.getTestResponse();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<Void> unsubscribe() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/subscribe/history")
    public ResponseEntity<Void> history() {
        return ResponseEntity.ok().build();
    }
}
