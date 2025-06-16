package com.osc.saferoute.controller.dto;

import java.time.LocalDateTime;

public class UpcomingEvacuationDrillDto {
    private final Long drillId;
    private final String drillName;
    private final LocalDateTime startDatetime;
    private final String drillType;
    private final String meetingPlace;
    private final String drillDetails;
    private final String targetAudience;
    private final String mapInfoUrl;
    private final String itemsToBring;
    private final String notes;
    private final String userRegistrationStatus;

    public UpcomingEvacuationDrillDto(Long drillId, String drillName, LocalDateTime startDatetime, String drillType,
                                      String meetingPlace, String drillDetails, String targetAudience,
                                      String mapInfoUrl, String itemsToBring, String notes,
                                      String userRegistrationStatus) {
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
        this.userRegistrationStatus = userRegistrationStatus;
    }

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

    public String getUserRegistrationStatus() {
        return userRegistrationStatus;
    }
}
