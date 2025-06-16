package com.osc.saferoute.domain.repository;

import com.osc.saferoute.domain.model.Reward;
import java.util.List;

public interface RewardRepository {

    List<Reward> findAllRewards();

}
