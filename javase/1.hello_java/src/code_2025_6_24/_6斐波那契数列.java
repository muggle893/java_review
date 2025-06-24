package code_2025_6_24;

import java.util.Scanner;

public class _6斐波那契数列 {
    public static void main(String[] args) {
        // 求斐波那契数列的第n项。(迭代实现)
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = 1, b = 1;
        for (int i = 3; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        System.out.println("斐波那契数列的第" + n + "项为：" + b);
    }
}
