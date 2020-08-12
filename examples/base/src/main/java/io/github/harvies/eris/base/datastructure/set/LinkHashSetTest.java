package io.github.harvies.eris.base.datastructure.set;

/**
 * LinkHashSet( HashSet+LinkedHashMap )
 * 对 于 LinkedHashSet 而 言 , 它 继 承 与 HashSet 、 又 基 于 LinkedHashMap 来 实 现 的 。
 * LinkedHashSet 底层使用 LinkedHashMap 来保存所有元素,它继承与 HashSet,其所有的方法
 * 操作上又与 HashSet 相同,因此 LinkedHashSet 的实现上非常简单,只提供了四个构造方法,并
 * 通过传递一个标识参数,调用父类的构造器,底层构造一个 LinkedHashMap 来实现,在相关操
 * 作上与父类 HashSet 的操作相同,直接调用父类 HashSet 的方法即可。
 *
 * @author harvies
 */
public class LinkHashSetTest {
}
