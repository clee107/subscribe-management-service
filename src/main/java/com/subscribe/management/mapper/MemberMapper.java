package com.subscribe.management.mapper;

import com.subscribe.management.domain.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    boolean existsByPhone(String phone);

    Optional<MemberDto> findById(Integer memberId);

    Optional<MemberDto> findByPhone(String phone);

    int save(MemberDto memberDto);
}
