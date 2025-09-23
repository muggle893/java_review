package io;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class _5ScanFileDirectory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        try {
            scan(s, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void scan(String dirPath, int level) throws IOException {
        File file = new File(dirPath);
        if (file.isFile()) {
            return;
        }
        for (File f : file.listFiles()) {
            for (int i = 0; i < level -1 ; i++) {
                System.out.print("----");
            }
            System.out.println(f.getPath());
            if (f.isDirectory()) {
                scan(f.getPath(), level + 1);
            }
        }
    }
}
