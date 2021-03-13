package io.github.harvies.charon.tests.base.algorithm.sort2;

/**
 * 冒泡排序
 * 每趟找出最大的元素
 */
public class Bubble<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (less(nums[j + 1], nums[j])) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }
}
