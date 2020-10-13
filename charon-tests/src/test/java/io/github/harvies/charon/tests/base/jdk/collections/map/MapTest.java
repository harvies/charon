package io.github.harvies.charon.tests.base.jdk.collections.map;

import org.junit.Test;

import java.util.*;

public class MapTest {
    @Test
    public void testHashtable() {
        Map<String, String> map = new Hashtable<>();
        map.put("a", "a");
        map.put("c", "c");
        map.put("b", "b");
        System.err.println(map);
    }

    @Test
    public void testHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put(null, null);
        map.put("a", "a");
        map.put("c", "c");
        map.put("b", "b");
        System.err.println(map);
    }

    @Test
    public void testLinkedHashMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(null, null);
        map.put("a", "a");
        map.put("c", "c");
        map.put("b", "b");
        System.err.println(map);
    }

    @Test
    public void testTreeMap() {
        Map<String, String> map1 = new TreeMap<>();
        map1.put("a", "a");
        map1.put("c", "c");
        map1.put("b", "b");
        System.err.println(map1);
    }

    @Test
    public void hash(){
    }

    @Test
    public void computeIfAbsent() {
        Map<String, String> map = new HashMap<>();
        String key1 = map.computeIfAbsent("key1", s -> s);
        System.err.println(key1);
    }
}
