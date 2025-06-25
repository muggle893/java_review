package code_2025_6_25;

public class _4返回数字之和 {
    public static void main(String[] args) {
        System.out.println(turnBackDigits(12345));
    }
    public static int turnBackDigits(int num) {
        if (num < 10) {
            return num;
        }
        return num % 10 + turnBackDigits(num / 10);
    }
}
