package com.osc.saferoute.application.service;

import com.osc.saferoute.domain.model.*;
import com.osc.saferoute.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {
    private final UserRepository userRepository;

    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
