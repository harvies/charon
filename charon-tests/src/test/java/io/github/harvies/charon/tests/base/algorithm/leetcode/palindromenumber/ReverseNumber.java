package io.github.harvies.charon.tests.base.algorithm.leetcode.palindromenumber;

/**
 * 反转所有数字(性能略差)
 *
 * @author harvies
 */
public class ReverseNumber implements PalindromeNumber {
    @Override
    public boolean isPalindrome(int x) {
        int hisX = x;
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            rev = rev * 10 + pop;
        }
        return rev == hisX;
    }
}
