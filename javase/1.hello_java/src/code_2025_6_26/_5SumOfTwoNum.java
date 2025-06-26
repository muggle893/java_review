package code_2025_6_26;

import java.util.Arrays;

public class _5SumOfTwoNum {
    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        String res = Arrays.toString(sumOfTwoNum(arr, 9));
        System.out.println(res);
    }
    public static int[] sumOfTwoNum(int[] nums, int target) {
        if (nums == null) {
            return null;
        }
        // 排序+二分查找
        Arrays.sort(nums);
        int[] res = {-1, -1};
        for (int i = 0; i < nums.length; i++) {
            int findIndex = Arrays.binarySearch(nums,0, nums.length, target - nums[i]);
            if (findIndex >= 0) {
                res[0] = i;
                res[1] = findIndex;
                break;
            }
        }
        return res;
    }
}
