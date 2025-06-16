package com.osc.saferoute.infrastructure.mybatis.repository;

import com.osc.saferoute.domain.model.PointHistory;
import com.osc.saferoute.domain.repository.PointHistoryRepository;
import com.osc.saferoute.infrastructure.mybatis.entity.PointHistoryEntity;
import com.osc.saferoute.infrastructure.mybatis.mapper.PointHistoryMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository // Spring annotation to indicate a repository component
public class PointHistoryRepositoryImpl implements PointHistoryRepository {

    private final PointHistoryMapper pointHistoryMapper;

    // Constructor injection
    public PointHistoryRepositoryImpl(PointHistoryMapper pointHistoryMapper) {
        this.pointHistoryMapper = pointHistoryMapper;
    }

    @Override
    public List<PointHistory> findByUserId(Long userId) {
        List<PointHistoryEntity> entities = pointHistoryMapper.findByUserId(userId);
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    // Private helper method to convert Entity to Domain object
    private PointHistory toDomain(PointHistoryEntity entity) {
        if (entity == null) {
            return null;
        }
        // Ensure Timestamp is converted to LocalDateTime for the domain model
        LocalDateTime transactionDateTime = entity.getTransactionDatetime() != null ?
                                            entity.getTransactionDatetime().toLocalDateTime() : null;
        return new PointHistory(
                entity.getHistoryId(),
                transactionDateTime,
                entity.getReasonDetails(),
                entity.getPointsChange()
        );
    }
}
