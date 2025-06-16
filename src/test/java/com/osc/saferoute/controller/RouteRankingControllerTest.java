package com.osc.saferoute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osc.saferoute.application.service.RouteRankingService;
import com.osc.saferoute.controller.dto.RouteRankingResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RouteRankingController.class)
class RouteRankingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteRankingService routeRankingService; // from com.osc.saferoute.application.service

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON strings if needed for debugging

    @Test
    @DisplayName("GET /api/v1/rankings/routes/fastest - Success")
    void getRouteRankings_whenFastestCriteria_shouldReturnOkAndRankedList() throws Exception {
        // Arrange
        String criteria = "fastest";
        RouteRankingResponse response1 = new RouteRankingResponse("route001", "UserA", 1500.0, 20, 4, 1);
        RouteRankingResponse response2 = new RouteRankingResponse("route002", "UserB", 1200.0, 15, 5, 2);
        List<RouteRankingResponse> mockResponseList = Arrays.asList(response1, response2);

        when(routeRankingService.getRouteRankings(criteria)).thenReturn(mockResponseList);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/rankings/routes/{criteria}", criteria)
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].routeId", is(response1.getRouteId())))
                .andExpect(jsonPath("$[0].userNickname", is(response1.getUserNickname())))
                .andExpect(jsonPath("$[0].distance", is(response1.getDistance())))
                .andExpect(jsonPath("$[0].estimatedTime", is(response1.getEstimatedTime())))
                .andExpect(jsonPath("$[0].selfAssessedSafety", is(response1.getSelfAssessedSafety())))
                .andExpect(jsonPath("$[0].rank", is(response1.getRank())))
                .andExpect(jsonPath("$[1].routeId", is(response2.getRouteId())))
                .andExpect(jsonPath("$[1].rank", is(response2.getRank())));

        verify(routeRankingService, times(1)).getRouteRankings(criteria);
    }

    @Test
    @DisplayName("GET /api/v1/rankings/routes/empty - Service returns empty list")
    void getRouteRankings_whenServiceReturnsEmptyList_shouldReturnOkAndEmptyArray() throws Exception {
        // Arrange
        String criteria = "emptyCriteria";
        when(routeRankingService.getRouteRankings(anyString())).thenReturn(Collections.emptyList());

        // Act
        ResultActions resultActions = mockMvc.perform(get("/api/v1/rankings/routes/{criteria}", criteria)
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0))); // Checks if the root is an array of size 0

        verify(routeRankingService, times(1)).getRouteRankings(criteria);
    }
}
