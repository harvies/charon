package io.github.harvies.charon.cache.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import io.github.harvies.charon.cache.request.TestRequest;

public interface TestService {

    @Cached(expire = 10, cacheType = CacheType.LOCAL, localLimit = 100)
    TestRequest echo(TestRequest request);
}
