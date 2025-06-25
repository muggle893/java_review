package code_2025_6_25;

public class _5返回斐波那契数列第N项 {
    public static void main(String[] args) {
        System.out.println(fabNItem(10));
    }
    public static int fabNItem(int N) {
        int a = 1;
        int b = 1;
        int c = 0;
        for (int i = 3; i <= N; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
