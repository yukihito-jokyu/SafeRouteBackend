package com.osc.saferoute.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.osc.saferoute.domain.model.PointHistory;

import java.time.LocalDateTime;

public class PointHistoryDto {

    private Long historyId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") // Standard date-time format for APIs
    private LocalDateTime transactionDatetime;

    private String reasonDetails;
    private int pointsChange;

    // Private constructor to enforce usage of factory method
    private PointHistoryDto(Long historyId, LocalDateTime transactionDatetime, String reasonDetails, int pointsChange) {
        this.historyId = historyId;
        this.transactionDatetime = transactionDatetime;
        this.reasonDetails = reasonDetails;
        this.pointsChange = pointsChange;
    }

    // Getters are needed for Jackson serialization
    public Long getHistoryId() {
        return historyId;
    }

    public LocalDateTime getTransactionDatetime() {
        return transactionDatetime;
    }

    public String getReasonDetails() {
        return reasonDetails;
    }

    public int getPointsChange() {
        return pointsChange;
    }

    // Static factory method for conversion from Domain object
    public static PointHistoryDto fromDomain(PointHistory domain) {
        if (domain == null) {
            return null;
        }
        return new PointHistoryDto(
                domain.getHistoryId(),
                domain.getTransactionDatetime(),
                domain.getReasonDetails(),
                domain.getPointsChange()
        );
    }
}
