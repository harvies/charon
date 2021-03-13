package io.github.harvies.charon.tests.base.algorithm.leetcode.twosum;

import io.github.harvies.charon.annotations.Complexity;

import java.util.HashMap;
import java.util.Map;

/**
 * 一遍哈希表
 *
 * @author harvies
 */
@Complexity(time = "n", space = "n")
public class OnePassHashTable implements TwoSum {
    @Override
    public int[] twoSum(int[] nums, int target) {
        //key是数据,value是索引
        Map<Integer, Integer> map = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{i, map.get(complement)};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no solution");
    }
}
