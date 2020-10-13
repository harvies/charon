package io.github.harvies.charon.tests.base.datastructure.set;

/**
 * HashSet(Hash 表)
 * <p>
 * 哈希表边存放的是哈希值。HashSet 存储元素的顺序并不是按照存入时的顺序(和 List 显然不
 * 同) 而是按照哈希值来存的所以取数据也是按照哈希值取得。元素的哈希值是通过元素的
 * hashcode 方法来获取的, HashSet 首先判断两个元素的哈希值,如果哈希值一样,接着会比较
 * equals 方法 如果 equls 结果为 true ,HashSet 就视为同一个元素。如果 equals 为 false 就不是
 * 同一个元素。
 * 哈希值相同 equals 为 false 的元素是怎么存储呢,就是在同样的哈希值下顺延(可以认为哈希值相
 * 同的元素放在一个哈希桶中)。也就是哈希一样的存一列。如图 1 表示 hashCode 值不相同的情
 * 况;图 2 表示 hashCode 值相同,但 equals 不相同的情况。
 * HashSet 通过 hashCode 值来确定元素在内存中的位置。一个 hashCode 位置上可以存放多个元
 * 素。
 *
 * @author harvies
 */
public class HashSetTest {
}
