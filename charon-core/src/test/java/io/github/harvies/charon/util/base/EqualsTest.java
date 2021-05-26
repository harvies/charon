package io.github.harvies.charon.util.base;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.*;

public class EqualsTest {

    @SneakyThrows
    @Test
    public void test() {
        Set<A> set = new HashSet<>();
        A a1 = new A(1);
        A a2 = new A(2);
        set.add(a1);
        set.add(a2);
        System.out.println(a1.equals(a2));
        System.out.println(set.size());
    }

    private static class A {

        public A(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public boolean equals(Object o) {
            return true;
        }
    }
}
