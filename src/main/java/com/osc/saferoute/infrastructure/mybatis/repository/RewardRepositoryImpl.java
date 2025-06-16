package com.osc.saferoute.infrastructure.mybatis.repository;

import com.osc.saferoute.domain.model.Reward;
import com.osc.saferoute.domain.repository.RewardRepository;
import com.osc.saferoute.infrastructure.mybatis.mapper.RewardMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RewardRepositoryImpl implements RewardRepository {

    private final RewardMapper rewardMapper;

    public RewardRepositoryImpl(RewardMapper rewardMapper) {
        this.rewardMapper = rewardMapper;
    }

    @Override
    public List<Reward> findAllRewards() {
        return rewardMapper.selectAllRewards();
    }
}
