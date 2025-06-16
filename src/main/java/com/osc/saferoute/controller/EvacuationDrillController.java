package com.osc.saferoute.controller;

import com.osc.saferoute.application.service.EvacuationDrillApplicationService;
import com.osc.saferoute.domain.model.EvacuationDrill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/evacuation-drills")
public class EvacuationDrillController {

    private final EvacuationDrillApplicationService evacuationDrillApplicationService;

    public EvacuationDrillController(EvacuationDrillApplicationService evacuationDrillApplicationService) {
        this.evacuationDrillApplicationService = evacuationDrillApplicationService;
    }

    @GetMapping("/latest")
    public ResponseEntity<EvacuationDrill> getLatestEvacuationDrill() {
        Optional<EvacuationDrill> drillOptional = evacuationDrillApplicationService.getLatestScheduledDrill();
        return drillOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
