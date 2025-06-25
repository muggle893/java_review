package code_2025_6_25;

public class _3打印数字的每一位 {
    public static void main(String[] args) {
        printBitsOfNum(1234);
    }
    public static void printBitsOfNum(int num) {
        if (num < 10) {
            System.out.print(num + " ");
            return;
        }
        printBitsOfNum(num / 10);
        System.out.print(num % 10 + " ");
    }
}
