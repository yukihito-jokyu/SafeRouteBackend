package com.osc.saferoute.application.service;

import com.osc.saferoute.domain.model.UpcomingDrill;
import com.osc.saferoute.domain.repository.UpcomingDrillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpcomingDrillApplicationService {

    private final UpcomingDrillRepository upcomingDrillRepository;

    public UpcomingDrillApplicationService(UpcomingDrillRepository upcomingDrillRepository) {
        this.upcomingDrillRepository = upcomingDrillRepository;
    }

    public List<UpcomingDrill> getUpcomingDrills() {
        return upcomingDrillRepository.findUpcomingDrills();
    }
}
