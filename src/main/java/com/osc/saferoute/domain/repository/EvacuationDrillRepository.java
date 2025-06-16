package com.osc.saferoute.domain.repository;

import com.osc.saferoute.controller.dto.UpcomingEvacuationDrillDto;
import com.osc.saferoute.domain.model.EvacuationDrill;
import java.util.List;
import java.util.Optional;

public interface EvacuationDrillRepository {
    Optional<EvacuationDrill> findLatestScheduledDrill();
    List<UpcomingEvacuationDrillDto> findUpcomingDrillsWithUserStatus(Long userId);
}
