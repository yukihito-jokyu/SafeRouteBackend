package com.osc.saferoute.domain.model;

public class EvacuationRoute {

    private String routeId;
    private String routeName;
    private boolean myRouteFlag;

    // Constructors
    public EvacuationRoute() {
    }

    public EvacuationRoute(String routeId, String routeName, boolean myRouteFlag) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.myRouteFlag = myRouteFlag;
    }

    // Getters and Setters
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

    public boolean isMyRouteFlag() {
        return myRouteFlag;
    }

    public void setMyRouteFlag(boolean myRouteFlag) {
        this.myRouteFlag = myRouteFlag;
    }

    // toString, equals, hashCode (optional but good practice)
    @Override
    public String toString() {
        return "EvacuationRoute{" +
                "routeId='" + routeId + '\'' +
                ", routeName='" + routeName + '\'' +
                ", myRouteFlag=" + myRouteFlag +
                '}';
    }
}
