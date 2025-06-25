package code_2025_6_25;

public class _6递归求解汉诺塔问题 {
    public static void main(String[] args) {
        hanoi(3, 'A', 'B', 'C');
    }

    public static void move(char a, char b) {
        System.out.print(a + "-->" + b + " |");
    }

    public static void hanoi(int n, char a, char b, char c) {
        if (n == 1) {
            move(a, c);
            return;
        }
        hanoi(n - 1, a, c, b);
        move(a, c);
        hanoi(n - 1, b, a, c);
    }
}
