package com.osc.saferoute.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class PointHistory {

    private final Long historyId;
    private final LocalDateTime transactionDatetime;
    private final String reasonDetails;
    private final int pointsChange;

    public PointHistory(Long historyId, LocalDateTime transactionDatetime, String reasonDetails, int pointsChange) {
        // Consider adding validation logic here if necessary (e.g., reasonDetails not null)
        this.historyId = historyId;
        this.transactionDatetime = transactionDatetime;
        this.reasonDetails = reasonDetails;
        this.pointsChange = pointsChange;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointHistory that = (PointHistory) o;
        return pointsChange == that.pointsChange &&
               Objects.equals(historyId, that.historyId) &&
               Objects.equals(transactionDatetime, that.transactionDatetime) &&
               Objects.equals(reasonDetails, that.reasonDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyId, transactionDatetime, reasonDetails, pointsChange);
    }

    @Override
    public String toString() {
        return "PointHistory{" +
               "historyId=" + historyId +
               ", transactionDatetime=" + transactionDatetime +
               ", reasonDetails='" + reasonDetails + '\'' +
               ", pointsChange=" + pointsChange +
               '}';
    }
}
