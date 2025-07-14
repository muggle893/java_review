package lenearlist.code_2025_7_14;

import java.util.*;

public class PalindromeList {
    public boolean chkPalindrome(ListNode A) {
        // write code here
        if (A == null) {
            return true;
        }
        //1.先找中间节点
        ListNode slow = A;
        ListNode fast = A;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //2.逆序后半部分链表
        ListNode newHead = slow;
        slow = slow.next;
        newHead.next = null;
        while (slow!=null) {
            ListNode nxt = slow.next;
            slow.next = newHead;
            newHead = slow;
            slow = nxt;
        }
        //3.判断回文
        while (newHead!= null && A != newHead) {
            if (A.val != newHead.val) {
                return false;
            } else {
                newHead = newHead.next;
                A = A.next;
            }
        }
        return true;
    }
}
