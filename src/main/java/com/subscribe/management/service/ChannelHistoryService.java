package com.subscribe.management.service;

import com.subscribe.management.domain.command.ChannelSubscriptionHistorySearchCommand;
import com.subscribe.management.domain.dto.ChannelDto;
import com.subscribe.management.domain.dto.history.channel.ChannelSubscriptionHistoryDto;
import com.subscribe.management.domain.response.ChannelSubscriptionHistoryListResponse;
import com.subscribe.management.error.ApiResponseException;
import com.subscribe.management.mapper.ChannelMapper;
import com.subscribe.management.mapper.ChannelSubscriptionHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChannelHistoryService {
    private final ChannelMapper channelMapper;
    private final ChannelSubscriptionHistoryMapper channelSubscriptionHistoryMapper;

    public ChannelSubscriptionHistoryListResponse getHistory(ChannelSubscriptionHistorySearchCommand command) {
        Optional<ChannelDto> foundChannel = channelMapper.findById(command.getChannelId());

        List<ChannelSubscriptionHistoryDto> history = channelSubscriptionHistoryMapper.findAllByChannelIdAndDateEquals(command.getChannelId(), command.getDate());

        if (foundChannel.isEmpty() && history.isEmpty()) {
            throw new ApiResponseException(HttpStatus.NOT_FOUND, "해당 채널을 찾을 수 없습니다.");
        }

        if (foundChannel.isEmpty()) {
            return ChannelSubscriptionHistoryListResponse.create(command.getChannelId(), "삭제된 채널", command.getDate(), history);
        }

        ChannelDto channelDto = foundChannel.get();
        return ChannelSubscriptionHistoryListResponse.create(channelDto.getChannelId(), channelDto.getChannelName(), command.getDate(), history);
    }
}
