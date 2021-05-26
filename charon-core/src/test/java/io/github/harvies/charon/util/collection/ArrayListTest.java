package io.github.harvies.charon.util.collection;

import com.google.common.collect.Lists;
import io.github.harvies.charon.util.reflect.FieldUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ArrayListTest {

    int size = 15;

    @SneakyThrows
    @Test
    public void newArrayListNoCapacity() {
        List<Integer> list = new ArrayList<>();
        common(list);
    }

    @SneakyThrows
    @Test
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
