package com.subscribe.management.domain.dto.history.channel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subscribe.management.domain.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChannelSubscriptionHistoryDto {
    @JsonIgnore
    private Long logId;

    private Integer memberId;
    private String phone;
    private SubscriptionType requestType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
