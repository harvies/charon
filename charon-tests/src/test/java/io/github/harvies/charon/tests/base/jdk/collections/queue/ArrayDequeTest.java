package io.github.harvies.charon.tests.base.jdk.collections.queue;

import org.junit.Test;

import java.util.Deque;

public class ArrayDequeTest {
    @Test
    public void testArrayDeque() {
        Deque<Integer> deque = new ArrayDequeWrapper<>(4);
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addLast(5);
        System.out.println(deque);
    }
}
