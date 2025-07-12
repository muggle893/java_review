package lenearlist.code_2025_7_12;

public class MergeTwoSortedList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode list3 = null;
        ListNode tail3 = null;
        while (list1 != null && list2 != null) {
            ListNode tmpNode = null;
            if (list1.val < list2.val) {
                tmpNode = list1;
                list1 = list1.next;
            } else {
                tmpNode = list2;
                list2 = list2.next;
            }
            if (list3 == null) {
                list3 = tmpNode;
                tail3 = tmpNode;
                list3.next = null;
            } else {
                tail3.next = tmpNode;
                tail3 = tmpNode;
                tail3.next = null;
            }
        }
        while (list1 != null) {
            ListNode tmpNode = list1;
            list1 = list1.next;
            if (list3 == null) {
                list3 = tmpNode;
                tail3 = tmpNode;
                list3.next = null;
            } else {
                tail3.next = tmpNode;
                tail3 = tmpNode;
                tail3.next = null;
            }
        }
        while (list2 != null) {
            ListNode tmpNode = list2;
            list2 = list2.next;
            if (list3 == null) {
                list3 = tmpNode;
                tail3 = tmpNode;
                list3.next = null;
            } else {
                tail3.next = tmpNode;
                tail3 = tmpNode;
                tail3.next = null;
            }
        }
        return list3;
    }
}
