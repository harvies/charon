package io.github.harvies.charon.tests.base.datastructure.map;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap底层是数组+链表
 * HashMap的链表数据结构是用来解决什么问题的？
 * 解决哈希冲突。
 * 1、JDK1.7的HashMap是由数组+链表构成的，新增一个数通过哈希算法，计算出对应存放在数组的某个位置，如果这个位置已经存在数据了，也就是说存在了哈希冲突，这时候JDK1.7就将新增的数和原来的数构成一个链表放在数组这个位置，后面冲突的数依次都放入链表中。
 * 2、通常解决哈希冲突有两种办法，上面所说的通过链表的形式称为链地址法；还有一种方法称为开放地址法，也就是说如果存在哈希冲突了，那么将新增的值在用一个新的哈希算法算出所存的位置插入，但是这还会构成二次冲突，三次冲突.....
 * 3、JDK1.8的HashMap是由数组+链表+红黑树构成，当链表长度超过8会自动转换成红黑树，红黑树节点个数小于6，又自动转换为链表。这是为了提高检索效率（红黑树检索效率明显是高于链表的）
 * 出处:https://www.imooc.com/wenda/detail/394879
 *
 * @author harvies
 */
public class HashMapTest {
    @Test
    public void test() {
        Map<Integer, Integer> map = new HashMap<>(1);
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        System.err.println(map);
    }
}
