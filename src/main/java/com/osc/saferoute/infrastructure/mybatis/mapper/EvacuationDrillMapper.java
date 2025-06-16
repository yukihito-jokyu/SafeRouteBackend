package com.osc.saferoute.infrastructure.mybatis.mapper;

import com.osc.saferoute.infrastructure.mybatis.entity.EvacuationDrillEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EvacuationDrillMapper {
    EvacuationDrillEntity findLatestScheduledDrill();
    List<EvacuationDrillEntity> findUpcomingDrills(@Param("currentTimestamp") LocalDateTime currentTimestamp, @Param("userId") Long userId);
    List<EvacuationDrillEntity> findPastDrills(@Param("currentTimestamp") LocalDateTime currentTimestamp, @Param("userId") Long userId);
}
