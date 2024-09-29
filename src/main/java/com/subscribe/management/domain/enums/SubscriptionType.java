package com.subscribe.management.domain.enums;

import lombok.Getter;

@Getter
public enum SubscriptionType {
    CANCEL(0),
    GENERAL(1),
    PREMIUM(2);

    private final int level;

    private SubscriptionType(int level) {
        this.level = level;
    }
}
