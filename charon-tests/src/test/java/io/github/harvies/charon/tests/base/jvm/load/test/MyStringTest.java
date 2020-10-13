package io.github.harvies.charon.tests.base.jvm.load.test;

import io.github.harvies.charon.util.StringUtils;

/**
 * @author harvies
 */
public class MyStringTest {

    public static void main(java.lang.String[] args) {
        String a = new String("aaa");
        String b = a;
        StringUtils.println(a);
        StringUtils.println(b);
        a = new String("bbb");
        StringUtils.println(a);
        StringUtils.println(b);
    }
}
