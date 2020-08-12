package io.github.harvies.eris.base.algorithm.leetcode.twosum;

/**
 * 暴力破解法
 * <p>
 * Complexity Analysis
 * <p>
 * Time complexity : O(n^2)O(n
 * 2
 * ). For each element, we try to find its complement by looping through the rest of array which takes O(n)O(n) time. Therefore, the time complexity is O(n^2)O(n
 * 2
 * ).
 * <p>
 * Space complexity : O(1)O(1).
 *
 * @author harvies
 */
public class BruteForce implements TwoSum {

    @Override
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            /**
             * int j=i+1,第二个数从i+1开始
             */
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
