package code_2025_6_24;

public class _5使用函数求最大值 {
    public static void main(String[] args) {
        System.out.println(max3(11, 10, 33));
    }
    public static int max3(int n1, int n2, int n3) {
        return max2(n1, max2(n2, n3));
    }
    public static int max2(int n1, int n2) {
        if (n1 > n2) {
            return n1;
        } else {
            return n2;
        }
    }
}
