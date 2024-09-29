package com.subscribe.management.domain.command;

import com.subscribe.management.config.annotation.EnumNamePattern;
import com.subscribe.management.config.annotation.PhoneNumber;
import com.subscribe.management.domain.enums.SubscriptionType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnsubscribeCommand {
    @NotNull
    private Integer channelId;

    @NotNull
    @PhoneNumber
    private String phone;

    @NotNull
    @EnumNamePattern(regexp = "CANCEL|GENERAL")
    private SubscriptionType subscriptionType;
}
