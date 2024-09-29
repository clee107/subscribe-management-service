package com.subscribe.management.domain.response;

import com.subscribe.management.domain.dto.history.member.ChannelMemberSubscriptionLogItemDto;
import com.subscribe.management.domain.dto.history.member.MemberSubscriptionChannelStatusDto;
import com.subscribe.management.domain.dto.history.member.MemberSubscriptionHistoryItemDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemberSubscriptionHistoryResponse {
    private Integer memberId;
    private String phone;
    private List<MemberSubscriptionHistoryItemDto> history;

    public static MemberSubscriptionHistoryResponse create(Integer memberId, String phone, List<ChannelMemberSubscriptionLogItemDto> history) {
        MemberSubscriptionHistoryResponse response = new MemberSubscriptionHistoryResponse();

        List<MemberSubscriptionHistoryItemDto> historyList = new ArrayList<>();
        List<LocalDate> dates = history.stream()
                .map(ChannelMemberSubscriptionLogItemDto::getCreatedDate)
                .distinct()
                .toList();

        for (LocalDate date : dates) {
            List<MemberSubscriptionChannelStatusDto> channels = history.stream()
                    .filter(x -> x.getCreatedDate().equals(date))
                    .map(MemberSubscriptionChannelStatusDto::create)
                    .toList();

            historyList.add(MemberSubscriptionHistoryItemDto.create(date, channels));
        }

        response.memberId = memberId;
        response.phone = phone;
        response.history = historyList;

        return response;
    }
}
