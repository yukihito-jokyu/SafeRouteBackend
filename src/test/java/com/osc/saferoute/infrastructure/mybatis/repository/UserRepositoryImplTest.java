package com.osc.saferoute.infrastructure.mybatis.repository;

import com.osc.saferoute.domain.model.UserId;
import com.osc.saferoute.infrastructure.mybatis.mapper.UserMapper;
// Assuming UserEntity and its toDomain method or a similar conversion exists if UserMapper returns UserEntity
// For findPointsById, UserMapper returns Integer directly, so no entity conversion is needed here.
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
public class UserRepositoryImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    @Test
    void findPointsByUserId_shouldReturnPoints_whenMapperReturnsPoints() {
        String userIdStr = "test-user-1";
        UserId domainUserId = new UserId(userIdStr);
        Integer expectedPoints = 100;

        when(userMapper.findPointsById(userIdStr)).thenReturn(expectedPoints);

        Integer actualPoints = userRepositoryImpl.findPointsByUserId(domainUserId);

        assertEquals(expectedPoints, actualPoints);
        verify(userMapper).findPointsById(userIdStr);
    }

    @Test
    void findPointsByUserId_shouldReturnNull_whenMapperReturnsNull() {
        String userIdStr = "non-existent-user";
        UserId domainUserId = new UserId(userIdStr);

        when(userMapper.findPointsById(userIdStr)).thenReturn(null);

        Integer actualPoints = userRepositoryImpl.findPointsByUserId(domainUserId);

        assertNull(actualPoints);
        verify(userMapper).findPointsById(userIdStr);
    }
}
