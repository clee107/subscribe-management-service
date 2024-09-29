package com.subscribe.management.domain.command;

import com.subscribe.management.domain.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelSubscriptionLogCommand {
    private Integer memberId;
    private Integer channelId;
    private SubscriptionType requestType;

    public static ChannelSubscriptionLogCommand create(Integer memberId, SubscribeCommand subscribeCommand) {
        ChannelSubscriptionLogCommand command = new ChannelSubscriptionLogCommand();
        command.setMemberId(memberId);
        command.setChannelId(subscribeCommand.getChannelId());
        command.requestType = subscribeCommand.getSubscriptionType();

        return command;
    }

    public static ChannelSubscriptionLogCommand create(Integer memberId, UnsubscribeCommand unsubscribeCommand) {
        ChannelSubscriptionLogCommand command = new ChannelSubscriptionLogCommand();
        command.memberId = memberId;
        command.channelId = unsubscribeCommand.getChannelId();
        command.requestType = unsubscribeCommand.getSubscriptionType();

        return command;
    }
}
