package code_2025_6_24;

public class _7求和的重载 {
    public static void main(String[] args) {
        System.out.println(sum(2, 3));
        System.out.println(sum(2, 3, 4.0));
    }
    public static int sum(int a, int b) {
        return a + b;
    }
    public static double sum(double a, double b, double c) {
        return a + b + c;
    }
}
