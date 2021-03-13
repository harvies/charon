package io.github.harvies.charon.tests.base.jvm.load;

import org.junit.Test;

public class ClassLoaderTest {

    @Test
    public void getAppClassLoader() {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
