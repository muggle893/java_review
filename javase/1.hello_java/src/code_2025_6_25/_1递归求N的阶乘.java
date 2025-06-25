package code_2025_6_25;

public class _1递归求N的阶乘 {
    public static void main(String[] args) {
        System.out.println(f(3));
    }

    public static int f(int N) {
        if (N == 1) {
            return 1;
        }
        return N * f(N - 1);
    }
}
