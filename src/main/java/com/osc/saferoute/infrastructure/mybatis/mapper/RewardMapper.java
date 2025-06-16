package com.osc.saferoute.infrastructure.mybatis.mapper;

import com.osc.saferoute.domain.model.Reward;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RewardMapper {

    List<Reward> selectAllRewards();

}
