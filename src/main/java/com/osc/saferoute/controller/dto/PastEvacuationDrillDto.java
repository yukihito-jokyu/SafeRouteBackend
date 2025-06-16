package com.osc.saferoute.controller.dto;

import java.time.LocalDateTime;

public class PastEvacuationDrillDto {

    private Integer drill_id;
    private String drill_name;
    private LocalDateTime start_datetime;
    private String meeting_place;
    private String drill_details;
    private String target_audience;
    private String status;

    public PastEvacuationDrillDto() {
    }

    public PastEvacuationDrillDto(Integer drill_id, String drill_name, LocalDateTime start_datetime, String meeting_place, String drill_details, String target_audience, String status) {
        this.drill_id = drill_id;
        this.drill_name = drill_name;
        this.start_datetime = start_datetime;
        this.meeting_place = meeting_place;
        this.drill_details = drill_details;
        this.target_audience = target_audience;
        this.status = status;
    }

    public Integer getDrill_id() {
        return drill_id;
    }

    public void setDrill_id(Integer drill_id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
