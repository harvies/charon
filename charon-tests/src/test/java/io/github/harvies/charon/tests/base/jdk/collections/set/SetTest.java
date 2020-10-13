package io.github.harvies.charon.tests.base.jdk.collections.set;

import org.junit.Test;

import java.util.*;

public class SetTest {
    @Test
    public void hashSet() {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("c");
        set.add("b");
        System.err.println(set);
    }

    @Test
    public void linkedHashSet() {
        Set<String> set = new LinkedHashSet<>();
        set.add("a");
        set.add("c");
        set.add("b");
        System.err.println(set);
    }

    @Test
    public void treeSet() {
        Set<String> set = new TreeSet<>();
        set.add("a");
        set.add("c");
        set.add("b");
        set.add("d");
        System.err.println(set);
    }

    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("a");
        System.err.println(list);
        HashSet<String> strings = new HashSet<>(list);
        System.err.println(strings);
    }
}
