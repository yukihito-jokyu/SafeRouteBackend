package com.osc.saferoute.domain.model;

public record RouteRankingData(
    String routeId,
    String userNickname,
    Double distance,
    Integer estimatedTime,
    Integer selfAssessedSafety
) {
}
