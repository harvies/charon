package io.github.harvies.eris.base.apache.commons.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

public class StringUtilsTest {
    @Test
    public void test() {
        String a = "123456789";
        String[] b = {"a", "b", "c"};
        String c = "a,b,c";
        String d = " a-b-c ";
        //省略
//        String abbreviate = StringUtils.abbreviate(a, 7);
//        System.err.println(abbreviate);
        //数组转字符串
        String center = StringUtils.join(b, ",");
        System.err.println(center);
        //字符串转数组
        String[] split = StringUtils.split(c, ",");
        System.err.println(Arrays.toString(split));
        //去空格(输入参数可为null）
        String trim = StringUtils.trim(d);
        System.err.println(trim);
        //字符串截取
        String substring = StringUtils.substring(a, 0, 3);
        System.err.println(substring);
    }

}
