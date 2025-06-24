package code_2025_6_24;

import java.util.Scanner;

public class _4模拟登录 {
    public static void main(String[] args) {
        // 编写代码模拟三次密码输入的场景。 最多能输入三次密码，密码正确，
        // 提示“登录成功”,密码错误， 可以重新输 入，最多输入三次。三次均错，则提示退出程序
        int count = 0;
        String pwd = "123";
        Scanner scanner = new Scanner(System.in);
        while (count < 3) {
            String inp = scanner.next();
            if (pwd.equals(inp)) {
                System.out.println("登录成功!");
                break;
            } else {
                count++;
                if (count == 3) {
                    System.out.println("退出程序!");
                    break;
                }
                System.out.print("密码错误请重新输入:");
            }
        }
    }
}
