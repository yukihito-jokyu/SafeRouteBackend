package com.osc.saferoute.infrastructure.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpcomingDrillEntity {

    private String route_id;
    private String route_name;
    private boolean my_route_flag;

}
