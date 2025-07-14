package lenearlist.code_2025_7_14;

public class IntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 1.计算两个链表的节点个数
        int sz1 = 0;
        int sz2 = 0;
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != null) {
            sz1++;
            p1 = p1.next;
        }
        while (p2 != null) {
            sz2++;
            p2 = p2.next;
        }

        // 2.让长的链表的指针先走
        if (sz1 < sz2) {
            p1 = headB;
            p2 = headA;
        } else {
            p1 = headA;
            p2 = headB;
        }

        // 3.判断是否相交
        for (int i = 0; i < Math.abs(sz1-sz2); i++) {
            p1 = p1.next;
        }
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return null;
    }
}
