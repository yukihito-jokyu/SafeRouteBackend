package com.osc.saferoute.infrastructure.mybatis.repository;

import com.osc.saferoute.domain.model.*;
import com.osc.saferoute.domain.repository.UserRepository;
import com.osc.saferoute.infrastructure.mybatis.entity.UserEntity;
import com.osc.saferoute.infrastructure.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findById(UserId id) {
        UserEntity entity = userMapper.findById(id.value());
        return new User(new UserId(entity.getId()), new UserName(entity.getName()));
    }

    @Override
    public void save(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.id().value());
        entity.setName(user.name().value());
        userMapper.update(entity);
    }
}
