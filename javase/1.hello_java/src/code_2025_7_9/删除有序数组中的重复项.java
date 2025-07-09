package code_2025_7_9;

public class 删除有序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        // 双指针解法
        // 定义指针i，j，0~i为有效元素区间，i + 1~j - 1为可以覆盖的元素区间，j~n - 1为待处理区间
        int i = 0;
        int j = 0;
        for (;j < nums.length; j++) {
            if (j == 0 || nums[j] != nums[j - 1]) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }
}
