package io;

import java.io.*;
import java.util.Scanner;

public class _6CopyFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要复制的文件路径：");
        String s = scanner.nextLine();
        File srcFile = new File(s);
        if (!srcFile.isFile()) {
            System.out.println("请输入正确的文件路径!");
            System.exit(0);
        }
        System.out.println("输入目标目录路径：");
        String s1 = scanner.nextLine();
        File destDir = new File(s1);
        if (!destDir.isDirectory()) {
            System.out.println("请输入正确的目录路径!");
            System.exit(0);
        }
        try (FileInputStream fis = new FileInputStream(srcFile);
             FileOutputStream fos = new FileOutputStream(destDir + "/" +  srcFile.getName());){
            byte[] data = new byte[1024];
            int n = fis.read(data);
            while (n > 0) {
                fos.write(data, 0, n);
                n = fis.read(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
