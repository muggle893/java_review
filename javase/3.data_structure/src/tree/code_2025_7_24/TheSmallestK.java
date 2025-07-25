package tree.code_2025_7_24;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TheSmallestK {
    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int[] res = new int[k];
        if (k == 0) {
            return res;
        }
        int i;
        // 创建大根堆
        for (i = 0; i < k; i++) {
            pq.offer(arr[i]);
        }
        for(; i < arr.length; i++) {
            if (arr[i] < pq.peek()) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }
        i = 0;
        while (!pq.isEmpty()) {
            res[i++] = pq.poll();
        }
        return res;
    }
}
