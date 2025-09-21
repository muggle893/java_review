package io;

import java.io.File;
import java.io.IOException;

public class _1TestFile {
    public static void main(String[] args) throws IOException {
        File file = new File("./test/name.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        // exists()方法查看此文件是否存在
        System.out.println(file.exists());

        // 获取文件的绝对路径
        System.out.println("getAbsolutePath：" + file.getAbsolutePath());

        // 获取规范的文件路径
        System.out.println("getCanonicalPath：" + file.getCanonicalPath());

        // 获取文件名
        System.out.println("文件名：" + file.getName());

        // 获取上一层目录路径
        System.out.println(file.getParent());

        // 获取问文件路径
        System.out.println("getPath()方法：" + file.getPath());

        // 判断此文件是不是一个目录
        System.out.println("此文件是否为目录：" + file.isDirectory());

        // 判断此文件是不是一个普通文件
        System.out.println("此文件是否为普通文件：" + file.isFile());

        // 列出目录下的文件
        String[] list = file.getParentFile().list();
        for (String str : list) {
            System.out.println("---:" + str);
        }

        // 列出目录下的文件
        File[] files = file.getParentFile().listFiles();
        for (File f : files) {
            System.out.println("---:" + f.getPath());
        }


        // 移动文件
        file.renameTo(new File("./test2/game.txt"));
    }
}
