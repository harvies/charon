package io.github.harvies.charon.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BeanRefreshScope implements Scope {

    public static final String SCOPE_REFRESH = "refresh";

    private static final BeanRefreshScope INSTANCE = new BeanRefreshScope();

    //来个map用来缓存bean
    private static final ConcurrentHashMap<String, Object> BEAN_MAP = new ConcurrentHashMap<>(); //@1

    /**
     * 读写锁，防止写入的时候同时创建多个实例
     */
    public static final ReentrantReadWriteLock CACHE_LOCK = new ReentrantReadWriteLock();

    private BeanRefreshScope() {
    }

    public static BeanRefreshScope getInstance() {
        return INSTANCE;
    }

    /**
     * 清理当前
     */
    public static void clean() {
        BEAN_MAP.clear();
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        CACHE_LOCK.readLock().lock();
        try {
            Object bean = BEAN_MAP.get(name);
            if (bean == null) {
                bean = objectFactory.getObject();
                BEAN_MAP.put(name, bean);
            }
            return bean;
        } finally {
            CACHE_LOCK.readLock().unlock();
        }
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }

}
