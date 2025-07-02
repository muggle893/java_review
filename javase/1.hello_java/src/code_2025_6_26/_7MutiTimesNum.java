package code_2025_6_26;

import java.util.HashMap;

public class _7MutiTimesNum {
    public static void main(String[] args) {
        int[] nums = {3,2,3};
        System.out.println(findMutiNum(nums));
    }
    public static int findMutiNum(int[] nums) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int num : nums) {
            if (mp.containsKey(num)) {
                mp.put(num, mp.get(num) + 1);
            } else {
                mp.put(num, 1);
            }
        }
        int n = nums.length;
        int res = -1;
        for (int key : mp.keySet()) {
            if (mp.get(key) > n / 2) {
                res = key;
                break;
            }
        }
        return res;
    }
}
