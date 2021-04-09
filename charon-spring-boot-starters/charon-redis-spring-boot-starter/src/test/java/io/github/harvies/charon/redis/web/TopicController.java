package io.github.harvies.charon.redis.web;

import io.github.harvies.charon.redis.dto.UserDTO;
import io.github.harvies.charon.redis.listener.RedisListener;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    @Resource
    private RedissonClient redissonClient;

    @RequestMapping(value = "/pub")
    public void pub(UserDTO userDTO) {
        redissonClient.getTopic(RedisListener.pub).publish(userDTO);
    }

}
