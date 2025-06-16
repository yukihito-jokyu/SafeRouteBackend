package com.osc.saferoute.application.service;

import com.osc.saferoute.controller.dto.PastEvacuationDrillDto;
import com.osc.saferoute.domain.repository.EvacuationDrillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EvacuationDrillApplicationServiceTest {

    @Mock
    private EvacuationDrillRepository evacuationDrillRepository;

    @InjectMocks
    private EvacuationDrillApplicationService evacuationDrillApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPastDrills_returnsListOfDrills_whenRepositoryReturnsDrills() {
        Long userId = 1L;
        List<PastEvacuationDrillDto> expectedDrills = new ArrayList<>();
        expectedDrills.add(new PastEvacuationDrillDto(1, "Past Drill 1", LocalDateTime.now().minusDays(1), "Meeting Point A", "Details A", "Audience A", "attended"));
        expectedDrills.add(new PastEvacuationDrillDto(2, "Past Drill 2", LocalDateTime.now().minusDays(2), "Meeting Point B", "Details B", "Audience B", "absent"));

        when(evacuationDrillRepository.findPastDrillsWithUserStatus(userId)).thenReturn(expectedDrills);

        List<PastEvacuationDrillDto> actualDrills = evacuationDrillApplicationService.getPastDrills(userId);

        assertNotNull(actualDrills);
        assertEquals(expectedDrills.size(), actualDrills.size());
        assertEquals(expectedDrills.get(0).getDrill_name(), actualDrills.get(0).getDrill_name());
        assertEquals(expectedDrills.get(1).getDrill_name(), actualDrills.get(1).getDrill_name());
    }

    @Test
    void getPastDrills_returnsEmptyList_whenRepositoryReturnsEmptyList() {
        Long userId = 1L;
        when(evacuationDrillRepository.findPastDrillsWithUserStatus(userId)).thenReturn(Collections.emptyList());

        List<PastEvacuationDrillDto> actualDrills = evacuationDrillApplicationService.getPastDrills(userId);

        assertNotNull(actualDrills);
        assertTrue(actualDrills.isEmpty());
    }

    @Test
    void getPastDrills_returnsNull_whenRepositoryReturnsNull() {
        //This test reflects the current behavior of the service.
        //If the repository returns null, the service method will also return null.
        //The controller handles the null case and returns 204 No Content.
        Long userId = 1L;
        when(evacuationDrillRepository.findPastDrillsWithUserStatus(userId)).thenReturn(null);

        List<PastEvacuationDrillDto> actualDrills = evacuationDrillApplicationService.getPastDrills(userId);
        assertNull(actualDrills);
    }
}
