package io.github.harvies.charon.tests.base.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author harvies
 */
@Slf4j
public abstract class AbstractSort {
    public static final int[] sourceArray = {5, 3, 1, 2, 4};


    /**
     * 排序
     *
     * @param sourceArray
     * @return
     */
    abstract public int[] sort(int[] sourceArray);

    public void main0() {
        long begin = System.currentTimeMillis();
        log.warn("排序前:{}", Arrays.toString(sourceArray));
        int[] sort = sort(sourceArray);
        log.warn("排序后:{} 耗时:{} ms", Arrays.toString(sort), System.currentTimeMillis() - begin);
    }
}
