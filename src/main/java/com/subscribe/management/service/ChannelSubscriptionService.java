package com.subscribe.management.service;

import com.subscribe.management.domain.command.ChannelSubscriptionCommand;
import com.subscribe.management.domain.command.ChannelSubscriptionLogCommand;
import com.subscribe.management.domain.command.SubscribeCommand;
import com.subscribe.management.domain.command.UnsubscribeCommand;
import com.subscribe.management.domain.dto.ChannelDto;
import com.subscribe.management.domain.dto.ChannelSubscriptionXrefDto;
import com.subscribe.management.domain.dto.MemberDto;
import com.subscribe.management.domain.dto.history.member.ChannelMemberSubscriptionLogItemDto;
import com.subscribe.management.domain.enums.SubscriptionType;
import com.subscribe.management.domain.response.MemberSubscriptionHistoryResponse;
import com.subscribe.management.error.ApiResponseException;
import com.subscribe.management.mapper.ChannelMapper;
import com.subscribe.management.mapper.MemberSubscriptionHistoryMapper;
import com.subscribe.management.mapper.ChannelSubscriptionLogMapper;
import com.subscribe.management.mapper.ChannelSubscriptionXrefMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChannelSubscriptionService {
    private final MemberService memberService;
    private final ChannelMapper channelMapper;
    private final ChannelSubscriptionXrefMapper channelSubscriptionXrefMapper;
    private final ChannelSubscriptionLogMapper channelSubscriptionLogMapper;
    private final MemberSubscriptionHistoryMapper memberSubscriptionHistoryMapper;

    @Transactional
    public void joinSubscription(SubscribeCommand command) {
        ChannelDto channel = channelMapper.findById(command.getChannelId())
                .orElseThrow(() -> new ApiResponseException(HttpStatus.BAD_REQUEST, "채널을 찾을 수 없습니다."));

        MemberDto member = memberService.getOrCreateMember(command.getPhone());

        if (!channel.getCanSubscribe()) {
            throw new ApiResponseException(HttpStatus.BAD_REQUEST, "구독이 불가능한 채널입니다.");
        }

        ChannelSubscriptionCommand saveCommand = ChannelSubscriptionCommand.create(member.getMemberId(), command);
        Optional<ChannelSubscriptionXrefDto> subscriptionFound = channelSubscriptionXrefMapper.findByMemberIdAndChannelId(member.getMemberId(), command.getChannelId());

        if (subscriptionFound.isEmpty()) {
            channelSubscriptionXrefMapper.save(saveCommand);
        } else {
            ChannelSubscriptionXrefDto currentSubscription = subscriptionFound.get();

            if (currentSubscription.getSubscriptionType().getLevel() >= command.getSubscriptionType().getLevel()) {
                throw new ApiResponseException(HttpStatus.BAD_REQUEST, "구독 변경에 실패하였습니다.");
            }

            channelSubscriptionXrefMapper.updateByMemberIdAndChannelId(saveCommand);
        }

        ChannelSubscriptionLogCommand logCommand = ChannelSubscriptionLogCommand.create(member.getMemberId(), command);
        channelSubscriptionLogMapper.save(logCommand);
    }

    @Transactional
    public void withdrawSubscription(UnsubscribeCommand command) {
        ChannelDto channel = channelMapper.findById(command.getChannelId())
                .orElseThrow(() -> new ApiResponseException(HttpStatus.BAD_REQUEST, "채널을 찾을 수 없습니다."));

        MemberDto member = memberService.getOrCreateMember(command.getPhone());

        if (!channel.getCanUnsubscribe()) {
            throw new ApiResponseException(HttpStatus.BAD_REQUEST, "구독 해지가 불가능한 채널입니다.");
        }

        ChannelSubscriptionCommand updateCommand = ChannelSubscriptionCommand.create(member.getMemberId(), command);
        Optional<ChannelSubscriptionXrefDto> subscriptionFound = channelSubscriptionXrefMapper.findByMemberIdAndChannelId(member.getMemberId(), command.getChannelId());

        if (subscriptionFound.isEmpty()) {
            throw new ApiResponseException(HttpStatus.BAD_REQUEST);
        }

        ChannelSubscriptionXrefDto currentSubscription = subscriptionFound.get();

        if (currentSubscription.getSubscriptionType().getLevel() <= command.getSubscriptionType().getLevel()) {
            throw new ApiResponseException(HttpStatus.BAD_REQUEST, "구독 해지에 실패하였습니다.");
        }

        if (command.getSubscriptionType() == SubscriptionType.CANCEL) {
            channelSubscriptionXrefMapper.deleteByMemberIdAndChannelId(member.getMemberId(), command.getChannelId());
        } else {
            channelSubscriptionXrefMapper.updateByMemberIdAndChannelId(updateCommand);
        }

        ChannelSubscriptionLogCommand logCommand = ChannelSubscriptionLogCommand.create(member.getMemberId(), command);
        channelSubscriptionLogMapper.save(logCommand);
    }

    public MemberSubscriptionHistoryResponse getSubscriptionHistory(String phone) {
        MemberDto memberDto = memberService.getMember(phone);

        if (memberDto == null) {
            throw new ApiResponseException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");
        }

        List<ChannelMemberSubscriptionLogItemDto> history = memberSubscriptionHistoryMapper.findAllByMemberId(memberDto.getMemberId());
        return MemberSubscriptionHistoryResponse.create(memberDto.getMemberId(), memberDto.getPhone(), history);
    }
}
