package com.osc.saferoute.application.service;

import com.osc.saferoute.domain.model.RouteRanking;
import com.osc.saferoute.domain.repository.RouteRankingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteRankingService {

    private final RouteRankingRepository routeRankingRepository;

    public RouteRankingService(RouteRankingRepository routeRankingRepository) {
        this.routeRankingRepository = routeRankingRepository;
    }

    public List<RouteRanking> getFastestRoutes() {
        return routeRankingRepository.findFastestRoutes();
    }

    public List<RouteRanking> getShortestRoutes() {
        return routeRankingRepository.findShortestRoutes();
    }

    public List<RouteRanking> getSafestRoutes() {
        return routeRankingRepository.findSafestRoutes();
    }
}
