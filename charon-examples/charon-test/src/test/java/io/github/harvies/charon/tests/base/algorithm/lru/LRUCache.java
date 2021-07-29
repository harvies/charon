package io.github.harvies.charon.tests.base.algorithm.lru;

public interface LRUCache {
    
    int get(int key);

    void put(int key, int value);
}
