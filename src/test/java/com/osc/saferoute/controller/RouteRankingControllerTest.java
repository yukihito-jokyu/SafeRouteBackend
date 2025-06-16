package com.osc.saferoute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osc.saferoute.application.service.RouteRankingService;
import com.osc.saferoute.domain.model.RouteRanking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


@WebMvcTest(RouteRankingController.class)
public class RouteRankingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteRankingService routeRankingService;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON strings if needed for complex assertions

    @Test
    void testGetRankedRoutes_Fastest() throws Exception {
        // Arrange
        List<RouteRanking> fastestRoutes = Arrays.asList(
                new RouteRanking("R001", "Fastest Route 1", 10.0, 15.0, 3, "UserA"),
                new RouteRanking("R002", "Fastest Route 2", 12.5, 18.0, 4, "UserB")
        );
        when(routeRankingService.getFastestRoutes()).thenReturn(fastestRoutes);

        // Act & Assert
        mockMvc.perform(get("/api/rankings/routes?type=fastest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].routeId", is("R001")))
                .andExpect(jsonPath("$[0].routeName", is("Fastest Route 1")))
                .andExpect(jsonPath("$[1].routeId", is("R002")))
                .andExpect(jsonPath("$[1].routeName", is("Fastest Route 2")));
    }

    @Test
    void testGetRankedRoutes_Shortest() throws Exception {
        // Arrange
        List<RouteRanking> shortestRoutes = Arrays.asList(
                new RouteRanking("R003", "Shortest Route 1", 5.0, 25.0, 5, "UserC"),
                new RouteRanking("R004", "Shortest Route 2", 6.2, 22.0, 2, "UserD")
        );
        when(routeRankingService.getShortestRoutes()).thenReturn(shortestRoutes);

        // Act & Assert
        mockMvc.perform(get("/api/rankings/routes?type=shortest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].routeId", is("R003")))
                .andExpect(jsonPath("$[0].distance", is(5.0)))
                .andExpect(jsonPath("$[1].routeId", is("R004")))
                .andExpect(jsonPath("$[1].distance", is(6.2)));
    }

    @Test
    void testGetRankedRoutes_Safest() throws Exception {
        // Arrange
        List<RouteRanking> safestRoutes = Arrays.asList(
                new RouteRanking("R005", "Safest Route 1", 8.0, 30.0, 5, "UserE"),
                new RouteRanking("R006", "Safest Route 2", 7.5, 28.0, 5, "UserF")
        );
        when(routeRankingService.getSafestRoutes()).thenReturn(safestRoutes);

        // Act & Assert
        mockMvc.perform(get("/api/rankings/routes?type=safest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].routeId", is("R005")))
                .andExpect(jsonPath("$[0].selfAssessedSafety", is(5)))
                .andExpect(jsonPath("$[1].routeId", is("R006")))
                .andExpect(jsonPath("$[1].selfAssessedSafety", is(5)));
    }

    @Test
    void testGetRankedRoutes_Safest_Empty() throws Exception {
        // Arrange
        when(routeRankingService.getSafestRoutes()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/rankings/routes?type=safest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }


    @Test
    void testGetRankedRoutes_InvalidType() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/rankings/routes?type=nonexistent"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetRankedRoutes_NoType() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/rankings/routes"))
                .andExpect(status().isBadRequest());
    }
}
