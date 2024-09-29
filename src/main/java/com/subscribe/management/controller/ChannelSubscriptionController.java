package com.subscribe.management.controller;

import com.subscribe.management.domain.command.SubscribeCommand;
import com.subscribe.management.domain.command.UnsubscribeCommand;
import com.subscribe.management.domain.response.MemberSubscriptionHistoryResponse;
import com.subscribe.management.error.ApiResponseException;
import com.subscribe.management.service.ChannelSubscriptionService;
import com.subscribe.management.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/channel/subscription")
@RequiredArgsConstructor
public class ChannelSubscriptionController {
    private final ChannelSubscriptionService channelSubscriptionService;

    @PostMapping("/join")
    @Description("채널 구독하기")
    public ResponseEntity<Void> joinSubscription(@RequestBody @Valid SubscribeCommand command) {
        channelSubscriptionService.joinSubscription(command);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw")
    @Description("채널 구독 해지하기")
    public ResponseEntity<Void> withdrawSubscription(@RequestBody @Valid UnsubscribeCommand command) {
        channelSubscriptionService.withdrawSubscription(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history/{phoneNumber}")
    @Description("특정 사용자 구독 이력 조회")
    public ResponseEntity<MemberSubscriptionHistoryResponse> history(@PathVariable String phoneNumber) {
        if (!StringUtils.isValidPhoneNumber(phoneNumber)) {
            throw new ApiResponseException(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(channelSubscriptionService.getSubscriptionHistory(phoneNumber));
    }
}
