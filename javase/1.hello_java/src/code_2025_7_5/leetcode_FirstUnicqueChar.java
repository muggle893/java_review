package code_2025_7_5;

import java.util.Arrays;

public class leetcode_FirstUnicqueChar {
    public int firstUniqChar(String s) {
        // 创建cnts数组，用来计数字符出现次数
        int[] cnts = new int[26];
        Arrays.fill(cnts, 0);
        for (int i = 0; i < s.length(); i++) {
            cnts[s.charAt(i) - 'a']++;
        }

        // 再次遍历
        for (int i = 0; i < s.length(); i++) {
            if (cnts[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
