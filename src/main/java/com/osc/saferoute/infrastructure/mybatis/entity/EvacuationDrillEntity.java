package com.osc.saferoute.infrastructure.mybatis.entity;

import java.time.LocalDateTime;

public class EvacuationDrillEntity {
    private Long drill_id; // Matched with column name
    private String drill_name; // Matched with column name
    private LocalDateTime start_datetime; // Matched with column name
    private String drill_type; // Matched with column name

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
}
