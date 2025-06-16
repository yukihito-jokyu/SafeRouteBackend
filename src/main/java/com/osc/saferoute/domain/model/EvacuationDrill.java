package com.osc.saferoute.domain.model;

import java.time.LocalDateTime;

public class EvacuationDrill {
    private final Long drillId;
    private final String drillName;
    private final LocalDateTime startDatetime;
    private final String drillType;

    public EvacuationDrill(Long drillId, String drillName, LocalDateTime startDatetime, String drillType) {
        this.drillId = drillId;
        this.drillName = drillName;
        this.startDatetime = startDatetime;
        this.drillType = drillType;
    }

    // Getters
    public Long getDrillId() {
        return drillId;
    }

    public String getDrillName() {
        return drillName;
    }

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    public String getDrillType() {
        return drillType;
    }

    // Consider adding equals, hashCode, and toString if appropriate
}
