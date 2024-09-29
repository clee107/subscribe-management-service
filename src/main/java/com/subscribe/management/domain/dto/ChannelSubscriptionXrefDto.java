package com.subscribe.management.domain.dto;

import com.subscribe.management.domain.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChannelSubscriptionXrefDto {
    private Integer memberId;
    private Integer channelId;
    private SubscriptionType subscriptionType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
