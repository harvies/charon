package io.github.harvies.eris.base.datastructure.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 减小锁粒度
 * 减小锁粒度是指缩小锁定对象的范围,从而减小锁冲突的可能性,从而提高系统的并发能力。减
 * 小锁粒度是一种削弱多线程锁竞争的有效手段,这种技术典型的应用是 ConcurrentHashMap(高
 * 性能的 HashMap)类的实现。对于 HashMap 而言,最重要的两个方法是 get 与 set 方法,如果我
 * 们对整个 HashMap 加锁,可以得到线程安全的对象,但是加锁粒度太大。Segment 的大小也被
 * 称为 ConcurrentHashMap 的并发度。
 *
 * ConcurrentHashMap 分段锁
 * ConcurrentHashMap,它内部细分了若干个小的 HashMap,称之为段(Segment)。默认情况下
 * 一个 ConcurrentHashMap 被进一步细分为 16 个段,既就是锁的并发度。
 * 如果需要在 ConcurrentHashMap 中添加一个新的表项,并不是将整个 HashMap 加锁,而是首
 * 先根据 hashcode 得到该表项应该存放在哪个段中,然后对该段加锁,并完成 put 操作。在多线程
 * 环境中,如果多个线程同时进行 put 操作,只要被加入的表项不存放在同一个段中,则线程间可以
 * 做到真正的并行。
 *
 * ConcurrentHashMap 是由 Segment 数组结构和 HashEntry(jdk1.8是MapEntry) 数组结构组成
 * ConcurrentHashMap 是由 Segment 数组结构和 HashEntry 数组结构组成。Segment 是一种可
 * 重入锁 ReentrantLock,在 ConcurrentHashMap 里扮演锁的角色,HashEntry 则用于存储键值
 * 对数据。一个 ConcurrentHashMap 里包含一个 Segment 数组,Segment 的结构和 HashMap
 * 类似,是一种数组和链表结构, 一个 Segment 里包含一个 HashEntry 数组,每个 HashEntry 是
 * 一个链表结构的元素, 每个 Segment 守护一个 HashEntry 数组里的元素,当对 HashEntry 数组的
 * 数据进行修改时,必须首先获得它对应的 Segment 锁。
 *
 * @author harvies
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        /**
         * 设置32个分段锁(Segment)
         */
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(32);
        System.err.println(concurrentHashMap);
    }
}
