package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class _3TestOutputStream {
    public static void main(String[] args) {
        FileOutputStream fos =  null;
        try {
            fos = new FileOutputStream("./test2/game3.txt");
            // 写入一个字节
            int data = 97;
            fos.write(data);

            // 写入一个字节数组
            fos.write(new byte[]{98, 99, 100});

            // 写入一个字节数组，但是要从offset开始写入
            fos.write(new byte[]{98, 99, 100, 101, 102, 103}, 3, 3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
