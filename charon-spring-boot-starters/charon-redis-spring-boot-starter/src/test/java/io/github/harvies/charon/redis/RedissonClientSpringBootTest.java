package io.github.harvies.charon.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.api.SortOrder;

import javax.annotation.Resource;
import java.util.Set;

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

    @Test
    public void test2() {
        Object o = redissonClient.getMap("testMap").addAndGet("key1", 1L);
        System.out.println(o);
    }

    @Test
    public void testSortSet() {
        RScoredSortedSet<Object> sortSet = redissonClient.getScoredSortedSet("testSortSet");
        sortSet.delete();
        sortSet.addScore("key1", 2);
        sortSet.addScore("key3", 1);
        sortSet.addScore("key2", 3);
        Set<Object> objects = sortSet.readSort("score", SortOrder.DESC, 0, 2);
        System.out.println(objects);//[key2, key1]
    }
}
