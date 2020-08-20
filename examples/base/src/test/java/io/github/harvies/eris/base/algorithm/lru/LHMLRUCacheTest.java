package io.github.harvies.eris.base.algorithm.lru;

import org.junit.jupiter.api.Test;

class LHMLRUCacheTest extends LRUCacheTest {

    @Test
    void get() {
        cache = new LHMLRUCache(2 /* 缓存容量 */);
    }

}