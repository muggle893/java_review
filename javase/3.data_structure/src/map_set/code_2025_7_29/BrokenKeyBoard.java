package map_set.code_2025_7_29;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BrokenKeyBoard {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();// 应该输入的字符串
        String s2 = in.next();// 键盘坏了之后的字符串
        String s3 = s1.toUpperCase();
        String s4 = s2.toUpperCase();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s4.length(); i++) {
            set.add(s4.charAt(i));
        }
        Set<Character> outSet = new HashSet<>();
        for (int i = 0; i < s3.length(); i++) {
            char c = s3.charAt(i);
            if (!set.contains(c) && !outSet.contains(c)) {
                System.out.print(c);
                outSet.add(c);
            }
        }
    }
}
