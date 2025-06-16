package com.osc.saferoute.domain.repository;

import com.osc.saferoute.domain.model.EvacuationRoute;
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
}
