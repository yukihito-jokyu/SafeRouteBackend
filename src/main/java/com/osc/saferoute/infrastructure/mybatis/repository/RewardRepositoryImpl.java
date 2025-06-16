package com.osc.saferoute.infrastructure.mybatis.repository;

import com.osc.saferoute.controller.RewardController;
import com.osc.saferoute.domain.model.Reward;
import com.osc.saferoute.domain.repository.RewardRepository;
import com.osc.saferoute.infrastructure.mybatis.entity.RewardEntity;
import com.osc.saferoute.infrastructure.mybatis.mapper.RewardMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class RewardRepositoryImpl implements RewardRepository {

    private final RewardMapper rewardMapper;

    private static final Logger logger = LoggerFactory.getLogger(RewardRepositoryImpl.class);

    public RewardRepositoryImpl(RewardMapper rewardMapper) {
        this.rewardMapper = rewardMapper;
    }

    @Override
    public List<Reward> findAllRewards() {
        List<RewardEntity> rewards = rewardMapper.selectAllRewards();
        logger.info("rewards: {}", rewards);
        return rewards.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private Reward toDomain(RewardEntity entity) {
        logger.info("entity: {}", entity);
        if (entity == null) {
            return null;
        }

        return new Reward(
                entity.getRewardId(),
                entity.getRewardName(),
                entity.getRewardDescription(),
                entity.getRequiredPoints(),
                entity.getCategory()
        );
    }
}
