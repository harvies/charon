package io.github.harvies.eris.base.algorithm.leetcode.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * 两遍哈希表
 * <p>
 * Complexity Analysis:
 * <p>
 * Time complexity : O(n)O(n). We traverse the list containing nn elements exactly twice. Since the hash table reduces the look up time to O(1)O(1), the time complexity is O(n)O(n).
 * <p>
 * Space complexity : O(n)O(n). The extra space required depends on the number of items stored in the hash table, which stores exactly nn elements.
 *
 * @author harvies
 */
public class TwoPassHashTable implements TwoSum {

    @Override
    public int[] twoSum(int[] nums, int target) {
        /**
         * 先将数据和索引存入哈希表
         */
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            /**
             * 如果剩下的数不是当前遍历的数则找到了
             */
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[0];
    }
}
