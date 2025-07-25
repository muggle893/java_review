package tree.code_2025_7_25;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Sort {

    public static int[] getRandomArray(int len) {
        int[] nums = new int[len];
        // 随机生成数字（1~len)，放到nums中
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            nums[i] = random.nextInt(len) + 1;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] randomArray = getRandomArray(10);

        System.out.println("直接插入排序测试：");
        System.out.println("排序前：" + Arrays.toString(randomArray));
        insertSort(randomArray);
        System.out.println("排序后：" + Arrays.toString(randomArray));

        randomArray = getRandomArray(20);
        System.out.println("希尔排序测试：");
        System.out.println("排序前：" + Arrays.toString(randomArray));
        shellSort(randomArray);
        System.out.println("排序后：" + Arrays.toString(randomArray));

        randomArray = getRandomArray(20);
        System.out.println("选择排序测试：");
        System.out.println("排序前：" + Arrays.toString(randomArray));
        selectSort(randomArray);
        System.out.println("排序后：" + Arrays.toString(randomArray));

        randomArray = getRandomArray(20);
        System.out.println("堆排序测试：");
        System.out.println("排序前：" + Arrays.toString(randomArray));
        heapSort(randomArray);
        System.out.println("排序后：" + Arrays.toString(randomArray));

        randomArray = getRandomArray(20);
        System.out.println("选择排序测试：");
        System.out.println("排序前：" + Arrays.toString(randomArray));
        selectSort(randomArray);
        System.out.println("排序后：" + Arrays.toString(randomArray));

        randomArray = getRandomArray(5);
        System.out.println("快速排序测试：");
        System.out.println("排序前：" + Arrays.toString(randomArray));
        quickSort(randomArray);
        System.out.println("排序后：" + Arrays.toString(randomArray));
    }

    // 插入排序
    public static void insertSort(int[] array){
        // write code here
        if (array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int tmpVal = array[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > tmpVal) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = tmpVal;
        }
    }




    // 希尔排序
     public static void shellSort(int[] array){
        // write code  here
        int gap = array.length / 3 + 1;
        while (gap > 1) {
            shell(array, gap);
            gap = gap / 3 + 1;
        }
        shell(array, gap);
    }

    public static void shell(int[] nums, int gap) {
        for (int i = gap; i < nums.length; i++) {
            int tmpVal = nums[i];
            int j;
            for (j = i - gap; j >= 0; j -= gap) {
                if (nums[j] > tmpVal) {
                    nums[j + gap] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + gap] = tmpVal;
        }
    }

    // 选择排序
    public static void selectSort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            int minValIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minValIndex]) {
                    minValIndex = j;
                }
            }
            swap(array, i, minValIndex);
        }
    }

    public static void swap(int[] array, int p1, int p2) {
        int tmp = array[p1];
        array[p1] = array[p2];
        array[p2] = tmp;
    }


    // 堆排序
    public static void heapSort(int[] array){
        // 建堆(建立大根堆)
        createHeap(array);
        // 交换
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            shitDown2(array, 0, i);
        }
    }

    public static void shitDown2(int[] array, int root, int len) {
        int tmpVal = array[root]; // 保存向下调整节点的值
        int parent = root;
        int child = root * 2 +  1;
        while (child < len) {
            if (child + 1 < len && array[child + 1] > array[child]) {
                child++;
            }
            if (array[child] > tmpVal) {
                swap(array, child, parent);
            } else {
                break;
            }
            // 更新
            parent = child;
            child = child * 2 + 1;
        }
        array[parent] = tmpVal;
    }

    public static void shitDown(int[] array, int root) {
        int tmpVal = array[root]; // 保存向下调整节点的值
        int parent = root;
        int child = root * 2 +  1;
        while (child < array.length) {
            if (child + 1 < array.length && array[child + 1] > array[child]) {
                child++;
            }
            if (array[child] > tmpVal) {
                swap(array, child, parent);
            } else {
                break;
            }
            // 更新
            parent = child;
            child = child * 2 + 1;
        }
        array[parent] = tmpVal;
    }
    public static void createHeap(int[] array) {
        for (int i = (array.length - 1) / 2; i >= 0; i--) {
            shitDown(array, i);
        }
    }

    // 冒泡排序
    public static void bubbleSort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            boolean exchanged = false;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    exchanged = true;
                }
            }
            if (!exchanged) {
                return;
            }
        }
    }

    // 快速
    public static void quickSort(int[] array){
        quickSort(array, 0, array.length - 1);
    }
    public static void quickSort(int[] array, int left, int right){
        if (left >= right) {
            return;
        }
        int pivot = partition(array, left, right);
        quickSort(array, left, pivot - 1);
        quickSort(array, pivot + 1, right);
    }

    private static int partition2(int[] array, int left, int right) {
        int pivot = array[left];  // 基准值取左端
        int i = left, j = right;
        while (i < j) {
            // 从右向左找第一个小于pivot的值
            while (i < j && array[j] >= pivot) j--;
            // 从左向右找第一个大于pivot的值
            while (i < j && array[i] <= pivot) i++;
            if (i < j) swap(array, i, j);
        }
        // 将基准值放到正确位置
        swap(array, left, i);
        return i;
    }

    public static int partition(int[] array, int left, int right) {
        int tmpVal = array[left];
        while (left < right) {
            // 右边找一个数
            while (left < right && array[right] >= tmpVal) {
                right--;
            }
            // 放入坑中
            array[left] = array[right];
            // 左边找一个数
            while (left < right && array[left] <= tmpVal) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = tmpVal;
        return left;
    }
}
