package io.github.harvies.eris.base.algorithm.leetcode.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * 一遍哈希表
 * Complexity Analysis:
 * <p>
 * Time complexity : O(n)O(n). We traverse the list containing nn elements only once. Each look up in the table costs only O(1)O(1) time.
 * <p>
 * Space complexity : O(n)O(n). The extra space required depends on the number of items stored in the hash table, which stores at most nn elements.
 *
 * @author harvies
 */
public class OnePassHashTable implements TwoSum {
    @Override
    public int[] twoSum(int[] nums, int target) {
        /**
         * 先将数据和索引存入哈希表
         */
        Map<Integer, Integer> map = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{i, map.get(complement)};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
