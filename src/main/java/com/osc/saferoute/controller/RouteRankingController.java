package com.osc.saferoute.controller;

import com.osc.saferoute.controller.dto.RouteRankingResponse;
import com.osc.saferoute.application.service.RouteRankingService; // This service will be created later
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rankings/routes")
public class RouteRankingController {

    private final RouteRankingService routeRankingService;

    @Autowired
    public RouteRankingController(RouteRankingService routeRankingService) {
        this.routeRankingService = routeRankingService;
    }

    @GetMapping("/{criteria}")
    public ResponseEntity<List<RouteRankingResponse>> getRouteRankings(@PathVariable String criteria) {
        // Placeholder implementation:
        // List<RouteRankingResponse> rankings = routeRankingService.getRankingsByCriteria(criteria);
        // return ResponseEntity.ok(rankings);
        return ResponseEntity.ok(new ArrayList<>());
    }
}
