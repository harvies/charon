package io.github.harvies.charon.tests.base.algorithm.leetcode.p20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackSolutionTest {

    @Test
    void isValid() {
        ValidParentheses validParentheses = new StackSolution();
        assertTrue(validParentheses.isValid("()"));
        assertTrue(validParentheses.isValid("()[]{}"));
        assertFalse(validParentheses.isValid("(]"));
        assertFalse(validParentheses.isValid("([)]"));
        assertTrue(validParentheses.isValid("{[]}"));
    }
}