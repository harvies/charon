package io.github.harvies.eris.base.apache.commons.lang;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import java.util.Random;

public class ArrayUtilsTest {
    @Test
    public void test() {
        String[] a = {"a", "b", "c"};
        //添加一个
        String[] ds = ArrayUtils.add(a, "d");
        System.err.println(ToStringBuilder.reflectionToString(ds));
        //添加多个
        String[] strings = ArrayUtils.addAll(a, "e", "f");
        System.err.println(ToStringBuilder.reflectionToString(strings));
        //包含
        boolean b = ArrayUtils.contains(a, "b");
        System.err.println(b);

        //洗牌
        ArrayUtils.shuffle(a, new Random());
        System.err.println(ToStringBuilder.reflectionToString(a));
        //反转
        ArrayUtils.reverse(a);
        System.err.println(ToStringBuilder.reflectionToString(a));
        //toString
        String s = ArrayUtils.toString(a);
        System.err.println(s);
    }
}
