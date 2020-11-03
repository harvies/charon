package io.github.harvies.charon.tests.base.jdk.collections.queue;

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

    public static void main(String[] args) {
        //15=16-1=2^4-1
        System.out.println(1 & 15);
        System.out.println(2 & 15);
        System.out.println(3 & 15);
        System.out.println(4 & 15);
        System.out.println(15 & 15);
        System.out.println(-1 & 7);
    }
}
