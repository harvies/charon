package io.github.harvies.charon.util.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class IntegerTest3 {
    /**
     * 溢出 int 范围为-2,147,483,648 (-2^31)到2,147,483,647 (2^31-1)
     */
    @Test
    public void test() {
        int a = Integer.MAX_VALUE;
        log.warn("a:{}", a);
        int b = a + 1;
        log.warn("b:{}", b);
    }

    @Test
    public void cache() {
        Integer integer1 = 3;
        Integer integer2 = 3;

        if (integer1 == integer2) {
            System.out.println("integer1 == integer2");
        } else {
            System.out.println("integer1 != integer2");
        }

        Integer integer3 = 300;
        Integer integer4 = 300;

        if (integer3 == integer4) {
            System.out.println("integer3 == integer4");
        } else {
            System.out.println("integer3 != integer4");
        }
    }
}
