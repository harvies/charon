package io.github.harvies.charon.shardingsphere.jdbc.service;

import io.github.harvies.charon.shardingsphere.jdbc.entity.User;
import io.github.harvies.charon.shardingsphere.jdbc.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    @Transactional
    public void migrate(Long from, Long to) {
        log.info("begin migrate from:[{}]", from);
        User fromUser = userMapper.selectById(from);
        User toUser = userMapper.selectById(to);
        userMapper.deleteById(from);
        toUser.setMobile(fromUser.getMobile());
        userMapper.updateById(toUser);
        log.info("end migrate from:[{}]", from);
    }
}
