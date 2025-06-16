package com.osc.saferoute.infrastructure.repository;

import com.osc.saferoute.domain.model.RouteRanking;
import com.osc.saferoute.domain.repository.RouteRankingRepository;
import com.osc.saferoute.infrastructure.mybatis.mapper.RouteRankingMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyBatisRouteRankingRepository implements RouteRankingRepository {

    private final RouteRankingMapper routeRankingMapper;

    public MyBatisRouteRankingRepository(RouteRankingMapper routeRankingMapper) {
        this.routeRankingMapper = routeRankingMapper;
    }

    @Override
    public List<RouteRanking> findFastestRoutes() {
        return routeRankingMapper.findFastestRoutes();
    }

    @Override
    public List<RouteRanking> findShortestRoutes() {
        return routeRankingMapper.findShortestRoutes();
    }

    @Override
    public List<RouteRanking> findSafestRoutes() {
        return routeRankingMapper.findSafestRoutes();
    }
}
