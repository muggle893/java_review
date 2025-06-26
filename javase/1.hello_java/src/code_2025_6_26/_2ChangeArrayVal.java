package code_2025_6_26;

import java.util.Arrays;

public class _2ChangeArrayVal {
    public static void main(String[] args) {
        //实现一个方法 transform, 以数组为参数,
        //循环将数组中的每个元素 乘以 2 , 并设置到对应的数组元素上. 例如 原数组为 {1, 2, 3}, 修改之后为 {2, 4, 6}
        int[] arr = {1, 2, 3};
        System.out.println("转换前：" + Arrays.toString(arr));
        transform(arr);
        System.out.println("转换后：" + Arrays.toString(arr));
    }
    public static void transform(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] *= 2;
            }
        }
    }
}
