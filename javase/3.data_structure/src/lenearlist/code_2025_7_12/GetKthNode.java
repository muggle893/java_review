package lenearlist.code_2025_7_12;

public class GetKthNode {
    // 保证给定的k有效, 返回倒数第k个节点
    public int kthToLast(ListNode head, int k) {
        ListNode i1 = head;
        ListNode i2 = head;
        for (int i = 0; i < k - 1; i++) {
            i2 = i2.next;
        }
        while (i2.next != null) {
            i1 = i1.next;
            i2 = i2.next;
        }
        return i1.val;
    }
}
