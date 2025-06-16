package com.osc.saferoute.infrastructure.mybatis;

import com.osc.saferoute.domain.model.UpcomingDrill;
import com.osc.saferoute.domain.repository.UpcomingDrillRepository;
import com.osc.saferoute.infrastructure.mybatis.entity.UpcomingDrillEntity;
import com.osc.saferoute.infrastructure.mybatis.mapper.UpcomingDrillMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UpcomingDrillRepositoryImpl implements UpcomingDrillRepository {

    private final UpcomingDrillMapper upcomingDrillMapper;

    public UpcomingDrillRepositoryImpl(UpcomingDrillMapper upcomingDrillMapper) {
        this.upcomingDrillMapper = upcomingDrillMapper;
    }

    @Override
    public List<UpcomingDrill> findUpcomingDrills() {
        List<UpcomingDrillEntity> upcomingDrillEntities = upcomingDrillMapper.findUpcomingDrills();
        return upcomingDrillEntities.stream()
                .map(entity -> new UpcomingDrill(entity.getRoute_id(), entity.getRoute_name(), entity.isMy_route_flag()))
                .collect(Collectors.toList());
    }
}
