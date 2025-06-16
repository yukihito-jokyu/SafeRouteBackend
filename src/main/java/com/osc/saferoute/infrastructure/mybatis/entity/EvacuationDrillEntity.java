package com.osc.saferoute.infrastructure.mybatis.entity;

import java.time.LocalDateTime;

public class EvacuationDrillEntity {
    private Long drill_id; // Matched with column name
    private String drill_name; // Matched with column name
    private LocalDateTime start_datetime; // Matched with column name
    private String drill_type; // Matched with column name
    private String meeting_place;
    private String drill_details;
    private String target_audience;
    private String map_info_url;
    private String items_to_bring;
    private String notes;
    private String userRegistrationStatus; // Populated by query

    // Getters and Setters
    public Long getDrill_id() {
        return drill_id;
    }

    public void setDrill_id(Long drill_id) {
        this.drill_id = drill_id;
    }

    public String getDrill_name() {
        return drill_name;
    }

    public void setDrill_name(String drill_name) {
        this.drill_name = drill_name;
    }

    public LocalDateTime getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(LocalDateTime start_datetime) {
        this.start_datetime = start_datetime;
    }

    public String getDrill_type() {
        return drill_type;
    }

    public void setDrill_type(String drill_type) {
        this.drill_type = drill_type;
    }

    public String getMeeting_place() {
        return meeting_place;
    }

    public void setMeeting_place(String meeting_place) {
        this.meeting_place = meeting_place;
    }

    public String getDrill_details() {
        return drill_details;
    }

    public void setDrill_details(String drill_details) {
        this.drill_details = drill_details;
    }

    public String getTarget_audience() {
        return target_audience;
    }

    public void setTarget_audience(String target_audience) {
        this.target_audience = target_audience;
    }

    public String getMap_info_url() {
        return map_info_url;
    }

    public void setMap_info_url(String map_info_url) {
        this.map_info_url = map_info_url;
    }

    public String getItems_to_bring() {
        return items_to_bring;
    }

    public void setItems_to_bring(String items_to_bring) {
        this.items_to_bring = items_to_bring;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUserRegistrationStatus() {
        return userRegistrationStatus;
    }

    public void setUserRegistrationStatus(String userRegistrationStatus) {
        this.userRegistrationStatus = userRegistrationStatus;
    }
}
