package com.osc.saferoute.infrastructure.mybatis.entity;

import lombok.Data;

@Data
public class RewardEntity {
    private Long rewardId;
    private String rewardName;
    private String rewardDescription;
    private Integer requiredPoints;
    private String category;

    public RewardEntity() {
    }

}
