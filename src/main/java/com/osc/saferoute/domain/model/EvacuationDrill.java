package com.osc.saferoute.domain.model;

import java.time.LocalDateTime;

public class EvacuationDrill {
    private final Integer drillId;
    private final String drillName;
    private final LocalDateTime startDatetime;
    private final String drillType;
    private final String meetingPlace;
    private final String drillDetails;
    private final String targetAudience;
    private final String mapInfoUrl;
    private final String itemsToBring;
    private final String notes;

    public EvacuationDrill(Integer drillId, String drillName, LocalDateTime startDatetime, String drillType,
                           String meetingPlace, String drillDetails, String targetAudience,
                           String mapInfoUrl, String itemsToBring, String notes) {
        this.drillId = drillId;
        this.drillName = drillName;
        this.startDatetime = startDatetime;
        this.drillType = drillType;
        this.meetingPlace = meetingPlace;
        this.drillDetails = drillDetails;
        this.targetAudience = targetAudience;
        this.mapInfoUrl = mapInfoUrl;
        this.itemsToBring = itemsToBring;
        this.notes = notes;
    }

    // Getters
    public Integer getDrillId() {
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

    public String getMeetingPlace() {
        return meetingPlace;
    }

    public String getDrillDetails() {
        return drillDetails;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getMapInfoUrl() {
        return mapInfoUrl;
    }

    public String getItemsToBring() {
        return itemsToBring;
    }

    public String getNotes() {
        return notes;
    }

    // Consider adding equals, hashCode, and toString if appropriate
}
