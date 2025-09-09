package thread.practice4;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static int sum = 0;
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    semaphore.acquire();
                    sum += i;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    semaphore.acquire();
                    sum += i;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        });
        t2.start();
        Thread.sleep(2000);
        System.out.println(sum);
    }
}
