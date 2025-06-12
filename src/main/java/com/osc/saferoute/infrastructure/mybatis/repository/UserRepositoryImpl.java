package com.osc.saferoute.infrastructure.mybatis.repository;

import com.osc.saferoute.domain.model.*;
import com.osc.saferoute.domain.repository.UserRepository;
import com.osc.saferoute.infrastructure.mybatis.entity.UserEntity;
import com.osc.saferoute.infrastructure.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findById(UserId id) {
        UserEntity entity = userMapper.findById(id.value());
        logger.info("ユーザー名: {}", entity.getName());
        return new User(new UserId(entity.getId()), new UserName(entity.getName()));
    }

    @Override
    public void save(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.id().value());
        entity.setName(user.name().value());
        userMapper.update(entity);
    }

    @Override
    public Integer findPointsByUserId(com.osc.saferoute.domain.model.UserId userId) {
        // Assuming userMapper is the instance of UserMapper injected into this class
        return userMapper.findPointsById(userId.value());
    }
}
