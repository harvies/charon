package io.github.harvies.charon.tests.base.algorithm.leetcode.p20;

import java.util.*;

public class StackSolution implements ValidParentheses {
    @Override
    public boolean isValid(String s) {
        //存储括号映射关系
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        //使用栈结构存储数据(Stack继承Vector,不推荐使用，加锁效率低)
        Deque<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            //左括号入栈
            if (!pairs.containsKey(aChar)) {
                stack.push(aChar);
            } else {
                //取左括号
                if (stack.size() == 0) {
                    return false;
                }
                Character peek = stack.pop();
                //如果当前右括号在栈顶没有匹配的左括号
                if (!pairs.get(aChar).equals(peek)) {
                    return false;
                }
            }
        }
        //完全匹配应该是0
        return stack.size() == 0;
    }
}
