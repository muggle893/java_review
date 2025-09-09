package thread.practice4;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(()->{
                for (int j = 0; j < 20000; j++) {
                    System.out.println(Thread.currentThread().getName() + "正在执行任务!");
                }
                countDownLatch.countDown();
            });
            t.setName("线程" + i);
            t.start();
        }
        countDownLatch.await();
        System.out.println("任务全部执行完毕！");
    }
}
