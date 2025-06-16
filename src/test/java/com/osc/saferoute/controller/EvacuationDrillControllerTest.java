package com.osc.saferoute.controller;

import com.osc.saferoute.application.service.EvacuationDrillApplicationService;
import com.osc.saferoute.domain.model.EvacuationDrill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(EvacuationDrillController.class)
public class EvacuationDrillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvacuationDrillApplicationService evacuationDrillApplicationService;

@Autowired
private ObjectMapper objectMapper; // Autowire Spring's ObjectMapper

    @Test
    void getLatestEvacuationDrill_shouldReturnDrill_whenFound() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2024, 8, 1, 10, 0, 0);
        EvacuationDrill drill = new EvacuationDrill(1L, "Test Drill", startTime, "TypeA");
        when(evacuationDrillApplicationService.getLatestScheduledDrill()).thenReturn(Optional.of(drill));

        mockMvc.perform(get("/evacuation-drills/latest"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().json(objectMapper.writeValueAsString(drill)));
    }

    @Test
    void getLatestEvacuationDrill_shouldReturnNotFound_whenNotFound() throws Exception {
        when(evacuationDrillApplicationService.getLatestScheduledDrill()).thenReturn(Optional.empty());

        mockMvc.perform(get("/evacuation-drills/latest"))
               .andExpect(status().isNotFound());
    }
}
