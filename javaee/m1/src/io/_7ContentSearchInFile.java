package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class _7ContentSearchInFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要搜索的文件路径：");
        String path = scanner.nextLine();
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("文件路径非法，文件不存在!");
            return;
        }
        System.out.println("请输入搜索的内容：");
        String word = scanner.nextLine();
        searchContent(file, word);
    }
    public static void searchContent(File f, String target) {
        if (f.isFile()) {
            match(f, target);
            return;
        }
        for (File tmpFile : f.listFiles()) {
            searchContent(tmpFile, target);
        }
    }

    public static void match(File f, String target) {
        try (FileInputStream fis = new FileInputStream(f)){
            StringBuilder strBuilder = new StringBuilder();
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNextLine()) {
                strBuilder.append(scanner.nextLine());
            }
            if (strBuilder.toString().contains(target)) {
                System.out.println("找到搜索的内容，文件路径为->:" + f.getCanonicalPath());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
