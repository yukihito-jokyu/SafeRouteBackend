package com.osc.saferoute.application.service;

import com.osc.saferoute.domain.model.EvacuationDrill;
import com.osc.saferoute.domain.repository.EvacuationDrillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EvacuationDrillApplicationServiceTest {

    @Mock
    private EvacuationDrillRepository evacuationDrillRepository;

    @InjectMocks
    private EvacuationDrillApplicationService evacuationDrillApplicationService;

    @Test
    void getLatestScheduledDrill_shouldReturnDrill_whenRepositoryReturnsDrill() {
        LocalDateTime startTime = LocalDateTime.of(2024, 8, 1, 10, 0, 0);
        EvacuationDrill drill = new EvacuationDrill(1L, "Test Drill", startTime, "TypeA");
        when(evacuationDrillRepository.findLatestScheduledDrill()).thenReturn(Optional.of(drill));

        Optional<EvacuationDrill> result = evacuationDrillApplicationService.getLatestScheduledDrill();

        assertTrue(result.isPresent());
        assertEquals(drill, result.get());
        verify(evacuationDrillRepository).findLatestScheduledDrill();
    }

    @Test
    void getLatestScheduledDrill_shouldReturnEmpty_whenRepositoryReturnsEmpty() {
        when(evacuationDrillRepository.findLatestScheduledDrill()).thenReturn(Optional.empty());

        Optional<EvacuationDrill> result = evacuationDrillApplicationService.getLatestScheduledDrill();

        assertTrue(result.isEmpty());
        verify(evacuationDrillRepository).findLatestScheduledDrill();
    }
}
