package org.iceberg.leetcode;

/**
 * Created by xiaoyuzhzh on 2020/6/20.
 */
public class TwoNumPlus {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;

        if (l1.next == null && l1.val == 0) {
            return l2;
        }

        if (l1.next == null && l1.val == 0) {
            return l1;
        }

        int next = (l1.val + l2.val) / 10;
        head = new ListNode((l1.val + l2.val) % 10);
        current = head;
        while (l1.next != null) {
            if (l2.next != null) {
                l1 = l1.next;
                l2 = l2.next;
                current.next = new ListNode((l1.val + l2.val + next) % 10);
                next = (l1.val + l2.val + next) / 10;
            } else{
                appendList(next,l1,current);
                next = 0;
                break;
            }
            current = current.next;
        }

        //l2还有剩余
        if(l2.next!=null){
            appendList(next,l2,current);
            next = 0;
        }

        //最后还有进位
        if(next>0){
            current.next = new ListNode(next);
        }

        return head;
    }

    //添加单链的尾数
    private void appendList(int next, ListNode tail, ListNode current) {
        while (tail.next != null) {
            tail = tail.next;
            if(next>0){//有进位，需要继续计算
                current.next = new ListNode((tail.val + next) % 10);
                next = (tail.val + next) / 10;
            }else {
                current.next = tail;
            }
            current = current.next;
        }
        //最后还有进位
        if(next>0){
            current.next = new ListNode(next);
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
