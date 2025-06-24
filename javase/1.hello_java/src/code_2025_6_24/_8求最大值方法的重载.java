package code_2025_6_24;

public class _8求最大值方法的重载 {
    public static void main(String[] args) {
        System.out.println(max(3, 4));
        System.out.println(max(3.1, 3.2, 3.3));
    }
    public static int max(int a, int b) {
        return Math.max(a, b);
    }
    public static double max(double a, double b, double c) {
        return Math.max(Math.max(a, b), c);
    }
}
