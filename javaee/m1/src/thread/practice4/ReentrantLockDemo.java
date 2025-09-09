package thread.practice4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static int sum = 0;
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true); // 加参数变为一个公平锁
        // 1.测试加解锁操作
//        Thread t1 = new Thread(()->{
//            lock.lock();
//            for (int i = 0; i < 1000; i++) {
//                sum += i;
//            }
//            lock.unlock();
//        });
//        t1.start();
//        Thread t2 = new Thread(()->{
//            lock.lock();
//            for (int i = 0; i < 1000; i++) {
//                sum += i;
//            }
//            lock.unlock();
//        });
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println(sum);

    }
}
