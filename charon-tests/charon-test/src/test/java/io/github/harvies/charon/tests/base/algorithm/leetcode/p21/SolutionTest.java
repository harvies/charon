package io.github.harvies.charon.tests.base.algorithm.leetcode.p21;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class SolutionTest {

    @Test
    void testRecursionSolution() {
        Solution solution = new RecursionSolution();
        extracted(solution);
    }

    @Test
    void testIntegratorSolution() {
        Solution solution = new IntegratorSolution();
        extracted(solution);
    }

    private void extracted(Solution solution) {
        ListNode listNode1 = ListUtils.arrayToListNode(new Integer[]{1, 2, 4});
        ListNode listNode2 = ListUtils.arrayToListNode(new Integer[]
                {1, 3, 4});
        System.out.println(listNode1);
        System.out.println(listNode2);
        ListNode actual = solution.mergeTwoLists(listNode1, listNode2);
        System.out.println(actual);
        ListNode expect = ListUtils.arrayToListNode(new Integer[]{1, 1, 2, 3, 4, 4});
        assertThat(actual, is(expect));
    }
}
