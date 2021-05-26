package io.github.harvies.charon.util.collection;

import com.google.common.collect.Maps;
import io.github.harvies.charon.util.reflect.MethodUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HashMapTest {

    int size = 20;

    @SneakyThrows
    @Test
    public void newHashMapNoExpectedSize() {
        Map<Integer, Integer> map = new HashMap<>();
        common(map);
    }

    @SneakyThrows
    @Test
    public void newHashMapWithExpectedSize() {
        Map<Integer, Integer> map = Maps.newHashMapWithExpectedSize(size);
        common(map);
    }

    private void common(Map<Integer, Integer> map) {
        for (int i = 0; i < size; i++) {
            int beforeCapacity = getCapacity(map);
            map.put(i, i);
            int afterCapacity = getCapacity(map);
            log.info("index[{}] beforeCapacity:[{}] afterCapacity:[{}]", i, beforeCapacity, afterCapacity);
        }
    }

    @SneakyThrows
    private <K, V> int getCapacity(Map<K, V> map) {
        return (int) MethodUtils.invokeMethod(map, true, "capacity");
    }
}
