package com.osc.saferoute.application.service;

import com.osc.saferoute.domain.model.Reward;
import com.osc.saferoute.domain.repository.RewardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public List<Reward> getRewardsList() {
        return rewardRepository.findAllRewards();
    }
}
