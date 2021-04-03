package io.github.harvies.charon.tests.base.algorithm.leetcode.p21;

public class ListUtils {
    //数组转换成链表
    public static ListNode arrayToListNode(Integer[] s) {
        ListNode root = new ListNode(s[0]);//生成链表的根节点，并将数组的第一个元素的值赋给链表的根节点
        ListNode other = root;//生成另一个节点，并让other指向root节点，other在此作为一个临时变量，相当于指针
        for (int i = 1; i < s.length; i++) {//由于已给root赋值，所以i从1开始
            ListNode temp = new ListNode(s[i]);//每循环一次生成一个新的节点,并给当前节点赋值
            other.next = temp;//将other的下一个节点指向生成的新的节点
            other = temp;//将other指向最后一个节点(other的下一个节点)  other=other.getNext();
        }
        return root;
    }

    /**
     * 遍历一个链表
     */
    public static void printListNode(ListNode l) {
        while (l != null) {
            System.out.print(l.val + " ");
            l = l.next;
        }
    }

    //输出数组
    public static void printArrays(Integer[] ints) {
        for (int x : ints) {
            System.out.print(x + " ");
        }
    }

    //将链表转换成数组
    public static Integer[] listNodeToArray(ListNode l) {
        int size = listNodeSize(l);
        Integer[] ints = new Integer[size];
        int index = 0;
        while (l != null) {
            ints[index] = l.val;
            l = l.next;
            index++;
        }
        return ints;
    }

    //求链表的长度
    public static Integer listNodeSize(ListNode l) {
        int size = 0;
        while (l != null) {
            size++;
            l = l.next;
        }
        return size;
    }
}
