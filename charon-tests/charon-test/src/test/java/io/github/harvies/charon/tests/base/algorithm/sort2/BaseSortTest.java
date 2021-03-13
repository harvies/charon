package io.github.harvies.charon.tests.base.algorithm.sort2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

@Slf4j
public class BaseSortTest {

    protected Integer[] sourceArray;
    protected long begin;

    @BeforeEach
    void before() {
        sourceArray = new Integer[]{5, 3, 1, 2, 4};
        begin = System.currentTimeMillis();
        log.warn("排序前:{}", Arrays.toString(sourceArray));
    }

    @AfterEach
    void after() {
        log.warn("排序后:{} 耗时:{} ms", Arrays.toString(sourceArray), System.currentTimeMillis() - begin);
    }
}
