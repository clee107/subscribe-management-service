package com.subscribe.management.mapper;

import com.subscribe.management.domain.command.ChannelSubscriptionCommand;
import com.subscribe.management.domain.dto.ChannelSubscriptionXrefDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ChannelSubscriptionXrefMapper {
    Optional<ChannelSubscriptionXrefDto> findByMemberIdAndChannelId(
            @Param("memberId") Integer memberId,
            @Param("channelId") Integer channelId
    );

    int save(ChannelSubscriptionCommand command);

    int updateByMemberIdAndChannelId(ChannelSubscriptionCommand command);

    int deleteByMemberIdAndChannelId(Integer memberId, Integer channelId);
}
