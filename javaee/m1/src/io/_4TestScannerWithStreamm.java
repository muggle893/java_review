package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

public class _4TestScannerWithStreamm {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("./test2/game3.txt")) {
            Scanner scanner = new Scanner(fis);
            String str = scanner.next();
            System.out.println(str);
            System.out.println(scanner.nextInt());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
