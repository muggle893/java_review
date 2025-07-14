package lenearlist.code_2025_7_14;

import lenearlist.code_2025_7_12.SingleLinkedList;

public class MoveNodeInList {

    // 现有一链表的头指针 ListNode* pHead，给一定值x，
    // 编写一段代码将所有小于x的结点排在其余结点之前，且不能改变原来的数据顺序，返回重新排列后的链表的头指针。
    public ListNode partition(ListNode pHead, int x) {
        // write code here
        // 将链表分成两个List, list1和list2, 然后用尾插法将小于x的结点放入list1
        // 将大于等于x的节点放入list2
        ListNode list1 = null, tail1 = null;
        ListNode list2 = null, tail2 = null;
        while (pHead != null) {
            ListNode nxt = pHead.next;
            if (pHead.val < x) {
                if (list1 == null) {
                    list1 = pHead;
                    tail1 = pHead;
                } else {
                    tail1.next = pHead;
                    tail1 = pHead;
                }
                tail1.next = null;
            } else {
                if (list2 == null) {
                    list2 = pHead;
                    tail2 = pHead;
                } else {
                    tail2.next = pHead;
                    tail2 = pHead;
                }
                tail2.next = null;
            }
            pHead = nxt;
        }

        // 将两个链表链接起来
        if (list1 == null) {
            return list2;
        } else {
            tail1.next = list2;
            return list1;
        }
    }
}
