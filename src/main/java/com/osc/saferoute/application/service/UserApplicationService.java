package com.osc.saferoute.application.service;

import com.osc.saferoute.domain.model.*;
import com.osc.saferoute.domain.repository.EvacuationRouteRepository; // Added import
import com.osc.saferoute.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List; // Added import

@Service
public class UserApplicationService {
    private final UserRepository userRepository;
    private final EvacuationRouteRepository evacuationRouteRepository; // Added repository

    // Updated constructor
    public UserApplicationService(UserRepository userRepository, EvacuationRouteRepository evacuationRouteRepository) {
        this.userRepository = userRepository;
        this.evacuationRouteRepository = evacuationRouteRepository; // Added assignment
    }

    public void changeUserName(UserId id, UserName newName) {
        User user = userRepository.findById(id);
        user.changeName(newName);
        userRepository.save(user);
    }

    public String getUserName(UserId id) {
        User user = userRepository.findById(id);
        return user.name().value();
    }

    public Integer getCurrentPoints(com.osc.saferoute.domain.model.UserId userId) {
        // Assuming userRepository is the instance of UserRepository injected into this class
        return userRepository.findPointsByUserId(userId);
    }

    // New method to get primary evacuation routes
    public List<EvacuationRoute> getPrimaryEvacuationRoutes(String userId) {
        // It's good practice to validate userId if necessary, e.g., check for null or empty.
        // For now, directly calling the repository.
        return evacuationRouteRepository.findPrimaryByUserId(userId);
    }
}
