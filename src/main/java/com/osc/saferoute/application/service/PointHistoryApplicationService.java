package com.osc.saferoute.application.service;

import com.osc.saferoute.domain.model.PointHistory;
import com.osc.saferoute.domain.repository.PointHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Optional: if reads need to be transactional

import java.util.List;

@Service // Spring annotation to indicate a service component
public class PointHistoryApplicationService {

    private final PointHistoryRepository pointHistoryRepository;

    // Constructor injection
    public PointHistoryApplicationService(PointHistoryRepository pointHistoryRepository) {
        this.pointHistoryRepository = pointHistoryRepository;
    }

    // Read operations are often transactional, though it might be managed at a higher level or by default
    @Transactional(readOnly = true)
    public List<PointHistory> getPointHistoryForUser(Long userId) {
        // Basic validation can be added here (e.g., if userId is null)
        if (userId == null) {
            // Or throw a custom exception, e.g., InvalidArgumentException
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        return pointHistoryRepository.findByUserId(userId);
    }
}
