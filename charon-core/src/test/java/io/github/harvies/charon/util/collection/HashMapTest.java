package io.github.harvies.charon.util.collection;

import io.github.harvies.charon.util.reflect.MethodUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HashMapTest {

    @SneakyThrows
    @Test
    public void test() {
        int size = 7;
//        Map<Integer, Integer> map = Maps.newHashMapWithExpectedSize(13);
        Map<Integer, Integer> map = new HashMap<>(8);
        for (int i = 0; i < size; i++) {
            int beforeCapacity = getCapacity(map);
            map.put(i, i);
            int currentCapacity = getCapacity(map);
            log.info("beforeCapacity:[{}] currentCapacity:[{}]", beforeCapacity, currentCapacity);
        }
    }

    @SneakyThrows
    private <K, V> int getCapacity(Map<K, V> map) {
        return (int) MethodUtils.invokeMethod(map, true, "capacity");
    }
}
