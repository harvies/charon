package io.github.harvies.eris.base.algorithm.leetcode.p25;

import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void reverseKGroup() {
        ReverseNodesInKGroup reverseNodesInKGroup = new Solution();
        ListNode firstListNode = new ListNode(1);
        ListNode secondListNode = new ListNode(2);
        firstListNode.next = secondListNode;
        ListNode thirdListNode = new ListNode(3);
        secondListNode.next = thirdListNode;
        ListNode forthListNode = new ListNode(4);
        thirdListNode.next = forthListNode;
        ListNode fifthListNode = new ListNode(5);
        forthListNode.next = fifthListNode;
        ListNode reverseListNode = reverseNodesInKGroup.reverseKGroup(firstListNode, 2);
    }
}