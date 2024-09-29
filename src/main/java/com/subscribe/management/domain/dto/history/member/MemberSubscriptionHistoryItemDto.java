package com.subscribe.management.domain.dto.history.member;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MemberSubscriptionHistoryItemDto {
    private LocalDate date;
    private List<MemberSubscriptionChannelStatusDto> channels;

    public static MemberSubscriptionHistoryItemDto create(LocalDate date, List<MemberSubscriptionChannelStatusDto> channels) {
        MemberSubscriptionHistoryItemDto dto = new MemberSubscriptionHistoryItemDto();
        dto.date = date;
        dto.channels = channels;

        return dto;
    }
}
