package com.subscribe.management.domain.command;

import com.subscribe.management.domain.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelSubscriptionCommand {
    private Integer memberId;
    private Integer channelId;
    private SubscriptionType subscriptionType;

    public static ChannelSubscriptionCommand create(Integer memberId, Integer channelId, SubscriptionType subscriptionType) {
        ChannelSubscriptionCommand command = new ChannelSubscriptionCommand();
        command.setMemberId(memberId);
        command.setChannelId(channelId);
        command.setSubscriptionType(subscriptionType);

        return command;
    }

    public static ChannelSubscriptionCommand create(Integer memberId, SubscribeCommand subscribeCommand) {
        ChannelSubscriptionCommand command = new ChannelSubscriptionCommand();
        command.setMemberId(memberId);
        command.setChannelId(subscribeCommand.getChannelId());
        command.setSubscriptionType(subscribeCommand.getSubscriptionType());

        return command;
    }

    public static ChannelSubscriptionCommand create(Integer memberId, UnsubscribeCommand unsubscribeCommand) {
        ChannelSubscriptionCommand command = new ChannelSubscriptionCommand();
        command.setMemberId(memberId);
        command.setChannelId(unsubscribeCommand.getChannelId());
        command.setSubscriptionType(unsubscribeCommand.getSubscriptionType());

        return command;
    }
}
