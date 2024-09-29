package com.subscribe.management.mapper;

import com.subscribe.management.domain.command.ChannelSubscriptionLogCommand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChannelSubscriptionLogMapper {
    long save(ChannelSubscriptionLogCommand command);
}
