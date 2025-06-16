package com.osc.saferoute.infrastructure.mybatis.mapper;

import com.osc.saferoute.infrastructure.mybatis.entity.RewardEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RewardMapper {

    List<RewardEntity> selectAllRewards();

}
