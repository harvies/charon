package io.github.harvies.charon.tests.base.algorithm.sort2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class SelectionSortTest extends BaseSortTest {

    @Test
    void sort() {
        Sort<Integer> sort = new Selection<>();
        sort.sort(sourceArray);
    }

}
