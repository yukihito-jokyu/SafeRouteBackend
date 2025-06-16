package com.osc.saferoute.controller;

import com.osc.saferoute.application.service.RouteRankingService;
import com.osc.saferoute.domain.model.RouteRanking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/rankings/routes")
public class RouteRankingController {

    private final RouteRankingService routeRankingService;

    public RouteRankingController(RouteRankingService routeRankingService) {
        this.routeRankingService = routeRankingService;
    }

    @GetMapping
    public ResponseEntity<List<RouteRanking>> getRankedRoutes(@RequestParam(value = "type", required = false) String rankingType) {
        if (rankingType == null) {
            return ResponseEntity.badRequest().build();
        }

        List<RouteRanking> rankings;
        switch (rankingType.toLowerCase(Locale.ROOT)) {
            case "fastest":
                rankings = routeRankingService.getFastestRoutes();
                break;
            case "shortest":
                rankings = routeRankingService.getShortestRoutes();
                break;
            case "safest":
                rankings = routeRankingService.getSafestRoutes();
                break;
            default:
                return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(rankings);
    }
}
