package io.github.harvies.charon.tests.base.jdk.collections.map;

import org.junit.jupiter.api.Test;

import java.util.*;

public class TreeMapTest {
    @Test
    public void sortByKey() {
        Map<String, String> map = new TreeMap<>();
        map.put("a", "ddddd");
        map.put("c", "bbbbb");
        map.put("d", "aaaaa");
        map.put("b", "ccccc");

        //这里将map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        //然后通过比较器来实现排序
        //升序排序
        list.sort(Map.Entry.comparingByValue());

        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
    }

    @Test
    public void sortByValue() {
        Map<String, String> map = new TreeMap<>();
        map.put("a", "ddddd");
        map.put("c", "bbbbb");
        map.put("d", "aaaaa");
        map.put("b", "ccccc");

        //这里将map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        //然后通过比较器来实现排序
        //升序排序
        list.sort(Map.Entry.comparingByValue());

        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
    }
}