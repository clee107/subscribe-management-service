# ddl
create table member_main (
    member_id int not null auto_increment primary key comment '회원 순번',
    phone varchar(50) not null comment '연락처',
    created_at datetime not null comment '생성 일시',
    updated_at datetime not null comment '수정 일시'
) comment '회원 메인';

create table channel_main (
    channel_id int not null auto_increment primary key comment '채널 순번',
    channel_name varchar(200) not null comment '채널명',
    can_subscribe bit(1) not null comment '구독 가능 여부',
    can_unsubscribe bit(1) not null comment '해지 가능 여부',
    created_at datetime not null comment '생성 일시',
    updated_at datetime not null comment '수정 일시'
) comment '채널 메인';

create table channel_member_subscription_xref (
    member_id int not null comment '회원 순번',
    channel_id int not null comment '채널 순번',
    subscription_type enum('GENERAL', 'PREMIUM') not null comment '구독 타입',
    created_at datetime not null comment '생성 일시',
    updated_at datetime not null comment '수정 일시',
    constraint primary key (member_id, channel_id)
) comment '채널 회원 구독 매핑';

create table channel_member_subscription_log (
    log_id bigint unsigned auto_increment not null primary key comment '로그 순번',
    member_id int not null comment '회원 순번',
    channel_id int not null comment '채널 순번',
    request_type varchar(50) not null comment '구독 요청 타입',
    created_at datetime not null comment '생성 일시'
) comment '회원 구독 로그';

create unique index idx_phone on member_main(phone);
create index idx_memberid on channel_member_subscription_log (member_id);
create index idx_channelid on channel_member_subscription_log (channel_id);
create index idx_createdat on channel_member_subscription_log (created_at);


# dml
insert into channel_main (
    channel_name, can_subscribe, can_unsubscribe, created_at, updated_at
) values ('기본 테스트 채널', true, true, now(), now());

insert into channel_main (
    channel_name, can_subscribe, can_unsubscribe, created_at, updated_at
) values ('구독만 가능한 채널', true, false, now(), now());