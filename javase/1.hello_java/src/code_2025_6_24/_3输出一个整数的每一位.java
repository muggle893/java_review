package code_2025_6_24;

import java.util.Scanner;

public class _3输出一个整数的每一位 {
    public static void main(String[] args) {
        // 输出一个整数的每一位，如：123的每一位是3，2，1
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n != 0) {
            System.out.print(n % 10 + " ");
            n /= 10;
        }

    }
}
