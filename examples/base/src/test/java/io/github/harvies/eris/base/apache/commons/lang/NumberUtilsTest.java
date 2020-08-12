package io.github.harvies.eris.base.apache.commons.lang;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class NumberUtilsTest {
    @Test
    public void test() {
        //比较两个数的大小
        int compare = NumberUtils.compare(3, 2);
        System.err.println(compare);

        //字符串转Integer
        Integer integer = NumberUtils.createInteger("11");
        System.err.println(integer);
//        Integer integer1 = new Integer(null);
//        System.err.println(integer1);
        //字符串转int
        int i = NumberUtils.toInt("11");
        System.err.println(i);
    }
}
