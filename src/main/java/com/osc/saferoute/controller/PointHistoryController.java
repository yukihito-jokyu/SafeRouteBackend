package com.osc.saferoute.controller;

import com.osc.saferoute.application.service.PointHistoryApplicationService;
import com.osc.saferoute.controller.dto.PointHistoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users") // Base path for user-related endpoints
public class PointHistoryController {

    private final PointHistoryApplicationService pointHistoryApplicationService;

    // Constructor injection
    public PointHistoryController(PointHistoryApplicationService pointHistoryApplicationService) {
        this.pointHistoryApplicationService = pointHistoryApplicationService;
    }

    @GetMapping("/{userId}/points/history")
    public ResponseEntity<List<PointHistoryDto>> getPointHistory(@PathVariable Long userId) {
        // Basic validation for userId can be done here or in the service
        // For example, ensuring userId is positive, etc.
        if (userId == null || userId <= 0) {
             // Returning bad request if userId is invalid
            return ResponseEntity.badRequest().build();
        }

        List<PointHistoryDto> historyDtos = pointHistoryApplicationService.getPointHistoryForUser(userId)
                .stream()
                .map(PointHistoryDto::fromDomain) // Convert domain object to DTO
                .collect(Collectors.toList());

        return ResponseEntity.ok(historyDtos);
    }
}
