package code_2025_6_26;

import java.util.Arrays;

public class _1CreateArray {
    public static void main(String[] args) {
//        创建一个 int 类型的数组, 元素个数为 100, 并把每个元素依次设置为 1 - 100
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }
}
