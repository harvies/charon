package io.github.harvies.charon.tests.base.jdk.collections.queue;

import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1);
        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(4);
        priorityQueue.add(2);
        System.out.println(priorityQueue);
    }
}
