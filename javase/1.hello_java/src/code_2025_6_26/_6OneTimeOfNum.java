package code_2025_6_26;

public class _6OneTimeOfNum {
    public static void main(String[] args) {
        int[] testArr = {4,1,2,1,2};
        System.out.println(findOneTimeNum(testArr));
    }

    public static int findOneTimeNum(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int res = 0;
        for (int val : nums) {
            res ^= val;
        }
        return res;
    }
}
