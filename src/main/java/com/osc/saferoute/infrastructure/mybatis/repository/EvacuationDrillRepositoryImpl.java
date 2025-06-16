package com.osc.saferoute.infrastructure.mybatis.repository;

import com.osc.saferoute.domain.model.EvacuationDrill;
import com.osc.saferoute.domain.repository.EvacuationDrillRepository;
import com.osc.saferoute.infrastructure.mybatis.entity.EvacuationDrillEntity;
import com.osc.saferoute.infrastructure.mybatis.mapper.EvacuationDrillMapper;
import com.osc.saferoute.controller.dto.UpcomingEvacuationDrillDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
package com.osc.saferoute.infrastructure.mybatis.repository;

import com.osc.saferoute.controller.dto.PastEvacuationDrillDto;
import com.osc.saferoute.domain.model.EvacuationDrill;
import com.osc.saferoute.domain.repository.EvacuationDrillRepository;
import com.osc.saferoute.infrastructure.mybatis.entity.EvacuationDrillEntity;
import com.osc.saferoute.infrastructure.mybatis.mapper.EvacuationDrillMapper;
import com.osc.saferoute.controller.dto.UpcomingEvacuationDrillDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        // For consistency, ensure the toDomainModel also handles all new fields if used elsewhere,
        // though this subtask doesn't require modifying toDomainModel itself.
        return Optional.of(toDomainModel(entity));
    }

    private EvacuationDrill toDomainModel(EvacuationDrillEntity entity) {
        // This conversion is for the EvacuationDrill domain model, not the DTO.
        // It would need to be updated if EvacuationDrill itself now requires more fields from the entity.
        // Based on Step 2, EvacuationDrill was updated, so this method SHOULD be updated for consistency,
        // but the current subtask is about the DTO. I will proceed with DTO, but flag this.
        return new EvacuationDrill(
            entity.getDrill_id(),
            entity.getDrill_name(),
            entity.getStart_datetime(),
            entity.getDrill_type(),
            entity.getMeeting_place(), // Added as per EvacuationDrill changes in Step 2
            entity.getDrill_details(),  // Added as per EvacuationDrill changes in Step 2
            entity.getTarget_audience(),// Added as per EvacuationDrill changes in Step 2
            entity.getMap_info_url(),   // Added as per EvacuationDrill changes in Step 2
            entity.getItems_to_bring(), // Added as per EvacuationDrill changes in Step 2
            entity.getNotes()           // Added as per EvacuationDrill changes in Step 2
        );
    }

    @Override
    public List<UpcomingEvacuationDrillDto> findUpcomingDrillsWithUserStatus(Long userId) {
        List<EvacuationDrillEntity> entities = evacuationDrillMapper.findUpcomingDrills(LocalDateTime.now(), userId);
        if (entities == null) {
            return new ArrayList<>(); // Or Collections.emptyList()
        }
        return entities.stream()
            .map(entity -> new UpcomingEvacuationDrillDto(
                entity.getDrill_id(),
                entity.getDrill_name(),
                entity.getStart_datetime(),
                entity.getDrill_type(),
                entity.getMeeting_place(),
                entity.getDrill_details(),
                entity.getTarget_audience(),
                entity.getMap_info_url(),
                entity.getItems_to_bring(),
                entity.getNotes(),
                entity.getUserRegistrationStatus() // Assumed camelCase getter as per previous steps
            ))
            .collect(Collectors.toList());
    }

    @Override
    public List<PastEvacuationDrillDto> findPastDrillsWithUserStatus(Long userId) {
        List<EvacuationDrillEntity> entities = evacuationDrillMapper.findPastDrills(LocalDateTime.now(), userId);
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(entity -> new PastEvacuationDrillDto(
                        entity.getDrill_id(),
                        entity.getDrill_name(),
                        entity.getStart_datetime(),
                        entity.getMeeting_place(),
                        entity.getDrill_details(),
                        entity.getTarget_audience(),
                        entity.getUserRegistrationStatus()
                ))
                .collect(Collectors.toList());
    }
}
