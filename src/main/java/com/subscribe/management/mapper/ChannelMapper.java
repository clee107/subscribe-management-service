package com.subscribe.management.mapper;

import com.subscribe.management.domain.dto.ChannelDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ChannelMapper {
    Optional<ChannelDto> findById(Integer channelId);
}
