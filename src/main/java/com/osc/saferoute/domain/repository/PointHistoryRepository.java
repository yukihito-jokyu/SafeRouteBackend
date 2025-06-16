package com.osc.saferoute.domain.repository;

import com.osc.saferoute.domain.model.PointHistory;
import java.util.List;

public interface PointHistoryRepository {
    List<PointHistory> findByUserId(Long userId);
}
