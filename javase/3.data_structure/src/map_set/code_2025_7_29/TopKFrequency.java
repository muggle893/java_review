package map_set.code_2025_7_29;

import java.util.*;

public class TopKFrequency {
    /**
     * 给定一个单词列表 words 和一个整数 k ，返回前 k 个出现次数最多的单词。
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue()) {
                    return o2.getKey().compareTo(o1.getKey());
                }
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        // 1.统计每个单词的频率
        for (String w : words) {
            if (map.containsKey(w)) {
                map.put(w, map.get(w) + 1);
            } else {
                map.put(w, 1);
            }
        }
        // 2.建小根堆（这里要改一下比较器），然后输出
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if (pq.size() < k) {
                pq.add(entry);
            } else {
                if (entry.getValue() > pq.peek().getValue()) {
                    pq.poll();
                    pq.add(entry);
                } else if (entry.getValue() == pq.peek().getValue()){
                    if (entry.getKey().compareTo(pq.peek().getKey()) < 0) {
                        pq.poll();
                        pq.add(entry);
                    }
                }
            }
        }
        LinkedList<String> list = new LinkedList<>();
        while (!pq.isEmpty()) {
            list.addFirst(pq.poll().getKey());
        }
        return list;
    }
}
