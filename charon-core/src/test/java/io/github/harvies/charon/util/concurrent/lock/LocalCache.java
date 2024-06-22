package io.github.harvies.charon.util.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * java本地缓存,采用map+ReentrantReadWriteLock读写锁实现(读写互斥,读读不互斥,写写互斥)
 *
 * @author harvies
 */
public class LocalCache {
    private static final ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private static final Map<String, Object> MAP = new HashMap<>();

    public static void reLoad() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("reLoad begin");
        /**
         * 写数据前加写锁
         */
        REENTRANT_READ_WRITE_LOCK.writeLock().lock();
        try {
            /**
             * 加载数据到map
             */
            MAP.clear();
            MAP.put("keyA", "valueA");
            MAP.put("keyB", "valueB");
            MAP.put("keyC", "valueC");
        } finally {
            /**
             * finally里释放锁!
             */
            REENTRANT_READ_WRITE_LOCK.writeLock().unlock();
        }
        System.err.println("reload end");
    }

    public static Object get(String key) {
        /**
         * 读数据前加读锁
         */
        REENTRANT_READ_WRITE_LOCK.readLock().lock();
        try {
            return MAP.get(key);
        } finally {
            /**
             * finally里释放锁!
             */
            REENTRANT_READ_WRITE_LOCK.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        final Object[] value = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {
                reLoad();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (value[0] == null) {
                    value[0] = get("keyA");
                    System.err.println("value:" + value[0]);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
