package com.osc.saferoute.infrastructure.mybatis.entity;

import java.sql.Timestamp; // Using java.sql.Timestamp for direct MyBatis mapping

public class PointHistoryEntity {

    private Long historyId;
    private Long userId;
    private int pointsChange;
    private String reasonDetails;
    private Timestamp transactionDatetime; // MyBatis works well with Timestamp for DATETIME columns

    // Default constructor - often needed by ORMs/mappers
    public PointHistoryEntity() {
    }

    // Getters and Setters
    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPointsChange() {
        return pointsChange;
    }

    public void setPointsChange(int pointsChange) {
        this.pointsChange = pointsChange;
    }

    public String getReasonDetails() {
        return reasonDetails;
    }

    public void setReasonDetails(String reasonDetails) {
        this.reasonDetails = reasonDetails;
    }

    public Timestamp getTransactionDatetime() {
        return transactionDatetime;
    }

    public void setTransactionDatetime(Timestamp transactionDatetime) {
        this.transactionDatetime = transactionDatetime;
    }
}
