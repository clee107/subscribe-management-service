package com.subscribe.management.mapper;

import com.subscribe.management.domain.dto.history.member.ChannelMemberSubscriptionLogItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberSubscriptionHistoryMapper {
    List<ChannelMemberSubscriptionLogItemDto> findAllByMemberId(Integer memberId);
}
