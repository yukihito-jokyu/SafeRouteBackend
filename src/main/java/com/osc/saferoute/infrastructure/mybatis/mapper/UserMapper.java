package com.osc.saferoute.infrastructure.mybatis.mapper;

import com.osc.saferoute.infrastructure.mybatis.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserEntity findById(@Param("user_id") String user_id);
    void update(UserEntity user);
}
