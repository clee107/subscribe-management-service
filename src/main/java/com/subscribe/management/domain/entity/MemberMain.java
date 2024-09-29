package com.subscribe.management.domain.entity;

import com.subscribe.management.domain.entity.base.BaseDateEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity
public class MemberMain extends BaseDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    private String phone;

    public static MemberMain create(String phone) {
        MemberMain member = new MemberMain();
        member.phone = phone;

        return member;
    }
}
