package io.github.harvies.charon.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// TODO: 2020/8/7 整合redis-spring-boot-starter和redisson
@Configuration(proxyBeanMethods = false)
public class RedisAutoConfiguration {
    @Resource
    private RedisProperties redisProperties;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redisson() {
        Config config = new Config();
        //timeout default 3000;
        int timeout = 3000;
        if (redisProperties.getTimeout() != null) {
            timeout = Math.toIntExact(redisProperties.getTimeout().toMillis());
        }
        if (redisProperties.getCluster() != null) {
            config.useClusterServers()
                    .addNodeAddress(convert(redisProperties.getCluster().getNodes()))
                    .setTimeout(timeout)
                    .setPassword(redisProperties.getPassword());
        } else if (redisProperties.getSentinel() != null) {
            // TODO: 2020/8/7
        } else {
            config.useSingleServer()
                    .setAddress(redisProperties.getUrl())
                    .setTimeout(timeout)
                    .setPassword(redisProperties.getPassword())
                    .setDatabase(redisProperties.getDatabase());
        }
        return Redisson.create(config);
    }

    private String[] convert(List<String> nodesObject) {
        List<String> nodes = new ArrayList<>(nodesObject.size());
        for (String node : nodesObject) {
            if (!node.startsWith("redis://") && !node.startsWith("rediss://")) {
                nodes.add("redis://" + node);
            } else {
                nodes.add(node);
            }
        }
        return nodes.toArray(new String[nodes.size()]);
    }

}
