package io.github.harvies.eris.base.algorithm.leetcode.palindromenumber;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReverseHalfNumberTest {
    ReverseHalfNumber reverseHalfNumber = new ReverseHalfNumber();

    @Test
    void minus() {
        //负数不是回文数
        assertFalse(reverseHalfNumber.isPalindrome(-1));
    }

    @Test
    void zero() {
        //0是回文数
        assertTrue(reverseHalfNumber.isPalindrome(0));
    }

    @Test
    void aa() {

        assertFalse(reverseHalfNumber.isPalindrome(10));

        //回文奇数
        assertTrue(reverseHalfNumber.isPalindrome(121));
        //回文偶数
        assertTrue(reverseHalfNumber.isPalindrome(1221));
    }
}