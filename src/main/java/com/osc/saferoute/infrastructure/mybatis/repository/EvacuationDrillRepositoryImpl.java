package com.osc.saferoute.infrastructure.mybatis.repository;

import com.osc.saferoute.domain.model.EvacuationDrill;
import com.osc.saferoute.domain.repository.EvacuationDrillRepository;
import com.osc.saferoute.infrastructure.mybatis.entity.EvacuationDrillEntity;
import com.osc.saferoute.infrastructure.mybatis.mapper.EvacuationDrillMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Or @Component
public class EvacuationDrillRepositoryImpl implements EvacuationDrillRepository {

    private final EvacuationDrillMapper evacuationDrillMapper;

    public EvacuationDrillRepositoryImpl(EvacuationDrillMapper evacuationDrillMapper) {
        this.evacuationDrillMapper = evacuationDrillMapper;
    }

    @Override
    public Optional<EvacuationDrill> findLatestScheduledDrill() {
        EvacuationDrillEntity entity = evacuationDrillMapper.findLatestScheduledDrill();
        if (entity == null) {
            return Optional.empty();
        }
        return Optional.of(toDomainModel(entity));
    }

    private EvacuationDrill toDomainModel(EvacuationDrillEntity entity) {
        return new EvacuationDrill(
            entity.getDrill_id(),
            entity.getDrill_name(),
            entity.getStart_datetime(),
            entity.getDrill_type()
        );
    }
}
