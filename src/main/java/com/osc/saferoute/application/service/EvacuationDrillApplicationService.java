package com.osc.saferoute.application.service;

import com.osc.saferoute.domain.model.EvacuationDrill;
import com.osc.saferoute.domain.repository.EvacuationDrillRepository;

import com.osc.saferoute.controller.dto.PastEvacuationDrillDto;
import com.osc.saferoute.controller.dto.UpcomingEvacuationDrillDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvacuationDrillApplicationService {

    private final EvacuationDrillRepository evacuationDrillRepository;

    public EvacuationDrillApplicationService(EvacuationDrillRepository evacuationDrillRepository) {
        this.evacuationDrillRepository = evacuationDrillRepository;
    }

    public Optional<EvacuationDrill> getLatestScheduledDrill() {
        return evacuationDrillRepository.findLatestScheduledDrill();
    }

    public List<UpcomingEvacuationDrillDto> getUpcomingDrills(String userId) {
        // The repository implementation now handles passing LocalDateTime.now() to the mapper.
        return evacuationDrillRepository.findUpcomingDrillsWithUserStatus(userId);
    }

    public List<PastEvacuationDrillDto> getPastDrills(String userId) {
        return evacuationDrillRepository.findPastDrillsWithUserStatus(userId);
    }
}
