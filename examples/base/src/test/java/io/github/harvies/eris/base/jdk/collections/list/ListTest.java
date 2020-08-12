package io.github.harvies.eris.base.jdk.collections.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListTest {
    @Test
    public void test() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        System.err.println(list);
    }

    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        Collections.sort(list);
        System.err.println(list);
    }
}
