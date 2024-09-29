package com.subscribe.management.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChannelDto {
    private Integer channelId;
    private String channelName;
    private Boolean canSubscribe;
    private Boolean canUnsubscribe;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
