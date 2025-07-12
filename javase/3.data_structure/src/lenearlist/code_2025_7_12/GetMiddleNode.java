package lenearlist.code_2025_7_12;

public class GetMiddleNode {
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = slow.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
        }
        if (fast == null) {
            return slow;
        } else {
            return slow.next;
        }
    }
}
