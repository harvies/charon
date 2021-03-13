package io.github.harvies.charon.tests.base.apache.commons.collections;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionsTest {
    @Test
    public void test() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("b");
        list2.add("a");
        list2.add("c");
        Collection collection = CollectionUtils.removeAll(list2, list1);
        System.err.println(collection);
    }
}
