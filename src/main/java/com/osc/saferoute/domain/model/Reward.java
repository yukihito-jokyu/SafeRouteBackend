package com.osc.saferoute.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward {

    private Long rewardId;
    private String rewardName;
    private String rewardDescription;
    private Integer requiredPoints;
    private String category;

}
