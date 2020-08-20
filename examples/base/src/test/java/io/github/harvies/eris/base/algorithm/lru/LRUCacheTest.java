package io.github.harvies.eris.base.algorithm.lru;

import org.junit.jupiter.api.AfterEach;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class LRUCacheTest {
    
    LRUCache cache;

    @AfterEach
    void assert0() {
        cache.put(1, 1);
        cache.put(2, 2);
        assertThat(cache.get(1), is(1));
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        assertThat(cache.get(2), is(-1));
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        assertThat(cache.get(1), is(-1));
        assertThat(cache.get(3), is(3));
        assertThat(cache.get(4), is(4));
    }

}