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
 *
 * 当使用new ArrayList<>()创建一个空的ArrayList时，它的初始容量为0。在向ArrayList中添加元素时，它会自动进行扩容。
 * ArrayList的扩容机制是通过创建一个更大的新数组，并将原数组中的元素复制到新数组中来实现的。
 * 具体来说，当需要扩容时，ArrayList会将容量增加为原来的1.5倍（向下取整）。例如，如果当前容量为10，那么扩容后的新容量为15。
 * 在上述代码中，当向ArrayList中添加50个元素时，它会进行多次扩容。每次扩容后，新的容量都会增加为原来的1.5倍，直到能够容纳所有的元素。
 * 需要注意的是，频繁的扩容操作可能会影响性能，特别是在元素数量较多的情况下。为了提高性能，可以在创建ArrayList时指定一个合适的初始容量，以减少扩容的次数。
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
