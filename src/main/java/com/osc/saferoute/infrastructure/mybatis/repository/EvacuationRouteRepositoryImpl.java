package com.osc.saferoute.infrastructure.mybatis.repository;

import com.osc.saferoute.domain.model.EvacuationRoute;
import com.osc.saferoute.domain.repository.EvacuationRouteRepository;
import com.osc.saferoute.infrastructure.mybatis.mapper.EvacuationRouteMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EvacuationRouteRepositoryImpl implements EvacuationRouteRepository {

    private final EvacuationRouteMapper evacuationRouteMapper;

    public EvacuationRouteRepositoryImpl(EvacuationRouteMapper evacuationRouteMapper) {
        this.evacuationRouteMapper = evacuationRouteMapper;
    }

    @Override
    public List<EvacuationRoute> findPrimaryByUserId(String userId) {
        // The EvacuationRouteMapper is expected to return List<EvacuationRoute> directly
        // as per its definition and the resultMap in EvacuationRouteMapper.xml
        return evacuationRouteMapper.findPrimaryByUserId(userId);
    }
}
