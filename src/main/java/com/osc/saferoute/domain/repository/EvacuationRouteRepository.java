package com.osc.saferoute.domain.repository;

import com.osc.saferoute.domain.model.EvacuationRoute;
import com.osc.saferoute.domain.model.RouteRankingData; // Added import
import java.util.List;

public interface EvacuationRouteRepository {

    /**
     * Finds all primary evacuation routes for a given user.
     * The 'primary' status is determined by the 'my_route_flag'.
     *
     * @param userId The ID of the user whose primary routes are to be fetched.
     * @return A list of EvacuationRoute objects that are marked as primary for the user.
     *         Returns an empty list if no primary routes are found.
     */
    List<EvacuationRoute> findPrimaryByUserId(String userId);

    /**
     * Finds route ranking data based on specified criteria.
     *
     * @param criteria The criteria to filter and rank routes (e.g., "fastest", "shortest", "safest").
     * @return A list of RouteRankingData objects.
     *         Returns an empty list if no data matches the criteria.
     */
    List<RouteRankingData> findRouteRankingDataByCriteria(String criteria);
}
