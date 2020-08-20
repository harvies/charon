package io.github.harvies.eris.base.algorithm.lru;

import org.junit.jupiter.api.Test;

class HLLRUCacheTest extends LRUCacheTest {

    @Test
    void get() {
        cache = new HLLRUCache(2);
    }

}