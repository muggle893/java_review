package code_2025_6_26;

public class _3SumOfArr {
    public static void main(String[] args) {
        System.out.println(getArrSum(new int[]{1, 2, 3}));
    }

    public static int getArrSum(int[] arr) {
        if (arr == null) {
            return -1;
        }
        else {
            int sum = 0;
            for (int val : arr) {
                sum += val;
            }
            return sum;
        }
    }
}
