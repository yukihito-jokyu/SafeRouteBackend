package com.osc.saferoute.domain.repository;

import com.osc.saferoute.domain.model.RouteRanking;
import java.util.List;

public interface RouteRankingRepository {

    List<RouteRanking> findFastestRoutes();

    List<RouteRanking> findShortestRoutes();

    List<RouteRanking> findSafestRoutes();
}
