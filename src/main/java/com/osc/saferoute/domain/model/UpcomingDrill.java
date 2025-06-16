package com.osc.saferoute.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpcomingDrill {

    private String route_id;
    private String route_name;
    private boolean my_route_flag;

}
