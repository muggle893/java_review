package tree.code_2025_7_24;

import java.util.Arrays;

public class PriorityQueue {
    public int[] elem;
    public int usedSize;
    public static final int defaultSize = 10;
    public PriorityQueue() {
        usedSize = 0;
        elem = new int[defaultSize];
    }

    /**
     * 建堆的时间复杂度：
     *
     * @param array
     */
    public void createHeap(int[] array) {
        elem = Arrays.copyOf(array, array.length);
        usedSize = array.length;
        // 从第一个有孩子的节点开始向下调整
        int i = (usedSize - 1) / 2;
        for (; i >= 0; i--) {
            shiftDown(i, usedSize);
        }
    }

    /**
     *
     * @param root 是每棵子树的根节点的下标
     * @param len  是每棵子树调整结束的结束条件
     * 向下调整的时间复杂度：O(logn)
     */
    private void shiftDown(int root,int len) {
        int tmpVal = elem[root];
        int childIndex =  root * 2 + 1;
        while (childIndex < len) {
            if (childIndex + 1 < len && elem[childIndex] < elem[childIndex + 1]) {
                childIndex++;
            }
            if (elem[childIndex] > tmpVal) {
                elem[root] = elem[childIndex];
                root = childIndex;
                childIndex = childIndex * 2 + 1;
            }
        }
        elem[root] = tmpVal;
    }


    /**
     * 入队：仍然要保持是大根堆
     * @param val
     */
    public void push(int val) {
        // 检查是否要扩容
        if (isFull()) {
            elem = Arrays.copyOf(elem, elem.length * 2);
        }
        elem[usedSize++] = val;
        shiftUp(usedSize - 1);
    }

    private void shiftUp(int child) {
        int tmpVal = elem[child];
        while (child > 0) {
            int parentIndex = (child - 1) / 2;
            if (elem[parentIndex] < tmpVal) {
                elem[child] = elem[parentIndex];
            }
            child = parentIndex;
        }
        elem[child] = tmpVal;
    }

    public boolean isFull() {
        if (usedSize == elem.length) {
            return true;
        }
        return false;
    }

    /**
     * 出队【删除】：每次删除的都是优先级高的元素
     * 仍然要保持是大根堆
     */
    public void pollHeap() {
        if (isEmpty()) {
            throw new RuntimeException("优先级队列为空，不可以弹出元素！");
        }
        elem[0] = elem[--usedSize];
        shiftDown(0, usedSize);
    }

    public boolean isEmpty() {
        if (usedSize == 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取堆顶元素
     * @return
     */
    public int peekHeap() {
        if (isEmpty()) {
            throw new RuntimeException("优先级队列为空，不可以获取堆顶元素！");
        }
        return elem[0];
    }
}
