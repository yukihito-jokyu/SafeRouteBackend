package com.osc.saferoute.infrastructure.mybatis.mapper;

import com.osc.saferoute.domain.model.EvacuationRoute; // Using domain model directly
import com.osc.saferoute.domain.model.RouteRankingData; // Added import
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface EvacuationRouteMapper {

    /**
     * Selects all primary evacuation routes for a given user from the database.
     * The 'primary' status is determined by the 'my_route_flag'.
     * This method will be mapped to an SQL query defined in an XML file.
     *
     * @param userId The ID of the user whose primary routes are to be fetched.
     * @return A list of EvacuationRoute objects that are marked as primary for the user.
     */
    List<EvacuationRoute> findPrimaryByUserId(@Param("userId") String userId);

    /**
     * Finds route ranking data based on specified criteria.
     * This method corresponds to the select statement with id="findRouteRankingDataByCriteria" in EvacuationRouteMapper.xml.
     *
     * @param criteria The criteria to filter and rank routes (e.g., "fastest", "shortest", "safest").
     * @return A list of RouteRankingData objects.
     */
    List<RouteRankingData> findRouteRankingDataByCriteria(@Param("criteria") String criteria);
}
