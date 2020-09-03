package io.github.harvies.charon.util;

import net.sf.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BeanCopierUtils {

    private static Lock initLock = new ReentrantLock();

    private static Map<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>();

    private static BeanCopier init(Class source, Class target) {
        initLock.lock();

        String key = genKey(source, target);
        BeanCopier beanCopier = beanCopierMap.get(key);
        if (null != beanCopier) {
            initLock.unlock();
            return beanCopier;
        }

        BeanCopier newBeanCopier = BeanCopier.create(source, target, false);
        beanCopierMap.put(key, newBeanCopier);

        initLock.unlock();
        return newBeanCopier;
    }

    private static String genKey(Class<?> src, Class<?> dest) {
        return src.getName() + "_" + dest.getName();
    }

    public static void copy(Object src, Object dest) {
        String key = genKey(src.getClass(), dest.getClass());
        BeanCopier beanCopier;

        if (!beanCopierMap.containsKey(key)) {
            beanCopier = init(src.getClass(), dest.getClass());
        } else {
            beanCopier = beanCopierMap.get(key);
        }

        beanCopier.copy(src, dest, null);
    }

}