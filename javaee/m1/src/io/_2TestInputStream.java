package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class _2TestInputStream {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("./test2/game.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // read读取一个字节
        int data = fis.read();
        System.out.println("读取一个字节：" + data);
        // read(byte[])读取一个字节数组
        byte[] data2 = new byte[128];
        int realByteCnt = fis.read(data2);
        for (int i = 0; i < realByteCnt; i++) {
            System.out.println(data2[i]);
        }
        fis.close();
        try(FileInputStream fis2 = new FileInputStream("./test2/game2.txt")) {
            int cnt = fis2.read(data2, 5, 10);
            for (int i = 0; i < cnt; i++) {
                System.out.println("read(data, offset, len)方法：" + data2[i]);
            }
        }
    }
}
