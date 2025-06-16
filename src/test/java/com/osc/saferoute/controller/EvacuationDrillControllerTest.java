package com.osc.saferoute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.osc.saferoute.application.service.EvacuationDrillApplicationService;
import com.osc.saferoute.controller.dto.PastEvacuationDrillDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EvacuationDrillController.class)
public class EvacuationDrillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvacuationDrillApplicationService evacuationDrillApplicationService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Register JavaTimeModule to handle LocalDateTime serialization/deserialization
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getPastEvacuationDrills_returnsOk_whenServiceReturnsDrills() throws Exception {
        Long userId = 1L;
        List<PastEvacuationDrillDto> expectedDrills = new ArrayList<>();
        // Using a fixed LocalDateTime for consistent JSON comparison
        LocalDateTime drillTime = LocalDateTime.of(2023, 10, 26, 10, 0, 0);
        expectedDrills.add(new PastEvacuationDrillDto(1, "Past Drill 1", drillTime, "Meeting Point A", "Details A", "Audience A", "attended"));
        expectedDrills.add(new PastEvacuationDrillDto(2, "Past Drill 2", drillTime.minusDays(1), "Meeting Point B", "Details B", "Audience B", "absent"));

        when(evacuationDrillApplicationService.getPastDrills(userId)).thenReturn(expectedDrills);

        mockMvc.perform(get("/api/v1/evacuation-drills/past")
                        .param("userId", String.valueOf(userId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedDrills)));
    }

    @Test
    void getPastEvacuationDrills_returnsNoContent_whenServiceReturnsEmptyList() throws Exception {
        Long userId = 1L;
        when(evacuationDrillApplicationService.getPastDrills(userId)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/evacuation-drills/past")
                        .param("userId", String.valueOf(userId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void getPastEvacuationDrills_returnsNoContent_whenServiceReturnsNull() throws Exception {
        Long userId = 1L;
        when(evacuationDrillApplicationService.getPastDrills(userId)).thenReturn(null);

        mockMvc.perform(get("/api/v1/evacuation-drills/past")
                        .param("userId", String.valueOf(userId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void getPastEvacuationDrills_returnsBadRequest_whenUserIdIsMissing() throws Exception {
        mockMvc.perform(get("/api/v1/evacuation-drills/past")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
