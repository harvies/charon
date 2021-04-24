package io.github.harvies.charon.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.api.*;

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
    @Test
    public void testBloomFilter() {
        RBloomFilter<String> testBloomFilter = redissonClient.getBloomFilter("testBloomFilter");
        testBloomFilter.delete();
        testBloomFilter.tryInit(1000,0.1d);
        testBloomFilter.add("1");
        testBloomFilter.add("2");
        testBloomFilter.add("3");
        testBloomFilter.add("4");
        System.out.println(testBloomFilter.contains("1"));
        System.out.println(testBloomFilter.contains("2"));
        System.out.println(testBloomFilter.contains("3"));
        System.out.println(testBloomFilter.contains("4"));
        System.out.println(testBloomFilter.contains("5"));
    }
    
    @Test
    public void test3(){
        RBlockingQueue<String> blockingQueue = redissonClient.getBlockingQueue("aaa");
        try {
            blockingQueue.put("aaa");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                String take = blockingQueue.take();
                System.out.println(take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
