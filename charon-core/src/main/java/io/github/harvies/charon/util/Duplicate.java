package io.github.harvies.charon.util;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * classpath下重复文件(类)检查
 * <p>
 * http://dubbo.apache.org/zh-cn/docs/dev/principals/dummy.html
 * 有多个版本的相同jar包，会出现新版本的 A 类，调用了旧版本的 B 类，而且和JVM加载顺序有关，问题带有偶然性，误导性，遇到这种莫名其妙的问题，最头疼，所以，第一条，先把它防住，在每个 jar 包中挑一个一定会加载的类，加上重复类检查
 *
 * @see org.apache.dubbo.common.Version#checkDuplicate(Class)
 */
@Slf4j
public final class Duplicate {

    private Duplicate() {
    }

    /**
     * 检查重复的配置文件
     *
     * @param cls
     */
    public static void checkDuplicate(Class<?> cls) {
        checkDuplicate(cls.getName().replace('.', '/') + ".class");
    }

    /**
     * 检查重复的jar包
     *
     * @param path
     */
    public static void checkDuplicate(String path) {
        try {
            // 在ClassPath搜文件
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(path);
            Set<String> files = new HashSet<>();
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String file = url.getFile();
                    if (file != null && file.length() > 0) {
                        files.add(file);
                    }
                }
            }
            // 如果有多个，就表示重复
            if (files.size() > 1) {
                log.error("Duplicate class " + path + " in " + files.size() + " jar " + files);
            }
        } catch (Throwable e) { // 防御性容错
            log.error(e.getMessage(), e);
        }
    }

}