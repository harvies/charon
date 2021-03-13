package io.github.harvies.charon.tests.base.jvm.gc.reference;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

import static org.junit.Assert.*;

/**
 * 出处:https://www.iteye.com/topic/401478
 *
 * @author harvies
 */
public class ReferenceTest {
    /**
     * StrongReference 是 Java 的默认引用实现,  它会尽可能长时间的存活于 JVM 内， 当没有任何对象指向它时 GC 执行后将会被回收
     */
    @Test
    public void strongReference() {
        Object referent = new Object();

        /**
         * 通过赋值创建 StrongReference
         */
        Object strongReference = referent;

        assertSame(referent, strongReference);

        referent = null;
        System.gc();

        /**
         * StrongReference 在 GC 后不会被回收
         */
        assertNotNull(strongReference);
    }

    /**
     * WeakReference， 顾名思义,  是一个弱引用,  当所引用的对象在 JVM 内不再有强引用时, GC 后 weak reference 将会被自动回收
     */
    @Test
    public void weakReference() {
        Object referent = new Object();
        WeakReference<Object> weakRerference = new WeakReference<>(referent);

        assertSame(referent, weakRerference.get());

        referent = null;
        System.gc();

        /**
         * 一旦没有指向 referent 的强引用, weak reference 在 GC 后会被自动回收
         */
        assertNull(weakRerference.get());
    }

    /**
     * WeakHashMap 使用 WeakReference 作为 key， 一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry
     *
     * @throws InterruptedException
     */
    @Test
    public void weakHashMap() throws InterruptedException {
        Map<Object, Object> weakHashMap = new WeakHashMap<>();
        Object key = new Object();
        Object value = new Object();
        weakHashMap.put(key, value);

        assertTrue(weakHashMap.containsValue(value));

        key = null;
        System.gc();

        /**
         * 等待无效 entries 进入 ReferenceQueue 以便下一次调用 getTable 时被清理
         */
        Thread.sleep(1000);

        /**
         * 一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry
         */
        assertFalse(weakHashMap.containsValue(value));
    }

    /**
     * SoftReference 于 WeakReference 的特性基本一致， 最大的区别在于 SoftReference 会尽可能长的保留引用直到 JVM 内存不足时才会被回收(虚拟机保证), 这一特性使得 SoftReference 非常适合缓存应用
     */
    @Test
    public void softReference() {
        Object referent = new Object();
        SoftReference<Object> softRerference = new SoftReference<>(referent);

        assertNotNull(softRerference.get());

        referent = null;
        System.gc();

        /**
         *  soft references 只有在 jvm OutOfMemory 之前才会被回收, 所以它非常适合缓存应用
         */
        assertNotNull(softRerference.get());
    }

    /**
     * Phantom Reference(幽灵引用) 与 WeakReference 和 SoftReference 有很大的不同,  因为它的 get() 方法永远返回 null, 这也正是它名字的由来
     */
    @Test
    public void phantomReferenceAlwaysNull() {
        Object referent = new Object();
        PhantomReference<Object> phantomReference = new PhantomReference<>(referent, new ReferenceQueue<>());

        /**
         * phantom reference 的 get 方法永远返回 null
         */
        assertNull(phantomReference.get());
    }
}
