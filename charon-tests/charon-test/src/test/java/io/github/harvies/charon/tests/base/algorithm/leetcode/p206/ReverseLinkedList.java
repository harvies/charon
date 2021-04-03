package io.github.harvies.charon.tests.base.algorithm.leetcode.p206;

import io.github.harvies.charon.tests.base.algorithm.leetcode.p21.ListNode;
import io.github.harvies.charon.tests.base.algorithm.leetcode.p21.ListUtils;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = ListUtils.arrayToListNode(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(head);

        System.out.println(new ReverseLinkedList().reverseList(head));

    }

    public ListNode reverseList(ListNode head) {
        //前指针
        ListNode p = null;
        //当前指针
        ListNode c = head;
        //临界值是当前指针到达原链表尾部下一个节点，即Null
        while (c != null) {
            //下一个节点
            ListNode next = c.next;
            //p指向当前指针下一个节点
            c.next = p;
            //前指针前移
            p = c;
            //当前指针前移
            c = next;
        }
        //前指针到达尾节点，即为新链表头节点
        return p;
    }
}
