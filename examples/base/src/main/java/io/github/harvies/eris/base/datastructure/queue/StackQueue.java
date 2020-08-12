package io.github.harvies.eris.base.datastructure.queue;

import java.util.Stack;

/**
 * 使用Stack(FILO)实现Queue(FIFO)的功能
 * 要点:
 * 1.两个栈
 * 2.入队时压入到左侧的栈,出队的时候如果右侧队列没有,则把左侧队列全部压入右侧队列
 *
 * @author harvies
 */
public class StackQueue {
    public static void main(String[] args) {

        int[] array = {1, 5, 2, 6, 3, 4};
        /**
         * 更推荐使用Deque类，应为Stack是线程同步
         */
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();

        for (int i = 0; i < array.length; i++) {
            leftStack.push(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            if (rightStack.size() == 0) {
                for (; leftStack.size() > 0; ) {
                    rightStack.push(leftStack.pop());
                }
            }
            System.err.println(rightStack.pop());
        }
    }
}
