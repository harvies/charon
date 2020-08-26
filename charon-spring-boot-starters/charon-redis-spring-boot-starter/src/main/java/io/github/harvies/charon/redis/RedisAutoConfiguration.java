package io.github.harvies.charon.redis;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({RedisProperties.class})
public class RedisAutoConfiguration {

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(RedissonClient.class)
    @ConditionalOnClass(Redisson.class)
    public RedissonClient redisson(RedisProperties redisProperties) {
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
            SingleServerConfig singleServerConfig = config.useSingleServer();
            if (StringUtils.isNotBlank(redisProperties.getUrl())) {
                singleServerConfig.setAddress(redisProperties.getUrl());
            } else {
                singleServerConfig.setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort());
            }
            singleServerConfig.setTimeout(timeout)
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
