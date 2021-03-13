package io.github.harvies.charon.tests.base.algorithm.sort2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertionTest extends BaseSortTest {

    @Test
    void sort() {
        Sort<Integer> sort = new Insertion<>();
        sort.sort(sourceArray);
    }
}
