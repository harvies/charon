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

    @Test
    public void testArrayQueueCalculateSize(){
        int MIN_INITIAL_CAPACITY=8;
        int numElements=9;
        System.out.println(numElements);
        int initialCapacity = MIN_INITIAL_CAPACITY;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;
            System.out.println(initialCapacity);

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        System.out.println(initialCapacity);
    }

    public static void main(String[] args) {
        System.out.println(1|8);
        System.out.println(2|8);
        System.out.println(7|8);
        System.out.println(8|8);
        System.out.println(9|8);
    }
}
