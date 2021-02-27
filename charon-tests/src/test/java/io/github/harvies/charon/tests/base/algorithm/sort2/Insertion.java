package io.github.harvies.charon.tests.base.algorithm.sort2;

/**
 * 插入排序
 *
 * 从第二个元素开始，依次和前面所有元素比较，插入到前面元素中
 */
public class Insertion<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (less(nums[i], nums[j])) {
                    swap(nums, i, j);
                }
            }
        }
    }
}
