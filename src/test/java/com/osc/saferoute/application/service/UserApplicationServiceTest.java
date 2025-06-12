package com.osc.saferoute.application.service;

import com.osc.saferoute.domain.model.UserId;
import com.osc.saferoute.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserApplicationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserApplicationService userApplicationService;

    @Test
    void getCurrentPoints_shouldReturnPoints_whenRepositoryReturnsPoints() {
        String userIdStr = "test-user-1";
        UserId userId = new UserId(userIdStr);
        Integer expectedPoints = 100;
        when(userRepository.findPointsByUserId(userId)).thenReturn(expectedPoints);

        Integer actualPoints = userApplicationService.getCurrentPoints(userId);

        assertEquals(expectedPoints, actualPoints);
        verify(userRepository).findPointsByUserId(userId);
    }

    @Test
    void getCurrentPoints_shouldReturnNull_whenRepositoryReturnsNull() {
        String userIdStr = "non-existent-user";
        UserId userId = new UserId(userIdStr);
        when(userRepository.findPointsByUserId(userId)).thenReturn(null);

        Integer actualPoints = userApplicationService.getCurrentPoints(userId);

        assertNull(actualPoints);
        verify(userRepository).findPointsByUserId(userId);
    }
}
