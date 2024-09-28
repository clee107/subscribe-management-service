package com.subscribe.management.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface GeneralMapper {
    Optional<Integer> findByDual();
}
