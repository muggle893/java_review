package thread.practice1;

public class _5累加count的值 {
    public static long count = 0;
    public static void main(String[] args) {
//        每个线程循环 1w 次，累加变量 count 的值，
//        count 默认值为 0，注意线程安全问题。
        Object lock = new Object();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    count++;
                }
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    count++;
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
