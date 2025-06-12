package com.osc.saferoute.domain.repository;

import com.osc.saferoute.domain.model.User;
import com.osc.saferoute.domain.model.UserId;

public interface UserRepository {
    User findById(UserId id);
    void save(User user);
    Integer findPointsByUserId(com.osc.saferoute.domain.model.UserId userId);
}
