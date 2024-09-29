package com.subscribe.management.mapper;

import com.subscribe.management.domain.dto.history.channel.ChannelSubscriptionHistoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ChannelSubscriptionHistoryMapper {
    List<ChannelSubscriptionHistoryDto> findAllByChannelIdAndDateEquals(@Param("channelId") Integer channelId, @Param("date") LocalDate date);
}
