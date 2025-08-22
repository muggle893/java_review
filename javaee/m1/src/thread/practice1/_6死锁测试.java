package thread.practice1;

public class _6死锁测试 {
    public static void main(String[] args) {
//        每个线程循环 1w 次，累加变量 count 的值，
//        count 默认值为 0，注意线程安全问题。
        Object lock1 = new Object();
        Object lock2 = new Object();
        Thread t1 = new Thread(()->{
            synchronized (lock2) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("t1");
                }
            }
        });
        Thread t2 = new Thread(()->{
            synchronized (lock1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("t2");
                }
            }
        });
        t1.start();
        t2.start();

    }
}
