package com.osc.saferoute.controller;

import com.osc.saferoute.application.service.EvacuationDrillApplicationService;
import com.osc.saferoute.controller.dto.UpcomingEvacuationDrillDto;
import com.osc.saferoute.domain.model.EvacuationDrill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
package com.osc.saferoute.controller;

import com.osc.saferoute.application.service.EvacuationDrillApplicationService;
import com.osc.saferoute.controller.dto.PastEvacuationDrillDto;
import com.osc.saferoute.controller.dto.UpcomingEvacuationDrillDto;
import com.osc.saferoute.domain.model.EvacuationDrill;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @GetMapping("/upcoming")
    public ResponseEntity<List<UpcomingEvacuationDrillDto>> getUpcomingEvacuationDrills(
            @RequestParam(value = "userId", required = false) Long userId) {
        List<UpcomingEvacuationDrillDto> upcomingDrills = evacuationDrillApplicationService.getUpcomingDrills(userId);
        if (upcomingDrills == null || upcomingDrills.isEmpty()) { // defensive null check
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(upcomingDrills);
    }

    @GetMapping("/past")
    public ResponseEntity<List<PastEvacuationDrillDto>> getPastEvacuationDrills(@RequestParam(value = "userId", required = true) Long userId) {
        List<PastEvacuationDrillDto> drills = evacuationDrillApplicationService.getPastDrills(userId);
        if (drills == null || drills.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(drills);
    }
}
