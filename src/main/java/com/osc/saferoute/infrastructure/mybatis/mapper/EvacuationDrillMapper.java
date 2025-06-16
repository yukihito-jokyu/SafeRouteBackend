package com.osc.saferoute.infrastructure.mybatis.mapper;

import com.osc.saferoute.infrastructure.mybatis.entity.EvacuationDrillEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvacuationDrillMapper {
    EvacuationDrillEntity findLatestScheduledDrill();
}
