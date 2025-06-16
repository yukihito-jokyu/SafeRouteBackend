package com.osc.saferoute.controller;

import com.osc.saferoute.application.service.UserApplicationService;
import com.osc.saferoute.domain.model.EvacuationRoute; // Added import
import com.osc.saferoute.domain.model.UserId;
import com.osc.saferoute.domain.model.UserName;
import org.springframework.http.ResponseEntity; // Added import
import org.springframework.web.bind.annotation.*;

import java.util.List; // Added import

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PutMapping("/{id}/name")
    public void changeUserName(@PathVariable String id, @RequestBody String newName) {
        userApplicationService.changeUserName(new UserId(id), new UserName(newName));
    }

    @GetMapping("/{id}")
    public String getUserName(@PathVariable String id) {
        return userApplicationService.getUserName(new UserId(id));
    }

    @GetMapping("/get/point/{id}")
    public Integer getUserPoints(@PathVariable String id) {
        return userApplicationService.getCurrentPoints(new com.osc.saferoute.domain.model.UserId(id));
    }

    // New endpoint to get primary evacuation routes for a user
    @GetMapping("/{userId}/get/evacuation-routes")
    public ResponseEntity<List<EvacuationRoute>> getPrimaryEvacuationRoutes(@PathVariable String userId) {
        List<EvacuationRoute> routes = userApplicationService.getPrimaryEvacuationRoutes(userId);
        if (routes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Or ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(routes);
    }
}