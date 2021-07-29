package io.github.harvies.charon.tests.base.algorithm.sort2;

import org.junit.jupiter.api.Test;

class BubbleSortTest extends BaseSortTest {

    @Test
    void sort() {
        Sort<Integer> sort = new Bubble<>();
        sort.sort(sourceArray);
    }
}
