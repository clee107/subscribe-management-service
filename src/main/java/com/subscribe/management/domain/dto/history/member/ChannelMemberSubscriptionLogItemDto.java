package com.subscribe.management.domain.dto.history.member;

import com.subscribe.management.domain.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ChannelMemberSubscriptionLogItemDto {
    private Integer memberId;
    private Integer channelId;
    private String phone;
    private String channelName;
    private SubscriptionType requestType;
    private LocalDate createdDate;
    private LocalDateTime createdAt;
}
