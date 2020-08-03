package io.github.harvies.charon.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
public class CharonRedisSpringBootTest extends BaseTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void getClientList() {
        List clientList = redisTemplate.getClientList();
        log.info("clientList:[{}]", clientList);
        assert clientList != null;
        Assertions.assertNotEquals(0, clientList.size());
    }
}
