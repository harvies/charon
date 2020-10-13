package io.github.harvies.charon.tests.base.algorithm.leetcode.palindromenumber;

import io.github.harvies.charon.annotations.Complexity;

/**
 * 反转一半的数字
 *
 * @author harvies
 */
@Complexity(time = "log n", space = "1")
public class ReverseHalfNumber implements PalindromeNumber {
    @Override
    public boolean isPalindrome(int x) {
        //负数不是
        if (x < 0) {
            return false;
        }
        //个位数是0，但不是0，不是回文,比如10
        if (x % 10 == 0 && x != 0) {
            return false;
        }
        //反转一半的数
        int revertedNumber = 0;
        //反转后的数字大于原始数字，则停止 例如 12321 反转后的数字是123 > 12
        while (revertedNumber < x) {
            revertedNumber = revertedNumber * 10 + x % 10;
            //砍掉个位
            x = x / 10;
        }
        //反转后的数字==原始数字(偶数,例如1221  12=12) 或者 (奇数,例如12321 123/10=12)
        return revertedNumber == x || revertedNumber / 10 == x;
    }
}
