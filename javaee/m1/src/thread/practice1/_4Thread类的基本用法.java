package thread.practice1;

import com.sun.media.sound.SoftTuning;

public class _4Thread类的基本用法 {
    public static void main(String[] args) throws InterruptedException {
//        线程创建
//                线程中断
//        线程等待
//                线程休眠
//        获取线程实例
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
    }

    public static void main3(String[] args) throws InterruptedException {
//        线程创建
//                线程中断
//        线程等待
//                线程休眠
//        获取线程实例
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        });
        thread.start();
        thread.join();
    }
    public static void main2(String[] args) throws InterruptedException {
//        线程创建
//                线程中断
//        线程等待
//                线程休眠
//        获取线程实例
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程睡眠中被打断");
        });
        thread.start();
        // 线程打断
        thread.interrupt();
//        // 线程等待
//        thread.wait(2000);
//        // 线程休眠
//        Thread.sleep(1000);

    }
}
