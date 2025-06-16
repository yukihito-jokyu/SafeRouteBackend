package com.osc.saferoute.controller.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteRankingResponse {
    private String routeId;
    private String userNickname;
    private Double distance;
    private Integer estimatedTime;
    private Integer selfAssessedSafety;
    private Integer rank;
}
