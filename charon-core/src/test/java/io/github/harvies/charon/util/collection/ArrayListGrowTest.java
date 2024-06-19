package io.github.harvies.charon.util.collection;

import com.google.common.collect.Lists;
import io.github.harvies.charon.util.reflect.FieldUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList扩容测试
 *
 * 注意：Java9+执行时需要在启动参数加上 --add-opens java.base/java.util=ALL-UNNAMED
 *
 * 容量变化：在不指定容量的情况下，ArrayList的初始容量为0，后扩容为10，后续扩容为当前容量的1.5倍（向下取整）
 * 扩容时机：添加元素时，如果容量不够，则扩容
 * 实现方式：创建一个更大的新数组，并将原数组中的元素复制到新数组中
 *
 * 测试结果：
 * 21:37:11.491 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[0] beforeCapacity:[0] afterCapacity:[10]
 * 21:37:11.499 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[1] beforeCapacity:[10] afterCapacity:[10]
 * 21:37:11.500 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[2] beforeCapacity:[10] afterCapacity:[10]
 * 21:37:11.503 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[3] beforeCapacity:[10] afterCapacity:[10]
 * 21:37:11.503 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[4] beforeCapacity:[10] afterCapacity:[10]
 * 21:37:11.504 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[5] beforeCapacity:[10] afterCapacity:[10]
 * 21:37:11.504 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[6] beforeCapacity:[10] afterCapacity:[10]
 * 21:37:11.505 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[7] beforeCapacity:[10] afterCapacity:[10]
 * 21:37:11.505 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[8] beforeCapacity:[10] afterCapacity:[10]
 * 21:37:11.506 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[9] beforeCapacity:[10] afterCapacity:[10]
 * 21:37:11.506 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[10] beforeCapacity:[10] afterCapacity:[15]
 * 21:37:11.506 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[11] beforeCapacity:[15] afterCapacity:[15]
 * 21:37:11.507 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[12] beforeCapacity:[15] afterCapacity:[15]
 * 21:37:11.508 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[13] beforeCapacity:[15] afterCapacity:[15]
 * 21:37:11.508 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[14] beforeCapacity:[15] afterCapacity:[15]
 * 21:37:11.508 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[15] beforeCapacity:[15] afterCapacity:[22]
 * 21:37:11.509 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[16] beforeCapacity:[22] afterCapacity:[22]
 * 21:37:11.509 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[17] beforeCapacity:[22] afterCapacity:[22]
 * 21:37:11.509 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[18] beforeCapacity:[22] afterCapacity:[22]
 * 21:37:11.509 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[19] beforeCapacity:[22] afterCapacity:[22]
 * 21:37:11.510 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[20] beforeCapacity:[22] afterCapacity:[22]
 * 21:37:11.510 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[21] beforeCapacity:[22] afterCapacity:[22]
 * 21:37:11.510 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[22] beforeCapacity:[22] afterCapacity:[33]
 * 21:37:11.510 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[23] beforeCapacity:[33] afterCapacity:[33]
 * 21:37:11.511 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[24] beforeCapacity:[33] afterCapacity:[33]
 * 21:37:11.511 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[25] beforeCapacity:[33] afterCapacity:[33]
 * 21:37:11.511 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[26] beforeCapacity:[33] afterCapacity:[33]
 * 21:37:11.511 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[27] beforeCapacity:[33] afterCapacity:[33]
 * 21:37:11.511 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[28] beforeCapacity:[33] afterCapacity:[33]
 * 21:37:11.512 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[29] beforeCapacity:[33] afterCapacity:[33]
 * 21:37:11.512 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[30] beforeCapacity:[33] afterCapacity:[33]
 * 21:37:11.512 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[31] beforeCapacity:[33] afterCapacity:[33]
 * 21:37:11.512 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[32] beforeCapacity:[33] afterCapacity:[33]
 * 21:37:11.513 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[33] beforeCapacity:[33] afterCapacity:[49]
 * 21:37:11.513 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[34] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.513 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[35] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.513 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[36] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.514 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[37] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.514 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[38] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.515 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[39] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.515 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[40] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.516 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[41] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.516 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[42] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.516 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[43] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.516 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[44] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.517 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[45] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.518 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[46] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.518 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[47] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.518 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[48] beforeCapacity:[49] afterCapacity:[49]
 * 21:37:11.519 [main] INFO io.github.harvies.charon.util.collection.ArrayListGrowTest -- index:[49] beforeCapacity:[49] afterCapacity:[73]
 */
@Slf4j
public class ArrayListGrowTest {

    int size = 50;

    @Test
    @Disabled
    public void newArrayListNoCapacity() {
        List<Integer> list = new ArrayList<>();
        common(list);
    }

    @Test
    @Disabled
    public void newArrayListWithCapacity() {
        List<Integer> list = Lists.newArrayListWithCapacity(size);
        common(list);
    }

    private void common(List<Integer> list) {
        for (int i = 0; i < size; i++) {
            int beforeCapacity = getCapacity(list);
            list.add(i, i);
            int afterCapacity = getCapacity(list);
            log.info("index:[{}] beforeCapacity:[{}] afterCapacity:[{}]", i, beforeCapacity, afterCapacity);
        }
    }

    @SneakyThrows
    private <T> int getCapacity(List<T> list) {
        Object[] elementData = (Object[]) FieldUtils.getField(ArrayList.class, "elementData", true).get(list);
        return elementData.length;
    }

}
