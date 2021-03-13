package io.github.harvies.charon.tests.base.algorithm.sort2;

/**
 * 选择排序
 * 每次选出最小元素
 */
public class Selection<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        //比较次数
        for (int i = 0; i < nums.length - 1; i++) {
            //最小元素索引
            int minIndex = i;
            //找剩下元素中最小元素
            for (int j = i + 1; j < nums.length; j++) {
                if (less(nums[j], nums[minIndex])) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(nums, i, minIndex);
            }
        }
    }
}
