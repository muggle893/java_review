package code_2025_7_9;

import java.util.Arrays;

public class 合并两个数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
       // 先准备一个大小为m的数组，并将nums1的元素拷贝进去
        int[] nums3 = Arrays.copyOf(nums1, m);
        int cur = 0;
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (nums3[i] < nums2[j]) {
                nums1[cur++] = nums3[i++];
            } else {
                nums1[cur++] = nums2[j++];
            }
        }
        while (i < m) {
            nums1[cur++] = nums3[i++];
        }
        while (j < n) {
            nums1[cur++] = nums2[j++];
        }
    }
}
