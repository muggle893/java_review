package map_set.code_2025_7_29;

import java.util.Arrays;

// key-value 模型
public class HashBucket {

    public static void main(String[] args) {
        HashBucket hashBucket = new HashBucket();
        hashBucket.put(1, 5);
        hashBucket.put(2, 4);
        hashBucket.put(3, 1);
        hashBucket.put(9, 6);
        hashBucket.put(88, 5);
        hashBucket.put(55, 4);
        hashBucket.put(34, 1);
        hashBucket.put(23, 6);
        System.out.println(hashBucket.get(34));
        System.out.println(hashBucket.get(55));
        System.out.println(hashBucket.get(23));
        System.out.println(hashBucket.get(7));
        System.out.println(hashBucket.get(1));
    }

    private static class Node {
        private int key;
        private int value;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[]  array;
    private int size;   // 当前的数据个数
    private static final double LOAD_FACTOR = 0.75;
    private static final int DEFAULT_SIZE = 8;//默认桶的大小

    public int put(int key, int value) {
        // 在将元素放入哈希表之前，要检查负载因子是否满足条件
        if (loadFactor() >= LOAD_FACTOR) {
            // 超过默认负载因子将哈希表扩容
            resize();
        }
        int index = key % array.length;
        HashBucket.Node newNode = new HashBucket.Node(key, value);
        newNode.next = array[index];
        array[index] = newNode;
        return 1;
    }


    private void resize() {
        // 1.扩容
        HashBucket.Node[] newArr = Arrays.copyOf(array, array.length * 2);
        // 2.重新hash
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                HashBucket.Node cur = array[i];
                HashBucket.Node tmp;
                while (cur != null) {
                    tmp = cur.next;// 保存cur的下一个节点引用
                    // 计算重新后的hash值
                    int newIndex = cur.key % newArr.length;
                    // 放入哈希桶中
                    cur.next = newArr[newIndex];
                    newArr[newIndex] = cur;
                    // 更新cur
                    cur = tmp;
                }
            }
        }
    }


    private double loadFactor() {
        return size * 1.0 / array.length;
    }


    public HashBucket() {
        this.array = new Node[DEFAULT_SIZE];
        this.size = 0;
    }


    /**
     *
     * @param key
     * @return获取value成功返回key对应的value
     */
    public int get(int key) {
        int index = key % array.length;
        HashBucket.Node cur = array[index];
        while (cur != null) {
            if (cur.key == key) {
                return cur.value;
            }
            cur = cur.next;
        }
        return -1;
    }
}

