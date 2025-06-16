package com.osc.saferoute.controller;

import com.osc.saferoute.application.service.EvacuationDrillApplicationService;
import com.osc.saferoute.application.service.UpcomingDrillApplicationService;
import com.osc.saferoute.domain.model.EvacuationDrill;
import com.osc.saferoute.domain.model.UpcomingDrill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evacuation-drills")
public class EvacuationDrillController {

    private final EvacuationDrillApplicationService evacuationDrillApplicationService;
    private final UpcomingDrillApplicationService upcomingDrillApplicationService;

    public EvacuationDrillController(
            EvacuationDrillApplicationService evacuationDrillApplicationService,
            UpcomingDrillApplicationService upcomingDrillApplicationService) {
        this.evacuationDrillApplicationService = evacuationDrillApplicationService;
        this.upcomingDrillApplicationService = upcomingDrillApplicationService;
    }

    @GetMapping("/latest")
    public ResponseEntity<EvacuationDrill> getLatestEvacuationDrill() {
        Optional<EvacuationDrill> drillOptional = evacuationDrillApplicationService.getLatestScheduledDrill();
        return drillOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<UpcomingDrill>> getUpcomingEvacuationDrills() {
        List<UpcomingDrill> upcomingDrills = upcomingDrillApplicationService.getUpcomingDrills();
        return ResponseEntity.ok(upcomingDrills);
    }
}
