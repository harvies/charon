package io.github.harvies.eris.base.algorithm.leetcode.palindromenumber;

import io.github.harvies.eris.base.algorithm.leetcode.twosum.BaseTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class PalindromeNumberImplTest extends BaseTest {

    @Test
    public void isPalindrome() {
        PalindromeNumber palindromeNumber = new ReverseNumber();
        Assert.assertTrue(palindromeNumber.isPalindrome(121));
    }
}
