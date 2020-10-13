package io.github.harvies.charon.tests.base.guava.collect;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.MultiMapUtils;
import org.apache.commons.collections4.SetValuedMap;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author harvies
 */
public class MapsTest {
    @Test
    public void test1() {
        List<User> list = new ArrayList<>();
        list.add(User.builder().id(1L).username("aaa").build());
        list.add(User.builder().id(2L).username("bbb").build());

        Map<Long, User> userMap = Maps.uniqueIndex(list, icon -> icon.getId());
        System.out.println(userMap);
    }

    @Test
    public void setValuedMap() {
        SetValuedMap<Integer, Integer> map = MultiMapUtils.newSetValuedHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(i, i + 100);
            map.put(i, i + 100);
        }
        for (Integer key :
                map.keySet()) {
            System.err.println(key + "," + map.get(key));
        }
    }

    @Test
    public void listValuedMap() {
        ListValuedMap<Integer, Integer> map = MultiMapUtils.newListValuedHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(i, i + 100);
            map.put(i, i + 100);
        }
        for (Integer key :
                map.keySet()) {
            System.err.println(key + "," + map.get(key));
        }
    }

    @Test
    public void arrayListMultimapTest() {
        ArrayListMultimap<Integer, User> listMultimap = ArrayListMultimap.create();
        for (int i = 0; i < 3; i++) {
            int nextInt = RandomUtils.nextInt(1, 10);
            System.err.println("nextInt:" + nextInt);
            for (int j = 0; j < nextInt; j++) {
                listMultimap.put(i, User.builder().id(Long.valueOf(i)).username("username" + i).build());
            }
        }
        System.err.println(listMultimap.keys());
        System.err.println(listMultimap.size());
    }

}
