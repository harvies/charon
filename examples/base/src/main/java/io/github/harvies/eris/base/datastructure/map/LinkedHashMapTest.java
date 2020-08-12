package io.github.harvies.eris.base.datastructure.map;

import java.util.LinkedHashMap;

/**
 * LinkedHashMap(记录插入顺序)
 * LinkedHashMap 是 HashMap 的 一 个 子 类 , 保 存 了 记 录 的 插 入 顺 序 , 在 用 Iterator 遍 历
 * LinkedHashMap 时,先得到的记录肯定是先插入的,也可以在构造时带参数,按照访问次序排序。
 * 参考 1:http://www.importnew.com/28263.html
 * 参考 2:http://www.importnew.com/20386.html#comment-648123
 *
 * @author harvies
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap map = new LinkedHashMap();
        System.err.println(map);
    }

}
