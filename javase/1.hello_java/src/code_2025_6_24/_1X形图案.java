package code_2025_6_24;

import java.util.Scanner;

public class _1X形图案 {
    public static void main(String[] args) {
        // 当i == j 和i + j == n - 1的时候输出*, 其他情况输出空格
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || i + j == n - 1) {
                    System.out.print('*');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}
