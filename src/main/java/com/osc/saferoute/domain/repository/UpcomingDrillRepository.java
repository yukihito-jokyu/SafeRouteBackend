package com.osc.saferoute.domain.repository;

import com.osc.saferoute.domain.model.UpcomingDrill;
import java.util.List;

public interface UpcomingDrillRepository {

    List<UpcomingDrill> findUpcomingDrills();

}
