package com.osc.saferoute.infrastructure.mybatis.mapper;

import com.osc.saferoute.domain.model.RouteRanking;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RouteRankingMapper {

    List<RouteRanking> findFastestRoutes();

    List<RouteRanking> findShortestRoutes();

    List<RouteRanking> findSafestRoutes();
}
