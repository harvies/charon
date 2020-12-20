package io.github.harvies.charon.tests.base.jdk.collections.list;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ArrayListTest {
    @Test
    public void test() {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == 2) {
                arrayList.remove(i);
            }
        }
        System.err.println(arrayList);
    }

    @Test
    public void test2() {
        try {
            List<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                arrayList.add(i);
            }
            for (Object o : arrayList) {
                if (Objects.equals(o, 2)) {
                    arrayList.remove(o);
                }
            }
            System.err.println(arrayList);
        } catch (Exception e) {
            log.warn("e", e);
        }
    }

    @Test
    public void removeAll() {
        List<Integer> listA = Lists.newArrayList(1, 2, 3);
        List<Integer> listB = Lists.newArrayList(1, 2);
        listA.removeAll(listB);
        System.out.println(listA); //[3]
    }

    @Test
    public void retainAll() {
        List<Integer> listA = Lists.newArrayList(1, 2, 3);
        List<Integer> listB = Lists.newArrayList(1, 2);
        listA.retainAll(listB);
        System.out.println(listA); //[1,2]
    }
}
