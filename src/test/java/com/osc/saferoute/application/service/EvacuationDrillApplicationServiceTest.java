package com.osc.saferoute.application.service;

import com.osc.saferoute.controller.dto.UpcomingEvacuationDrillDto;
import com.osc.saferoute.domain.model.EvacuationDrill;
import com.osc.saferoute.domain.repository.EvacuationDrillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EvacuationDrillApplicationServiceTest { // Changed to class for consistency, was public class

    @Mock
    private EvacuationDrillRepository evacuationDrillRepository;

    @InjectMocks
    private EvacuationDrillApplicationService evacuationDrillApplicationService;

    // Fields for new tests
    private UpcomingEvacuationDrillDto drillDto1;
    private UpcomingEvacuationDrillDto drillDto2;

    // Fields for existing tests (updated constructor)
    private EvacuationDrill sampleDomainDrill;


    @BeforeEach
    void setUp() {
        // Initialize DTOs for new tests
        drillDto1 = new UpcomingEvacuationDrillDto(1L, "Drill 1", LocalDateTime.now().plusDays(1), "Type A", "Meeting A", "Details A", "Audience A", "mapA.url", "Items A", "Notes A", "Registered");
        drillDto2 = new UpcomingEvacuationDrillDto(2L, "Drill 2", LocalDateTime.now().plusDays(2), "Type B", "Meeting B", "Details B", "Audience B", "mapB.url", "Items B", "Notes B", "Not Registered");

        // Initialize domain model for existing tests with all fields
        sampleDomainDrill = new EvacuationDrill(
            1L, "Test Drill", LocalDateTime.of(2024, 8, 1, 10, 0, 0), "TypeA",
            "Main Hall", "Standard procedure", "All Staff", "http://maps.example.com/drill1",
            "ID Card", "Follow instructions"
        );
    }

    // Existing tests (updated to use sampleDomainDrill from setUp)
    @Test
    void getLatestScheduledDrill_shouldReturnDrill_whenRepositoryReturnsDrill() {
        when(evacuationDrillRepository.findLatestScheduledDrill()).thenReturn(Optional.of(sampleDomainDrill));

        Optional<EvacuationDrill> result = evacuationDrillApplicationService.getLatestScheduledDrill();

        assertTrue(result.isPresent());
        assertEquals(sampleDomainDrill, result.get());
        verify(evacuationDrillRepository).findLatestScheduledDrill();
    }

    @Test
    void getLatestScheduledDrill_shouldReturnEmpty_whenRepositoryReturnsEmpty() {
        when(evacuationDrillRepository.findLatestScheduledDrill()).thenReturn(Optional.empty());

        Optional<EvacuationDrill> result = evacuationDrillApplicationService.getLatestScheduledDrill();

        assertTrue(result.isEmpty()); // Changed from assertTrue(result.isEmpty()); to make it more explicit
        verify(evacuationDrillRepository).findLatestScheduledDrill();
    }

    // New tests for getUpcomingDrills
    @Test
    void getUpcomingDrills_shouldReturnListOfDrills_whenUserIdProvided() {
        Long userId = 123L;
        List<UpcomingEvacuationDrillDto> expectedDrills = Arrays.asList(drillDto1, drillDto2);

        when(evacuationDrillRepository.findUpcomingDrillsWithUserStatus(userId)).thenReturn(expectedDrills);

        List<UpcomingEvacuationDrillDto> actualDrills = evacuationDrillApplicationService.getUpcomingDrills(userId);

        assertNotNull(actualDrills);
        assertEquals(2, actualDrills.size());
        assertEquals(expectedDrills, actualDrills);
        verify(evacuationDrillRepository, times(1)).findUpcomingDrillsWithUserStatus(userId);
    }

    @Test
    void getUpcomingDrills_shouldReturnListOfDrills_whenUserIdIsNull() {
        Long userId = null;
        List<UpcomingEvacuationDrillDto> expectedDrills = Arrays.asList(drillDto1); // Example with one drill

        when(evacuationDrillRepository.findUpcomingDrillsWithUserStatus(userId)).thenReturn(expectedDrills);

        List<UpcomingEvacuationDrillDto> actualDrills = evacuationDrillApplicationService.getUpcomingDrills(userId);

        assertNotNull(actualDrills);
        assertEquals(1, actualDrills.size());
        assertEquals(expectedDrills, actualDrills);
        verify(evacuationDrillRepository, times(1)).findUpcomingDrillsWithUserStatus(userId);
    }

    @Test
    void getUpcomingDrills_shouldReturnEmptyList_whenRepositoryReturnsEmpty() {
        Long userId = 456L;
        when(evacuationDrillRepository.findUpcomingDrillsWithUserStatus(userId)).thenReturn(Collections.emptyList());

        List<UpcomingEvacuationDrillDto> actualDrills = evacuationDrillApplicationService.getUpcomingDrills(userId);

        assertNotNull(actualDrills);
        assertTrue(actualDrills.isEmpty()); // Changed from assertEquals(0, actualDrills.size());
        verify(evacuationDrillRepository, times(1)).findUpcomingDrillsWithUserStatus(userId);
    }
}
