package com.osc.saferoute.controller;

import com.osc.saferoute.application.service.RewardService;
import com.osc.saferoute.domain.model.Reward;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping
    public ResponseEntity<List<Reward>> getAllRewards() {
        try {
            List<Reward> rewards = rewardService.getRewardsList();
            return ResponseEntity.ok(rewards);
        } catch (Exception e) {
            // Log the exception details here
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
