package com.osc.saferoute.application.service;

import com.osc.saferoute.controller.dto.RouteRankingResponse;
import com.osc.saferoute.domain.model.RouteRankingData;
import com.osc.saferoute.domain.repository.EvacuationRouteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteRankingServiceImplTest {

    @Mock
    private EvacuationRouteRepository evacuationRouteRepository;

    @InjectMocks
    private RouteRankingServiceImpl routeRankingService;

    @Test
    @DisplayName("getRouteRankings should return ranked DTOs for 'fastest' criteria")
    void getRouteRankings_whenFastestCriteria_shouldReturnRankedDtos() {
        // Arrange
        String criteria = "fastest";
        RouteRankingData data1 = new RouteRankingData("route001", "UserA", 1500.0, 20, 4);
        RouteRankingData data2 = new RouteRankingData("route002", "UserB", 1200.0, 15, 5);
        List<RouteRankingData> mockDataList = Arrays.asList(data1, data2);

        when(evacuationRouteRepository.findRouteRankingDataByCriteria(criteria)).thenReturn(mockDataList);

        // Act
        List<RouteRankingResponse> result = routeRankingService.getRouteRankings(criteria);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        // Check data mapping and rank for the first element
        RouteRankingResponse response1 = result.get(0);
        assertEquals(data1.routeId(), response1.getRouteId());
        assertEquals(data1.userNickname(), response1.getUserNickname());
        assertEquals(data1.distance(), response1.getDistance());
        assertEquals(data1.estimatedTime(), response1.getEstimatedTime());
        assertEquals(data1.selfAssessedSafety(), response1.getSelfAssessedSafety());
        assertEquals(1, response1.getRank()); // Rank should be 1

        // Check data mapping and rank for the second element
        RouteRankingResponse response2 = result.get(1);
        assertEquals(data2.routeId(), response2.getRouteId());
        assertEquals(data2.userNickname(), response2.getUserNickname());
        assertEquals(2, response2.getRank()); // Rank should be 2

        verify(evacuationRouteRepository, times(1)).findRouteRankingDataByCriteria(criteria);
    }

    @Test
    @DisplayName("getRouteRankings should return empty list when repository returns empty")
    void getRouteRankings_whenRepositoryReturnsEmpty_shouldReturnEmptyList() {
        // Arrange
        String criteria = "safest";
        when(evacuationRouteRepository.findRouteRankingDataByCriteria(criteria)).thenReturn(Collections.emptyList());

        // Act
        List<RouteRankingResponse> result = routeRankingService.getRouteRankings(criteria);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(evacuationRouteRepository, times(1)).findRouteRankingDataByCriteria(criteria);
    }
}
