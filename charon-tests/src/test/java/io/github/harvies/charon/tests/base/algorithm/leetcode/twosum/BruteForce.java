package io.github.harvies.charon.tests.base.algorithm.leetcode.twosum;

import io.github.harvies.charon.annotations.Complexity;

/**
 * 暴力破解法
 * 遍历每个元素 x，并查找是否存在一个值与 target - x 相等的目标元素。
 * <p>
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 *
 * @author harvies
 */
@Complexity(time = "n^2", space = "1")
public class BruteForce implements TwoSum {

    @Override
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {

                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("no solution");
    }
}
