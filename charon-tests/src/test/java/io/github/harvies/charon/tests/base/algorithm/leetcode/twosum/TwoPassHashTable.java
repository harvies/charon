package io.github.harvies.charon.tests.base.algorithm.leetcode.twosum;


import io.github.harvies.charon.annotations.Complexity;

import java.util.HashMap;
import java.util.Map;

/**
 * 两遍哈希表
 *
 * @author harvies
 */
@Complexity(time = "n", space = "n")
public class TwoPassHashTable implements TwoSum {

    @Override
    public int[] twoSum(int[] nums, int target) {
        /**
         * 先将数据和索引存入哈希表
         */
        //key是数据 value是索引
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            //不等于自己
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("no solution");
    }
}
