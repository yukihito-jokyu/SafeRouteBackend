package com.osc.saferoute.infrastructure.mybatis.mapper;

import com.osc.saferoute.infrastructure.mybatis.entity.UpcomingDrillEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UpcomingDrillMapper {

    List<UpcomingDrillEntity> findUpcomingDrills();

}
