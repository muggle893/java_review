package tree.code_2025_7_24;

import java.util.Arrays;

public class TestPriorityQueue {
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        int[] arr = {3, 7, 9, 1, 20, 15, 42};

        // 创建堆
        priorityQueue.createHeap(arr);
        System.out.println(Arrays.toString(Arrays.copyOf(priorityQueue.elem, priorityQueue.usedSize)));

        // 测试入堆
        priorityQueue.push(55);
        System.out.println(Arrays.toString(Arrays.copyOf(priorityQueue.elem, priorityQueue.usedSize)));

        // 测试出堆
        priorityQueue.pollHeap();
        System.out.println(Arrays.toString(Arrays.copyOf(priorityQueue.elem, priorityQueue.usedSize)));

        // 测试获取堆顶元素
        int heapTopVal = priorityQueue.peekHeap();
        System.out.println(heapTopVal);

        // 测试堆判空
        System.out.println(priorityQueue.isEmpty());
    }
}
