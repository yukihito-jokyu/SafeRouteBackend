package com.osc.saferoute.application.service;

import com.osc.saferoute.domain.model.RouteRanking;
import com.osc.saferoute.domain.repository.RouteRankingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RouteRankingServiceTest {

    @Mock
    private RouteRankingRepository routeRankingRepository;

    @InjectMocks
    private RouteRankingService routeRankingService;

    @Test
    void testGetFastestRoutes() {
        // Arrange
        List<RouteRanking> expectedRankings = Arrays.asList(
                new RouteRanking("R001", "Fastest Route 1", 10.0, 15.0, 3, "UserA"),
                new RouteRanking("R002", "Fastest Route 2", 12.5, 18.0, 4, "UserB")
        );
        when(routeRankingRepository.findFastestRoutes()).thenReturn(expectedRankings);

        // Act
        List<RouteRanking> actualRankings = routeRankingService.getFastestRoutes();

        // Assert
        assertNotNull(actualRankings);
        assertEquals(expectedRankings.size(), actualRankings.size());
        assertEquals(expectedRankings, actualRankings);
        verify(routeRankingRepository, times(1)).findFastestRoutes();
    }

    @Test
    void testGetFastestRoutes_Empty() {
        // Arrange
        when(routeRankingRepository.findFastestRoutes()).thenReturn(Collections.emptyList());

        // Act
        List<RouteRanking> actualRankings = routeRankingService.getFastestRoutes();

        // Assert
        assertNotNull(actualRankings);
        assertEquals(0, actualRankings.size());
        verify(routeRankingRepository, times(1)).findFastestRoutes();
    }

    @Test
    void testGetShortestRoutes() {
        // Arrange
        List<RouteRanking> expectedRankings = Arrays.asList(
                new RouteRanking("R003", "Shortest Route 1", 5.0, 25.0, 5, "UserC"),
                new RouteRanking("R004", "Shortest Route 2", 6.2, 22.0, 2, "UserD")
        );
        when(routeRankingRepository.findShortestRoutes()).thenReturn(expectedRankings);

        // Act
        List<RouteRanking> actualRankings = routeRankingService.getShortestRoutes();

        // Assert
        assertNotNull(actualRankings);
        assertEquals(expectedRankings.size(), actualRankings.size());
        assertEquals(expectedRankings, actualRankings);
        verify(routeRankingRepository, times(1)).findShortestRoutes();
    }

    @Test
    void testGetShortestRoutes_Empty() {
        // Arrange
        when(routeRankingRepository.findShortestRoutes()).thenReturn(Collections.emptyList());

        // Act
        List<RouteRanking> actualRankings = routeRankingService.getShortestRoutes();

        // Assert
        assertNotNull(actualRankings);
        assertEquals(0, actualRankings.size());
        verify(routeRankingRepository, times(1)).findShortestRoutes();
    }

    @Test
    void testGetSafestRoutes() {
        // Arrange
        List<RouteRanking> expectedRankings = Arrays.asList(
                new RouteRanking("R005", "Safest Route 1", 8.0, 30.0, 5, "UserE"),
                new RouteRanking("R006", "Safest Route 2", 7.5, 28.0, 5, "UserF")
        );
        when(routeRankingRepository.findSafestRoutes()).thenReturn(expectedRankings);

        // Act
        List<RouteRanking> actualRankings = routeRankingService.getSafestRoutes();

        // Assert
        assertNotNull(actualRankings);
        assertEquals(expectedRankings.size(), actualRankings.size());
        assertEquals(expectedRankings, actualRankings);
        verify(routeRankingRepository, times(1)).findSafestRoutes();
    }

    @Test
    void testGetSafestRoutes_Empty() {
        // Arrange
        when(routeRankingRepository.findSafestRoutes()).thenReturn(Collections.emptyList());

        // Act
        List<RouteRanking> actualRankings = routeRankingService.getSafestRoutes();

        // Assert
        assertNotNull(actualRankings);
        assertEquals(0, actualRankings.size());
        verify(routeRankingRepository, times(1)).findSafestRoutes();
    }
}
