package com.subscribe.management.domain.response;

import com.subscribe.management.domain.dto.history.channel.ChannelSubscriptionHistoryDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ChannelSubscriptionHistoryListResponse {
    private Integer channelId;
    private String channelName;
    private LocalDate date;
    private List<ChannelSubscriptionHistoryDto> history;

    public static ChannelSubscriptionHistoryListResponse create(Integer channelId, String channelName, LocalDate date, List<ChannelSubscriptionHistoryDto> history) {
        ChannelSubscriptionHistoryListResponse response = new ChannelSubscriptionHistoryListResponse();
        response.setChannelId(channelId);
        response.setChannelName(channelName);
        response.setDate(date);
        response.setHistory(history);

        return response;
    }
}
