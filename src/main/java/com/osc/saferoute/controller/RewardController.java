package com.osc.saferoute.controller;

import com.osc.saferoute.application.service.RewardService;
import com.osc.saferoute.domain.model.Reward;
import com.osc.saferoute.infrastructure.mybatis.repository.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    private static final Logger logger = LoggerFactory.getLogger(RewardController.class);

    @GetMapping
    public ResponseEntity<List<Reward>> getAllRewards() {
        try {
            logger.info("rewards start");
            List<Reward> rewards = rewardService.getRewardsList();
            logger.info("rewards: {}", rewards);
            return ResponseEntity.ok(rewards);
        } catch (Exception e) {
            // Log the exception details here
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
