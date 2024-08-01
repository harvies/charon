package io.github.harvies.charon.util.collection;

import com.google.common.collect.Maps;
import io.github.harvies.charon.util.reflect.MethodUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap扩容测试
 *
 * 注意：Java9+执行时需要在启动参数加上 --add-opens java.base/java.util=ALL-UNNAMED
 *
 * 容量变化：在不指定容量的情况下，HashMap的初始容量为16，后续扩容为当前容量的2倍
 * 扩容时机：添加元素后，当HashMap的size达到容量的0.75（负载因子）倍时，触发扩容
 * 实现方式：创建一个更大的新数组，并将原数组中的元素重新hash到新数组中
 *
 * JDK1.8所做的优化：扩容时，如果发现新数组的容量为2的幂次方，则直接使用原数组，避免扩容
 * 也避免了链表元素顺序倒置的问题
 *
 * 测试结果：
 * 21:36:29.319 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[0] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.329 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[1] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.330 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[2] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.331 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[3] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.332 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[4] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.332 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[5] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.333 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[6] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.334 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[7] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.335 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[8] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.336 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[9] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.336 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[10] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.337 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[11] beforeCapacity:[16] afterCapacity:[16]
 * 21:36:29.338 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[12] beforeCapacity:[16] afterCapacity:[32]
 * 21:36:29.340 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[13] beforeCapacity:[32] afterCapacity:[32]
 * 21:36:29.340 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[14] beforeCapacity:[32] afterCapacity:[32]
 * 21:36:29.341 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[15] beforeCapacity:[32] afterCapacity:[32]
 * 21:36:29.342 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[16] beforeCapacity:[32] afterCapacity:[32]
 * 21:36:29.343 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[17] beforeCapacity:[32] afterCapacity:[32]
 * 21:36:29.344 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[18] beforeCapacity:[32] afterCapacity:[32]
 * 21:36:29.345 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[19] beforeCapacity:[32] afterCapacity:[32]
 * 21:36:29.345 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[20] beforeCapacity:[32] afterCapacity:[32]
 * 21:36:29.346 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[21] beforeCapacity:[32] afterCapacity:[32]
 * 21:36:29.347 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[22] beforeCapacity:[32] afterCapacity:[32]
 * 21:36:29.347 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[23] beforeCapacity:[32] afterCapacity:[32]
 * 21:36:29.348 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[24] beforeCapacity:[32] afterCapacity:[64]
 * 21:36:29.348 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[25] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.349 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[26] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.350 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[27] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.350 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[28] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.351 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[29] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.352 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[30] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.353 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[31] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.355 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[32] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.356 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[33] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.357 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[34] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.357 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[35] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.358 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[36] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.358 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[37] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.358 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[38] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.359 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[39] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.359 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[40] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.360 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[41] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.360 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[42] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.361 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[43] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.361 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[44] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.362 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[45] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.363 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[46] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.363 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[47] beforeCapacity:[64] afterCapacity:[64]
 * 21:36:29.363 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[48] beforeCapacity:[64] afterCapacity:[128]
 * 21:36:29.364 [main] INFO io.github.harvies.charon.util.collection.HashMapTest -- index[49] beforeCapacity:[128] afterCapacity:[128]
 */
@Slf4j
public class HashMapGrowTest {

    int size = 50;

    @Test
    @Disabled
    public void newHashMapNoExpectedSize() {
        Map<Integer, Integer> map = new HashMap<>();
        common(map);
    }

    @Test
    @Disabled
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
