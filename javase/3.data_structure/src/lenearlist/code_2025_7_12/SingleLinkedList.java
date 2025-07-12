package lenearlist.code_2025_7_12;

// 1、无头单向非循环链表实现
public class SingleLinkedList {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addFirst(3);
        singleLinkedList.addFirst(2);
        singleLinkedList.addFirst(1);
        singleLinkedList.display();
        singleLinkedList.addIndex(2, 4);
        singleLinkedList.display();
        singleLinkedList.addLast(6);
        singleLinkedList.display();
        singleLinkedList.remove(3);
        singleLinkedList.display();
        singleLinkedList.addLast(7);
        singleLinkedList.addLast(7);
        singleLinkedList.addLast(7);
        singleLinkedList.display();
        singleLinkedList.removeAllKey(7);
        singleLinkedList.display();
    }

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.next = null;
            this.val = val;
        }
    }

    public ListNode head;//null

    public SingleLinkedList() {
        this.head = null;
    }
    //头插法
    public void addFirst(int data) {
        ListNode listNode = new ListNode(data);
        listNode.next = head;
        head = listNode;
    }
    //尾插法
    public void addLast(int data) {
        // 找到最后一个节点的前一个节点
        if (head == null) {
            head = new ListNode(data);
        } else {
            ListNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new ListNode(data);
        }
    }
    //任意位置插入,第一个数据节点为0号下标
    public void addIndex(int index,int data) throws IndexException{
        if (index <= -1 || index > size()) {
            throw new IndexException("index超出范围，index=" + index);
        }
        ListNode node = new ListNode(data);
        // 找到要插入节点的前一个节点
        if (head == null) {
            head = node;
        } else {
            ListNode cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            node.next = cur.next;
            cur.next = node;
        }
    }
    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        ListNode cur = head;
        while (head != null) {
            if (head.val == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    //删除第一次出现关键字为key的节点
    public void remove(int key) {
        if (head == null) {
            return;
        }
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == key) {
                break;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        if (prev == null) {
            head = head.next;
        } else if (prev != null && cur != null) {
            prev.next = cur.next;
            cur.next = null;
        }

    }
    //删除所有值为key的节点
    public void removeAllKey(int key) {
        if (head == null) {
            return;
        }
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            if (cur.val == key) {
                if (prev != null) {
                    prev.next = cur.next;
                } else {
                    head = head.next;
                }
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
    }
    //得到单链表的长度
    public int size() {
        int cnt = 0;
        ListNode cur = head;
        while (cur != null) {
            cnt++;
            cur = cur.next;
        }
        return cnt;
    }
    public void display() {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    public void clear() {
        head = null;
    }
}
