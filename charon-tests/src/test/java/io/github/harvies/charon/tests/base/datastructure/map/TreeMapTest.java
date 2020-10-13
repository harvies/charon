package io.github.harvies.charon.tests.base.datastructure.map;

/**
 * TreeMap(可排序)
 * TreeMap 实现 SortedMap 接口,能够把它保存的记录根据键排序,默认是按键值的升序排序,
 * 也可以指定排序的比较器,当用 Iterator 遍历 TreeMap 时,得到的记录是排过序的。
 * 如果使用排序的映射,建议使用 TreeMap。
 * 在使用 TreeMap 时,key 必须实现 Comparable 接口或者在构造 TreeMap 传入自定义的
 * Comparator,否则会在运行时抛出 java.lang.ClassCastException 类型的异常。
 * 参考:https://www.ibm.com/developerworks/cn/java/j-lo-tree/index.html
 *
 * @author harvies
 */
public class TreeMapTest {
}
