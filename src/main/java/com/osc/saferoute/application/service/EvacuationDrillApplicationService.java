package com.osc.saferoute.application.service;

import com.osc.saferoute.domain.model.EvacuationDrill;
import com.osc.saferoute.domain.repository.EvacuationDrillRepository;
import org.springframework.stereotype.Service;

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
}
