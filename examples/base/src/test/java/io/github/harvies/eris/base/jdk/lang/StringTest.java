package io.github.harvies.eris.base.jdk.lang;

import org.junit.Test;

/**
 * @author harvies
 */
public class StringTest {
    @Test
    public void test() {
        String s1 = "s";
        String s2 = "s";
        String s3 = new String("s");
        String s4 = new String("s");
        StringBuffer sb1 = new StringBuffer("s");
        StringBuffer sb2 = new StringBuffer("s");
        System.out.println(s1 == s2);
        System.out.println(s3 == s4);
        System.out.println(sb1 == sb2);
        System.out.println(s1.equals(s2));
        System.out.println(s3.equals(s4));
        System.out.println(sb1.equals(sb2));
    }
}
