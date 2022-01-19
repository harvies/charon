package io.github.harvies.charon.cache.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import io.github.harvies.charon.cache.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    @Cached(expire = 60 * 10, cacheType = CacheType.LOCAL, localLimit = 100)
    public String echo(String uuid) {
        System.out.println("echo-echo-echo");
        return uuid;
    }
}
