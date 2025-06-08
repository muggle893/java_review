import java.util.Scanner;

public class JudgePrimeNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        boolean isPrime = true;
        // 素数规定大于1
        if (num <= 1) {
            isPrime = false;
        }
        for (int i = 2; i <= num - 1; i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            System.out.println(num + "是素数");
        }
        else {
            System.out.println(num + "不是素数");
        }
    }
}
