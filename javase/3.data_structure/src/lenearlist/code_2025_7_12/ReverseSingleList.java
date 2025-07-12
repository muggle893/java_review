package lenearlist.code_2025_7_12;

public class ReverseSingleList {
    public ListNode reverseList(ListNode head) {
        ListNode head2 = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            if (head2 == null) {
                head2 = cur;
                head2.next = null;
            } else {
                cur.next = head2;
                head2 = cur;

            }
            cur = nxt;
        }
        return head2;
    }
}
