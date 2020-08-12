package io.github.harvies.eris.base.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author harvies
 */
public class BubbleSort extends AbstractSort {
    public static void main(String[] args) {
        new BubbleSort().main0();
    }

    @Override
    public int[] sort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        /**
         * 外层循环用户冒泡(每次找出最大的)
         */
        for (int i = 0; i < arr.length; i++) {
            /**
             * 内层循环用于交换数据,-i是因为后面每次排序从i开始即可不用从0开始
             */
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int swap = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = swap;
                }
            }
        }
        return arr;
    }
}
