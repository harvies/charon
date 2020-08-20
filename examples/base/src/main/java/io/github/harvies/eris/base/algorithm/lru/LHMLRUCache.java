package io.github.harvies.eris.base.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap实现的lru算法
 */
public class LHMLRUCache extends LinkedHashMap<Integer, Integer> implements LRUCache {

    private int capacity;

    //accessOrder = true，默认情况该属性是为 false 的，表示按照插入顺序排序，若是为 true 表示按照访问顺序排序。因为当前的 LinkedHashMap 是需要按照访问顺序排序的因此 accessOrder 应该需要赋值为 true 才行。
    public LHMLRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    @Override
    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}