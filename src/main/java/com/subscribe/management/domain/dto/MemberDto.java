package com.subscribe.management.domain.dto;

import com.subscribe.management.domain.entity.MemberMain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private Integer memberId;
    private String phone;

    public static MemberDto create(MemberMain entity) {
        MemberDto dto = new MemberDto();
        dto.memberId = entity.getMemberId();
        dto.phone = entity.getPhone();

        return dto;
    }
}
