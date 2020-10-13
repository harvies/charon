package io.github.harvies.charon.tests.base.apache.commons.beanutils;

import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * 对象属性复制,比spring的BeanUtils好用,　必须是public类
 */
public class BeanUtilsTest {
    @Data
    public class A {
        private String a;
    }

    @Data
    public class B {
        private String a;
    }

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException {
        A a = new A();
        a.setA("a");
        B b = new B();
        System.err.println(a);
        System.err.println(b);
        BeanUtils.copyProperties(b, a);//和spring 的BeanUtils.copyProperties() 属性刚好相反
        System.err.println(a);
        System.err.println(b);
    }
}
