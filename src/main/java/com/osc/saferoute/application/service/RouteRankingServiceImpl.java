package com.osc.saferoute.application.service;

import com.osc.saferoute.controller.dto.RouteRankingResponse;
import com.osc.saferoute.domain.model.RouteRankingData; // Added import
import com.osc.saferoute.domain.repository.EvacuationRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteRankingServiceImpl implements RouteRankingService {

    private final EvacuationRouteRepository evacuationRouteRepository;

    @Autowired
    public RouteRankingServiceImpl(EvacuationRouteRepository evacuationRouteRepository) {
        this.evacuationRouteRepository = evacuationRouteRepository;
    }

    @Override
    public List<RouteRankingResponse> getRouteRankings(String criteria) {
        List<RouteRankingData> rankingDataList = evacuationRouteRepository.findRouteRankingDataByCriteria(criteria);
        List<RouteRankingResponse> responseList = new ArrayList<>();

        for (int i = 0; i < rankingDataList.size(); i++) {
            RouteRankingData data = rankingDataList.get(i);
            RouteRankingResponse responseDto = new RouteRankingResponse();

            responseDto.setRouteId(data.routeId());
            responseDto.setUserNickname(data.userNickname());
            responseDto.setDistance(data.distance());
            responseDto.setEstimatedTime(data.estimatedTime());
            responseDto.setSelfAssessedSafety(data.selfAssessedSafety());
            responseDto.setRank(i + 1); // Rank is 1-based

            responseList.add(responseDto);
        }
        return responseList;
    }
}
