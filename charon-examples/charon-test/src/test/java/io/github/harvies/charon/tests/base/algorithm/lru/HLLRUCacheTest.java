package io.github.harvies.charon.tests.base.algorithm.lru;

import org.junit.jupiter.api.Test;

class HLLRUCacheTest extends LRUCacheTest {

    @Test
    void get() {
        cache = new HLLRUCache(2);
    }

}