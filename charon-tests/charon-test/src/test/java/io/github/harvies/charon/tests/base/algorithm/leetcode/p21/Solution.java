package io.github.harvies.charon.tests.base.algorithm.leetcode.p21;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
interface Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2);
}
