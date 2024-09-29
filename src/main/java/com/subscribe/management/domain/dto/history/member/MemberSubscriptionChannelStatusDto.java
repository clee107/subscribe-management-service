package com.subscribe.management.domain.dto.history.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.subscribe.management.domain.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberSubscriptionChannelStatusDto {
    private Integer channelId;
    private String channelName;
    private SubscriptionType subscriptionType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public static MemberSubscriptionChannelStatusDto create(ChannelMemberSubscriptionLogItemDto subscriptionLogItem) {
        MemberSubscriptionChannelStatusDto dto = new MemberSubscriptionChannelStatusDto();
        dto.channelId = subscriptionLogItem.getChannelId();
        dto.channelName = subscriptionLogItem.getChannelName();
        dto.subscriptionType = subscriptionLogItem.getRequestType();
        dto.createdAt = subscriptionLogItem.getCreatedAt();

        return dto;
    }
}
