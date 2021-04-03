package io.github.harvies.charon.tests.base.algorithm.leetcode.p21;

import lombok.ToString;

import java.util.Objects;

@ToString
public class ListNode {
    public Integer val;
    public ListNode next;

    ListNode() {
    }

    ListNode(Integer val) {
        this.val = val;
    }

    ListNode(Integer val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val && Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }
}
