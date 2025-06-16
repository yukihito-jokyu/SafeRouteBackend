package com.osc.saferoute.domain.model;

import java.util.Objects;

public class RouteRanking {

    private String routeId;
    private String routeName;
    private Double distance;
    private Double estimatedTime;
    private Integer selfAssessedSafety;
    private String userNickname;

    public RouteRanking(String routeId, String routeName, Double distance, Double estimatedTime, Integer selfAssessedSafety, String userNickname) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.selfAssessedSafety = selfAssessedSafety;
        this.userNickname = userNickname;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Integer getSelfAssessedSafety() {
        return selfAssessedSafety;
    }

    public void setSelfAssessedSafety(Integer selfAssessedSafety) {
        this.selfAssessedSafety = selfAssessedSafety;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteRanking that = (RouteRanking) o;
        return Objects.equals(routeId, that.routeId) &&
               Objects.equals(routeName, that.routeName) &&
               Objects.equals(distance, that.distance) &&
               Objects.equals(estimatedTime, that.estimatedTime) &&
               Objects.equals(selfAssessedSafety, that.selfAssessedSafety) &&
               Objects.equals(userNickname, that.userNickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, routeName, distance, estimatedTime, selfAssessedSafety, userNickname);
    }

    @Override
    public String toString() {
        return "RouteRanking{" +
               "routeId='" + routeId + '\'' +
               ", routeName='" + routeName + '\'' +
               ", distance=" + distance +
               ", estimatedTime=" + estimatedTime +
               ", selfAssessedSafety=" + selfAssessedSafety +
               ", userNickname='" + userNickname + '\'' +
               '}';
    }
}
