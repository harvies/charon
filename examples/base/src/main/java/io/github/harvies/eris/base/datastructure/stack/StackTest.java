package io.github.harvies.eris.base.datastructure.stack;

import java.util.Stack;

/**
 * @author harvies
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<Object> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.err.println(stack.pop());
        }

    }
}
