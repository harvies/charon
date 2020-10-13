package io.github.harvies.charon.tests.base.datastructure.map;

/**
 * HashTable(线程安全)
 * Hashtable 是遗留类,很多映射的常用功能与 HashMap 类似,不同的是它承自 Dictionary 类,
 * 并且是线程安全的,任一时间只有一个线程能写 Hashtable,并发性不如 ConcurrentHashMap,
 * 因为 ConcurrentHashMap 引入了分段锁。Hashtable 不建议在新代码中使用,不需要线程安全
 * 的场合可以用 HashMap 替换,需要线程安全的场合可以用 ConcurrentHashMap 替换。
 *
 * @author harvies
 */
public class HashTableTest {
}
