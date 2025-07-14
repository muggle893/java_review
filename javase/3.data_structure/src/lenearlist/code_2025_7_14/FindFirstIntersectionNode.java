package lenearlist.code_2025_7_14;

public class FindFirstIntersectionNode {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(-4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode2;
        ListNode listNode = detectCycle(listNode1);

    }
    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        // 1.先找相交节点
        ListNode slow = head;
        ListNode fast = head;
        while (fast == null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        // 2.再从同一起点开始
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
