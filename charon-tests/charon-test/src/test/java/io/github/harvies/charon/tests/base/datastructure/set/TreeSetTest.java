package io.github.harvies.charon.tests.base.datastructure.set;

/**
 * TreeSet(二叉树)
 * <p>
 * TreeSet()是使用二叉树的原理对新 add()的对象按照指定的顺序排序(升序、降序),每增
 * 加一个对象都会进行排序,将对象插入的二叉树指定的位置。
 * 2.
 * Integer 和 String 对象都可以进行默认的 TreeSet 排序,而自定义类的对象是不可以的,自
 * 己定义的类必须实现 Comparable 接口,并且覆写相应的 compareTo()函数,才可以正常使
 * 用。
 * 3. 在覆写 compare()函数时,要返回相应的值才能使 TreeSet 按照一定的规则来排序
 * 4. 比较此对象与指定对象的顺序。如果该对象小于、等于或大于指定对象,则分别返回负整
 * 数、零或正整数。
 *
 * @author harvies
 */
public class TreeSetTest {
}
