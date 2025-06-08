import java.util.Scanner;

public class PrintPrimeNum {
    public static boolean isPrimeNum(int num) {
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
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String[] args) {
        System.out.println("1-100之间的素数");
        for (int i = 1; i <= 100; i++) {
            if (isPrimeNum(i)) {
                System.out.print(i + ", ");
            }
        }
    }


}
