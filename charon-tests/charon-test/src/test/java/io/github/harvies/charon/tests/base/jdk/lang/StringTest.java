package io.github.harvies.charon.tests.base.jdk.lang;

import io.github.harvies.charon.util.StringUtils;

public class StringTest {
    public static void main(String[] args) {
        String a = "aaa";
        String b = a;
        StringUtils.println(a);
        StringUtils.println(b);
        a = "bbb";
        StringUtils.println(a);
        StringUtils.println(b);
    }
}
