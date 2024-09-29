package com.subscribe.management.service;

import com.subscribe.management.domain.dto.MemberDto;
import com.subscribe.management.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    @Transactional
    public MemberDto createMember(String phone) {
        if (memberMapper.existsByPhone(phone)) {
            log.error("MemberService.createMember() conflict error ({}).", phone);
            throw new RuntimeException("A member with the phone number already exists.");
        }

        MemberDto memberDto = new MemberDto();
        memberDto.setPhone(phone);

        memberMapper.save(memberDto);

        return memberMapper.findById(memberDto.getMemberId()).orElseThrow();
    }

    @Transactional
    public MemberDto getOrCreateMember(String phone) {
        MemberDto member = getMember(phone);

        if (member != null) {
            return member;
        }

        return createMember(phone);
    }

    public MemberDto getMember(String phone) {
        Optional<MemberDto> foundMember = memberMapper.findByPhone(phone);

        return foundMember.orElse(null);
    }
}
