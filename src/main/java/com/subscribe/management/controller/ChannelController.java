package com.subscribe.management.controller;

import com.subscribe.management.domain.command.ChannelSubscriptionHistorySearchCommand;
import com.subscribe.management.domain.response.ChannelSubscriptionHistoryListResponse;
import com.subscribe.management.service.ChannelHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/channel")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelHistoryService channelHistoryService;

    @GetMapping("/history")
    @Description("채널 이력 조회")
    public ResponseEntity<ChannelSubscriptionHistoryListResponse> getHistory(@Valid ChannelSubscriptionHistorySearchCommand command) {
        return ResponseEntity.ok(channelHistoryService.getHistory(command));
    }
}
