package com.osc.saferoute.infrastructure.mybatis.repository;

import com.osc.saferoute.domain.model.EvacuationDrill;
import com.osc.saferoute.infrastructure.mybatis.entity.EvacuationDrillEntity;
import com.osc.saferoute.infrastructure.mybatis.mapper.EvacuationDrillMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EvacuationDrillRepositoryImplTest {

    @Mock
    private EvacuationDrillMapper evacuationDrillMapper;

    @InjectMocks
    private EvacuationDrillRepositoryImpl evacuationDrillRepositoryImpl;

    @Test
    void findLatestScheduledDrill_shouldReturnDomainModel_whenMapperReturnsEntity() {
        LocalDateTime startTime = LocalDateTime.of(2024, 8, 1, 10, 0, 0);
        EvacuationDrillEntity entity = new EvacuationDrillEntity();
        entity.setDrill_id(1L);
        entity.setDrill_name("Test Drill");
        entity.setStart_datetime(startTime);
        entity.setDrill_type("TypeA");

        when(evacuationDrillMapper.findLatestScheduledDrill()).thenReturn(entity);

        Optional<EvacuationDrill> result = evacuationDrillRepositoryImpl.findLatestScheduledDrill();

        assertTrue(result.isPresent());
        EvacuationDrill domainModel = result.get();
        assertEquals(entity.getDrill_id(), domainModel.getDrillId());
        assertEquals(entity.getDrill_name(), domainModel.getDrillName());
        assertEquals(entity.getStart_datetime(), domainModel.getStartDatetime());
        assertEquals(entity.getDrill_type(), domainModel.getDrillType());

        verify(evacuationDrillMapper).findLatestScheduledDrill();
    }

    @Test
    void findLatestScheduledDrill_shouldReturnEmpty_whenMapperReturnsNull() {
        when(evacuationDrillMapper.findLatestScheduledDrill()).thenReturn(null);

        Optional<EvacuationDrill> result = evacuationDrillRepositoryImpl.findLatestScheduledDrill();

        assertTrue(result.isEmpty());
        verify(evacuationDrillMapper).findLatestScheduledDrill();
    }
}
