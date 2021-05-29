package io.github.harvies.charon.util.collection.queue;

import org.junit.Test;

import java.util.Deque;

public class ArrayDequeTest {
    @Test
    public void testArrayDeque() {
        Deque<Integer> deque = new ArrayDequeWrapper<>(4);
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addFirst(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addFirst(9);
        deque.addLast(10);
        deque.poll();
        System.out.println(deque);
    }
}
