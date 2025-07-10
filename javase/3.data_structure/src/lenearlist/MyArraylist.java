package lenearlist;

import lenearlist.exception.ArrayPosException;

import java.util.Arrays;

public class MyArraylist {

    private int[] elem;
    private int usedSize;//0
    //默认容量
    private static final int DEFAULT_SIZE = 10;

    public MyArraylist() {
        this.elem = new int[DEFAULT_SIZE];
    }


    /**
     * 打印顺序表:
     * 根据usedSize判断即可
     */
    public void display() {
        for (int i = 0; i < usedSize; i++) {
            System.out.print(elem[i] + " ");
        }
        // 换行
        System.out.println();
    }

    private void expandList() {
        elem = Arrays.copyOf(elem, elem.length * 2);
    }

    // 新增元素,默认在数组最后新增
    public void add(int data) {
        // 如果数组满了就扩容
        if (isFull()) {
            // 扩容
            expandList();
        }
        // 新增元素
        elem[usedSize++] = data;
    }

    /**
     * 判断当前的顺序表是不是满的！
     *
     * @return true:满   false代表空
     */
    public boolean isFull() {
        if (usedSize == elem.length) {
            return true;
        }
        return false;
    }


    private boolean checkPosInAdd(int pos) {
        if (pos >= 0 && pos < usedSize) {
            return true;
        }
        return false;
    }

    // 在 pos 位置新增元素
    public void add(int pos, int data) {
        // 检查pos是否合法，不合法要抛异常
        if (!checkPosInAdd(pos)) {
            throw new ArrayPosException("参数的pos越界,pos= " + pos);
        }
        // 检查数组是否满了, 满了就要扩容
        if (isFull()) {
            if (isFull()) {
                // 扩容
                expandList();
            }
        }
        // 将pos位置后面的元素往后移动, 再插入元素
        for (int i = usedSize - 1; i >= pos; i--) {
            elem[i + 1] = elem[i];
        }
        elem[pos] = data;
        usedSize++;
    }

    // 判定是否包含某个元素
    public boolean contains(int toFind) {
        for (int i = 0; i < usedSize; i++) {
            if (toFind == elem[i]) {
                return true;
            }
        }
        return false;
    }

    // 查找某个元素对应的位置
    public int indexOf(int toFind) {
        for (int i = 0; i < usedSize; i++) {
            if (toFind == elem[i]) {
                return i;
            }
        }
        return -1;
    }

    // 获取 pos 位置的元素
    public int get(int pos) {
        if (!checkPosInAdd(pos)) {
            throw new ArrayPosException("参数的pos越界,pos= " + pos);
        }
        return elem[pos];
    }

    private boolean isEmpty() {
        if (usedSize == 0) {
            return true;
        }
        return false;
    }

    // 给 pos 位置的元素设为【更新为】 value
    public void set(int pos, int value) {
        if (!checkPosInAdd(pos)) {
            throw new ArrayPosException("参数的pos越界,pos= " + pos);
        }
        elem[pos] = value;
    }

    /**
     * 删除第一次出现的关键字key
     *
     * @param key
     */
    public void remove(int key) {
        int index = indexOf(key);
        if (index != -1) {
            for (int i = index; i < usedSize - 1; i++) {
                elem[i] = elem[i + 1];
            }
            usedSize--;
        }
    }

    // 获取顺序表长度
    public int size() {
        return usedSize;
    }

    // 清空顺序表
    public void clear() {
        usedSize = 0;
    }

}
