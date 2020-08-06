package io.github.harvies.charon.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;

public class RedissonClientSpringBootTest extends BaseTest {

    @BeforeEach
    public void beforeEach() {
        redissonClient.getAtomicLong("test-long").delete();
    }

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void test() {
        long l = redissonClient.getAtomicLong("test-long").addAndGet(1);
        Assertions.assertEquals(1, l);
    }
}
