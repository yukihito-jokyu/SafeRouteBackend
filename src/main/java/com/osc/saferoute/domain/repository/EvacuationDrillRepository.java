package com.osc.saferoute.domain.repository;

import com.osc.saferoute.domain.model.EvacuationDrill;
import java.util.Optional;

public interface EvacuationDrillRepository {
    Optional<EvacuationDrill> findLatestScheduledDrill();
}
