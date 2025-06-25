package code_2025_6_25;

public class _2递归求和 {
    public static void main(String[] args) {
        System.out.println(sum(10));
    }

    public static int sum(int N) {
        if (N == 1) {
            return 1;
        }
        return N + sum(N - 1);
    }
}
