package io.github.harvies.eris.base.algorithm.leetcode.palindromenumber;

/**
 * 反转一半的数字
 *
 * @author harvies
 */
public class ReverseHalfNumber implements PalindromeNumber {
    @Override
    public boolean isPalindrome(int x) {
        //todo 待完成
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            rev = rev * 10 + pop;
        }

        return false;
    }
}
