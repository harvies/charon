package io.github.harvies.charon.redis.listener;

import io.github.harvies.charon.redis.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisListener {
    public static final String pub = "pub";
    @Resource
    private RedissonClient redissonClient;

    @PostConstruct
    public void init() {
        redissonClient.getTopic(pub).addListener(UserDTO.class, (channel, msg) -> {
            log.info("topic rec msg:[{}]", msg);
            //不支持消费失败重新发送!
//            throw new RuntimeException("测试异常抛出");
        });
    }
}
