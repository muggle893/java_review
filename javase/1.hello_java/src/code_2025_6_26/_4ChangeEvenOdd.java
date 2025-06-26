package code_2025_6_26;

import java.util.Arrays;

public class _4ChangeEvenOdd {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        oddBeforeEven(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void oddBeforeEven(int[] arr) {
        if (arr == null) {
            return;
        }
        int i = 0;
        int j = arr.length - 1;
        int n = arr.length;
        while (i < j) {
            // 前面找偶数
            while (i < n && arr[i] % 2 != 0) {
                i++;
            }
            // 后面找奇数
            while (j >= 0 && arr[j] % 2 == 0) {
                j--;
            }
            // 交换
            if (i < j) {
                swap(arr, i, j);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
