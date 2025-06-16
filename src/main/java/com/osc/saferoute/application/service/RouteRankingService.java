package com.osc.saferoute.application.service;

import com.osc.saferoute.controller.dto.RouteRankingResponse;

import java.util.List;

public interface RouteRankingService {
    List<RouteRankingResponse> getRouteRankings(String criteria);
}
